package roomBooking.api.exceptions;

public class MaxNumberOfRoomsException extends RuntimeException {

    public MaxNumberOfRoomsException(String message) {
        super(message);
    }
}
