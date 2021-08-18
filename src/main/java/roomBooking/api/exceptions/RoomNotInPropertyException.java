package roomBooking.api.exceptions;

public class RoomNotInPropertyException extends RuntimeException {

    public RoomNotInPropertyException(String message) {
        super(message);
    }
}
