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
                .pricePerNight(String.valueOf(from.getPricePerNight()))
                .currency(from.getCurrency().name())
                .sleepingPlaces(String.valueOf(from.getSleepingPlaces()))
                .singleBed(String.valueOf(from.isSingleBed()))
                .doubleBed(String.valueOf(from.isDoubleBed()))
                .tvInRoom(String.valueOf(from.isTvInRoom()))
                .privateKitchen(String.valueOf(from.isPrivateKitchen()))
                .wifi(String.valueOf(from.isWifi()))
                .build();
        return roomDTO;
    }
}
