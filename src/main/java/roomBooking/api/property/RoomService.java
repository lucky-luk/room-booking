package roomBooking.api.property;

import org.springframework.stereotype.Service;
import roomBooking.api.booking.BookingRepository;
import roomBooking.api.exceptions.MaxNumberOfRoomsException;
import roomBooking.api.exceptions.PropertyNotFoundException;
import roomBooking.api.exceptions.RoomNotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PropertyService propertyService;
    private final BookingRepository bookingRepository;
    private final RoomDTOMapper roomDTOMapper;

    public RoomService(RoomRepository roomRepository,
                       PropertyService propertyService,
                       BookingRepository bookingRepository,
                       RoomDTOMapper roomDTOMapper) {
        this.roomRepository = roomRepository;
        this.propertyService = propertyService;
        this.bookingRepository = bookingRepository;
        this.roomDTOMapper = roomDTOMapper;
    }

    public Room createOrUpdateRoom(Room room, Long propertyId) {
        if (!canRoomBeAdd(propertyId)) {
            throw new MaxNumberOfRoomsException("Room cannot be add to property. Max number of rooms were added.");
        }
        room.setProperty(propertyService.getPropertyById(propertyId));
        room.setCurrency(Currency.PLN);
        return roomRepository.save(room);
    }

    public boolean canRoomBeAdd(Long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        int maxNumberOfRooms = property.getNumberOfRooms();
        int numberOfAddedRooms = property.getRooms().size();
        return numberOfAddedRooms < maxNumberOfRooms;
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public RoomDTO getRoomDTOById(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) {
            throw new RoomNotFoundException("Room not found by id: " + id + ".");
        }
        return roomDTOMapper.from(room);
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new RoomNotFoundException("List of rooms is empty.");
        }
        return rooms;
    }

    public List<RoomDTO> getAllRoomsDTO() {
        List<Room> rooms = getAllRooms();
        return rooms
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<Room> getAllRoomsByPropertyId(Long propertyId) {
        List<Room> rooms = roomRepository.getAllRoomsByProperty(propertyId);
        if (rooms == null) {
            throw new RoomNotFoundException("List of rooms by property id: " + propertyId + " is empty.");
        }
        return rooms;
    }

    public List<RoomDTO> getAllRoomsDTOByPropertyId(Long propertyId) {
        List<Room> rooms = getAllRoomsByPropertyId(propertyId);
        return rooms
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getAllRoomsDTOBySleepingPlaces(Integer numberOfSleepingPlaces) {
        List<Room> rooms = roomRepository.getAllRoomsBySleepingPlaces(numberOfSleepingPlaces);
        if (rooms == null) {
            throw new RoomNotFoundException("List of rooms by number of sleeping places: " + numberOfSleepingPlaces + " is empty.");
        }
        return rooms
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getAllRoomsDTOWithDoubleBed(boolean value) {
        List<Room> rooms = roomRepository.getAllRoomsWithDoubleBed(value);
        if (rooms == null) {
            throw new RoomNotFoundException("List of rooms with double bed is empty.");
        }
        return rooms
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getAllRoomsDTOByMinMaxPrice(BigDecimal min, BigDecimal max) {
        List<Room> rooms = roomRepository.getAllRoomsByMinMaxPrice(min, max);
        if (rooms == null) {
            throw new RoomNotFoundException("List of rooms by min: " + min + " max: " + max + " price is empty.");
        }
        return rooms
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public int deleteRoomById(Long id) {
        if (getRoomById(id) == null) {
            throw new RoomNotFoundException("Room not found by id: " + id + ".");
        }
        bookingRepository.deleteAllBookingsByRoomId(id);
        return roomRepository.deleteRoomById(id);
    }

    @Transactional
    public int deleteAllRoomsByPropertyId(Long propertyId) {
        if (propertyService.getPropertyById(propertyId) == null) {
            throw new PropertyNotFoundException("Property not found by id: " + propertyId + ".");
        }
        if (getAllRoomsByPropertyId(propertyId).isEmpty()) {
            throw new RoomNotFoundException("List of rooms by property id: " + propertyId + " is empty.");
        }
        bookingRepository.deleteAllBookingsByPropertyId(propertyId);
        return roomRepository.deleteAllRoomsByPropertyId(propertyId);
    }

    public boolean isRoomInProperty(Long roomId, Long propertyId) {
        List<Room> rooms = propertyService.getPropertyById(propertyId).getRooms();
        for (Room r : rooms) {
            if (roomId.equals(r.getId())) {
                return true;
            }
        }
        return false;
    }
}
