package roomBooking.api.client;

import org.springframework.stereotype.Component;
import roomBooking.api.booking.BookingDTO;
import roomBooking.api.booking.BookingDTOMapper;
import roomBooking.api.utils.DTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientDTOMapper implements DTOMapper<Client, ClientDTO> {

    private final BookingDTOMapper bookingDTOMapper;

    public ClientDTOMapper(BookingDTOMapper bookingDTOMapper) {
        this.bookingDTOMapper = bookingDTOMapper;
    }

    @Override
    public ClientDTO from(Client from) {

        List<BookingDTO> bookings = from.getBookings()
                .stream()
                .map(bookingDTOMapper::from)
                .collect(Collectors.toList());

        ClientDTO clientDTO = ClientDTO
                .builder()
                .name(from.getName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .nationality(from.getNationality())
                .registrationDate(from.getRegistrationDate().toString())
                .bookings(bookings)
                .build();
        return clientDTO;
    }
}
