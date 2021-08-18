package roomBooking.api.exceptions;

public class WrongBookingDate extends RuntimeException {

    public WrongBookingDate(String message) {
        super(message);
    }
}
