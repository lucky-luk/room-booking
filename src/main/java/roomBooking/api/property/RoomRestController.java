package roomBooking.api.property;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.exceptions.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1")
public class RoomRestController {

    private final RoomService roomService;
    private final PropertyService propertyService;

    public RoomRestController(RoomService roomService, PropertyService propertyService) {
        this.roomService = roomService;
        this.propertyService = propertyService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> createOrUpdateRoom(@RequestBody Room room,
                                                   @RequestParam Long propertyId) {
        if (room.isAnyRoomFieldNull()) {
            throw new RequiredNotNullValue("Room has null field.");
        }
        if (propertyService.getPropertyById(propertyId) == null) {
            throw new PropertyNotFoundException("Property not found by id: " + propertyId + ".");
        }
        if (room.getId() != null) {
            if (roomService.getRoomById(room.getId()) == null) {
                throw new RoomNotFoundException("Room not found by id: " + room.getId() + ".");
            }
            return new ResponseEntity<>(roomService.createOrUpdateRoom(room, propertyId), HttpStatus.OK);
        }
        return new ResponseEntity<>(roomService.createOrUpdateRoom(room, propertyId), HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(@RequestParam(required = false) Long id,
                                      @RequestParam(required = false) Long propertyId) {
        if (id != null) {
            return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
        }
        if (propertyId != null) {
            return new ResponseEntity<>(roomService.getAllRoomsByPropertyId(propertyId), HttpStatus.OK);
        }
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/rooms/dto")
    public ResponseEntity<?> getRoomsDTO(@RequestParam(required = false) Long id,
                                         @RequestParam(required = false) Long propertyId,
                                         @RequestParam(required = false) Integer numberOfSleepingPlaces,
                                         @RequestParam(required = false) boolean value,
                                         @RequestParam(required = false) BigDecimal min,
                                         @RequestParam(required = false) BigDecimal max) {
        if (id != null) {
            return new ResponseEntity<>(roomService.getRoomDTOById(id), HttpStatus.OK);
        }
        if (propertyId != null) {
            return new ResponseEntity<>(roomService.getAllRoomsDTOByPropertyId(propertyId), HttpStatus.OK);
        }
        if (numberOfSleepingPlaces != null) {
            return new ResponseEntity<>(roomService.getAllRoomsDTOBySleepingPlaces(numberOfSleepingPlaces), HttpStatus.OK);
        }
        if (value) {
            return new ResponseEntity<>(roomService.getAllRoomsDTOWithDoubleBed(true), HttpStatus.OK);
        }
        if (min != null && max != null) {
            return new ResponseEntity<>(roomService.getAllRoomsDTOByMinMaxPrice(min, max), HttpStatus.OK);
        }
        return new ResponseEntity<>(roomService.getAllRoomsDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/rooms/delete")
    public ResponseEntity<?> deleteProperty(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) Long propertyId) {
        if (id == null && propertyId == null) {
            throw new WrongParameterException("One of the parameters must be filled in.");
        }
        if (id != null) {
            if (roomService.deleteRoomById(id) > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            if (roomService.deleteAllRoomsByPropertyId(propertyId) > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
    }
}
