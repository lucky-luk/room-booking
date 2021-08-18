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
                .propertyName(from.getRoom().getProperty().getPropertyName())
                .roomId(from.getRoom().getId().toString())
                .bookingFrom(from.getBookingFrom().toString())
                .bookingTo(from.getBookingTo().toString())
                .bookingDays(String.valueOf(from.getBookingDays()))
                .bookingCost(from.getBookingCost().toString())
                .currency(from.getCurrency().name())
                .paymentMethod((from.getPaymentMethod().name()))
                .bookingDate(from.getBookingDate().toString())
                .build();
        return bookingDTO;
    }
}
