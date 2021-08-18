package roomBooking.api.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.client.ClientService;
import roomBooking.api.exceptions.*;
import roomBooking.api.property.PropertyService;
import roomBooking.api.property.RoomService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookingRestController {

    private final BookingService bookingService;
    private final ClientService clientService;
    private final PropertyService propertyService;
    private final RoomService roomService;

    public BookingRestController(BookingService bookingService,
                                 ClientService clientService,
                                 PropertyService propertyService,
                                 RoomService roomService) {
        this.bookingService = bookingService;
        this.clientService = clientService;
        this.propertyService = propertyService;
        this.roomService = roomService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking,
                                                 @RequestParam Long clientId,
                                                 @RequestParam Long propertyId,
                                                 @RequestParam Long roomId) {
        if (booking.isAnyBookingFieldNull()) {
            throw new RequiredNotNullValue("Booking has null field.");
        }
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + clientId + ".");
        }
        if (propertyService.getPropertyById(propertyId) == null) {
            throw new PropertyNotFoundException("Property not found by id: " + propertyId + ".");
        }
        if (roomService.getRoomById(roomId) == null) {
            throw new RoomNotFoundException("Room not found by id: " + roomId + ".");
        }
        return new ResponseEntity<>(bookingService.createBooking(booking, clientId, propertyId, roomId),
                HttpStatus.CREATED);
    }

    @PutMapping("/bookings/{id}/update")
    public ResponseEntity<Booking> updateBookingDate(@RequestParam Date newDateFrom,
                                                     @RequestParam Date newDateTo,
                                                     @PathVariable Long id) {
        try {
            return new ResponseEntity<>(bookingService.updateBookingDate(id, newDateFrom, newDateTo), HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            throw new BookingNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingDTO> getBookingDTOById(@PathVariable Long id) {
        if (bookingService.getBookingById(id) == null) {
            throw new BookingNotFoundException("Booking not found");
        }
        return new ResponseEntity<>(bookingService.getBookingDTOById(id), HttpStatus.OK);
    }

    @DeleteMapping("bookings/{id}/delete")
    public ResponseEntity<?> deleteBookingById(@PathVariable Long id) {
        if (bookingService.deleteBookingById(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("bookings/delete/client")
    public ResponseEntity<?> deleteAllBookingsByClientId(Long clientId) {
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + clientId + ".");
        }
        if (bookingService.getAllBookingsByClientId(clientId).isEmpty()) {
            throw new BookingNotFoundException("Client has no any bookings.");
        }
        bookingService.deleteAllBookingByClientId(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookingsDTO() {
        List<BookingDTO> bookingsDTO = bookingService.getAllBookingsDTO();
        if (bookingsDTO.isEmpty()) {
            throw new BookingNotFoundException("List of bookings is empty.");
        }
        return new ResponseEntity<>(bookingsDTO, HttpStatus.OK);
    }

    @GetMapping("/bookings/client")
    public ResponseEntity<List<BookingDTO>> getBookingsDTOByClient(@RequestParam String email) {
        if (clientService.getClientByEmail(email) == null) {
            throw new ClientNotFoundExceptions("Client not found.");
        }
        List<BookingDTO> bookingsDTO = bookingService.getAllBookingsDTOByClientEmail(email);
        if (bookingsDTO.isEmpty()) {
            throw new BookingNotFoundException("List of bookings by client is empty.");
        }
        return new ResponseEntity<>(bookingsDTO, HttpStatus.OK);
    }

    @GetMapping("/bookings/room")
    public ResponseEntity<List<BookingDTO>> getBookingByRoom(@RequestParam Long roomId) {
        if (roomService.getRoomById(roomId) == null) {
            throw new RoomNotFoundException("Room not found.");
        }
        List<BookingDTO> bookingsDTO = bookingService.getAllBookingsDTOByRoom(roomId);
        if (bookingsDTO.isEmpty()) {
            throw new BookingNotFoundException("List of bookings by room is empty.");
        }
        return new ResponseEntity<>(bookingsDTO, HttpStatus.OK);
    }

    @GetMapping("/bookings/room/available")
    public ResponseEntity<Boolean> isRoomAvailable(@RequestParam Date from,
                                                   @RequestParam Date to,
                                                   @RequestParam Long roomId) {
        if (roomService.getRoomById(roomId) == null) {
            throw new RoomNotFoundException("Room not found.");
        }
        return new ResponseEntity<>(bookingService.isRoomAvailable(from, to, roomId), HttpStatus.OK);
    }
}
