package roomBooking.api.property;

import org.springframework.stereotype.Component;
import roomBooking.api.utils.DTOMapper;

@Component
public class RoomDTOMapper implements DTOMapper<Room, RoomDTO> {
    @Override
    public RoomDTO from(Room from) {
        RoomDTO roomDTO = RoomDTO
                .builder()
                .property(from.getProperty().getPropertyName())
                .pricePerNight(from.getPricePerNight())
                .currency(from.getCurrency().name())
                .sleepingPlaces(from.getSleepingPlaces())
                .singleBed(from.isSingleBed())
                .doubleBed(from.isDoubleBed())
                .tvInRoom(from.isTvInRoom())
                .privateKitchen(from.isPrivateKitchen())
                .wifi(from.isWifi())
                .isAvailable(from.getAvailable())
                .build();
        return roomDTO;
    }
}
