package roomBooking.api.exceptions;

public class WrongParameterException extends RuntimeException {

    public WrongParameterException(String message) {
        super(message);
    }
}
