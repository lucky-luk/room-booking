package roomBooking.api.exceptions;

public class RequiredNotNullValue extends RuntimeException {

    public RequiredNotNullValue(String message) {
        super(message);
    }
}
