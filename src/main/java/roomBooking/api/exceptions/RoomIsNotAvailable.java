package roomBooking.api.exceptions;

public class RoomIsNotAvailable extends RuntimeException {

    public RoomIsNotAvailable(String message) {
        super(message);
    }
}
