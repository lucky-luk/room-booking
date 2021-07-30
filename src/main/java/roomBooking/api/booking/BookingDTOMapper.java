package roomBooking.api.booking;

import org.springframework.stereotype.Component;
import roomBooking.api.utils.DTOMapper;

@Component
public class BookingDTOMapper implements DTOMapper<Booking, BookingDTO> {
    @Override
    public BookingDTO from(Booking from) {
        BookingDTO bookingDTO = BookingDTO
                .builder()
                .clientName(from.getClient().getName() + " " + from.getClient().getLastName())
                .propertyName(from.getProperty().getPropertyName())
                .roomId(from.getRoom().getId())
                .bookingFrom(from.getBookingFrom())
                .bookingTo(from.getBookingTo())
                .bookingDays(from.getBookingDays())
                .bookingCost(from.getBookingCost())
                .currency(from.getCurrency().name())
                .paymentMethod((from.getPaymentMethod().name()))
                .bookingDate(from.getBookingDate())
                .build();
        return bookingDTO;
    }
}
