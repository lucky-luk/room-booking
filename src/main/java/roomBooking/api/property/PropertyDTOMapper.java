package roomBooking.api.property;

import org.springframework.stereotype.Component;
import roomBooking.api.comment.CommentDTO;
import roomBooking.api.comment.CommentDTOMapper;
import roomBooking.api.utils.DTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyDTOMapper implements DTOMapper<Property, PropertyDTO> {

    private final AddressDTOMapper addressDTOMapper;
    private final RoomDTOMapper roomDTOMapper;
    private final CommentDTOMapper commentDTOMapper;

    public PropertyDTOMapper(AddressDTOMapper addressDTOMapper, RoomDTOMapper roomDTOMapper, CommentDTOMapper commentDTOMapper) {
        this.addressDTOMapper = addressDTOMapper;
        this.roomDTOMapper = roomDTOMapper;
        this.commentDTOMapper = commentDTOMapper;
    }

    @Override
    public PropertyDTO from(Property from) {

        List<RoomDTO> rooms = from.getRooms()
                .stream()
                .map(roomDTOMapper::from)
                .collect(Collectors.toList());

        List<CommentDTO> comments = from.getComments()
                .stream()
                .map(commentDTOMapper::from)
                .collect(Collectors.toList());

        PropertyDTO propertyDTO = PropertyDTO
                .builder()
                .propertyName(from.getPropertyName())
                .propertyType(from.getPropertyType().name())
                .numberOfRooms(String.valueOf(from.getNumberOfRooms()))
                .host(from.getHost().getCompanyName())
                .address(addressDTOMapper.from(from.getAddress()))
                .parkingAvailable(String.valueOf(from.isParkingAvailable()))
                .restaurantAvailable(String.valueOf(from.isRestaurantAvailable()))
                .swimmingPoolAvailable(String.valueOf(from.isSwimmingPoolAvailable()))
                .petsAllowed(String.valueOf(from.isPetsAllowed()))
                .description(from.getDescription())
                .rooms(rooms)
                .comments(comments)
                .build();
        return propertyDTO;
    }
}
