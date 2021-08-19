package roomBooking.api.property;

import org.springframework.stereotype.Service;
import roomBooking.api.booking.BookingRepository;
import roomBooking.api.comment.CommentRepository;
import roomBooking.api.exceptions.PropertyNotFoundException;
import roomBooking.api.exceptions.WrongDataFormatException;
import roomBooking.api.host.HostService;
import roomBooking.api.utils.DataFormatValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final HostService hostService;
    private final PropertyDTOMapper propertyDTOMapper;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;
    private final DataFormatValidator dataFormatValidator;

    public PropertyService(PropertyRepository propertyRepository,
                           HostService hostService,
                           PropertyDTOMapper propertyDTOMapper,
                           RoomRepository roomRepository,
                           BookingRepository bookingRepository,
                           CommentRepository commentRepository,
                           DataFormatValidator dataFormatValidator) {
        this.propertyRepository = propertyRepository;
        this.hostService = hostService;
        this.propertyDTOMapper = propertyDTOMapper;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;
        this.dataFormatValidator = dataFormatValidator;
    }

    public Property createOrUpdateProperty(Property property, Long hostId) {
        if (!dataFormatValidator.validatePostalCode(property.getAddress().getPostalCode())) {
            throw new WrongDataFormatException("Wrong postal code format: " + property.getAddress().getPostalCode() + ".");
        }
        property.setHost(hostService.getHostById(hostId));
        if (property.getId() != null) {
            property.getAddress().setId(getPropertyById(property.getId()).getAddress().getId());
        }
        return propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property getPropertyByName(String propertyName) {
        return propertyRepository.getPropertyByName(propertyName);
    }

    public PropertyDTO getPropertyDTOById(Long id) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property == null) {
            throw new PropertyNotFoundException("Property not found by id: " + id + ".");
        }
        return propertyDTOMapper.from(property);
    }

    public PropertyDTO getPropertyDTOByName(String propertyName) {
        Property property = propertyRepository.getPropertyByName(propertyName);
        if (property == null) {
            throw new PropertyNotFoundException("Property not found by property name: " + propertyName + ".");
        }
        return propertyDTOMapper.from(property);
    }

    public List<PropertyDTO> getPropertyDTOByType(PropertyType propertyType) {
        List<Property> properties = propertyRepository.getAllPropertiesByType(propertyType);
        if (properties.isEmpty()) {
            throw new PropertyNotFoundException("List of properties by property type: " + propertyType + " is empty.");
        }
        return properties
                .stream()
                .map(propertyDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<PropertyDTO> getPropertyDTOByCity(String city) {
        List<Property> properties = propertyRepository.getAllPropertiesByCity(city);
        if (properties.isEmpty()) {
            throw new PropertyNotFoundException("List of properties by city: " + city + " is empty.");
        }
        return properties
                .stream()
                .map(propertyDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<PropertyDTO> getAllPropertiesDTO() {
        List<PropertyDTO> propertiesDTO = propertyRepository.findAll()
                .stream()
                .map(propertyDTOMapper::from)
                .collect(Collectors.toList());
        if (propertiesDTO.isEmpty()) {
            throw new PropertyNotFoundException("List of properties is empty.");
        }
        return propertiesDTO;
    }

    @Transactional
    public void deletePropertyById(Long id) {
        bookingRepository.deleteAllBookingsByPropertyId(id);
        roomRepository.deleteAllRoomsByPropertyId(id);
        commentRepository.deleteAllCommentsByPropertyId(id);
        Long addressId = getPropertyById(id).getAddress().getId();
        propertyRepository.deletePropertyById(id);
        propertyRepository.deleteAddressById(addressId);
    }

    @Transactional
    public void deletePropertyByName(String propertyName) {
        Long propertyId = getPropertyByName(propertyName).getId();
        bookingRepository.deleteAllBookingsByPropertyId(propertyId);
        roomRepository.deleteAllRoomsByPropertyId(propertyId);
        commentRepository.deleteAllCommentsByPropertyId(propertyId);
        Long addressId = getPropertyByName(propertyName).getAddress().getId();
        propertyRepository.deletePropertyByName(propertyName);
        propertyRepository.deleteAddressById(addressId);
    }
}
