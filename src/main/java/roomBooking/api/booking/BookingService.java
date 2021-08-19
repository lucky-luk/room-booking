package roomBooking.api.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import roomBooking.api.client.ClientService;
import roomBooking.api.exceptions.BookingNotFoundException;
import roomBooking.api.exceptions.RoomIsNotAvailable;
import roomBooking.api.exceptions.RoomNotInPropertyException;
import roomBooking.api.exceptions.WrongBookingDate;
import roomBooking.api.property.PropertyService;
import roomBooking.api.property.RoomService;
import roomBooking.api.webclient.WebClientService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final static Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final PropertyService propertyService;
    private final RoomService roomService;
    private final BookingDTOMapper bookingDTOMapper;
    private final WebClientService webClientService;

    public BookingService(BookingRepository bookingRepository,
                          ClientService clientService,
                          PropertyService propertyService,
                          RoomService roomService,
                          BookingDTOMapper bookingDTOMapper,
                          WebClientService webClientService) {
        this.bookingRepository = bookingRepository;
        this.clientService = clientService;
        this.propertyService = propertyService;
        this.roomService = roomService;
        this.bookingDTOMapper = bookingDTOMapper;
        this.webClientService = webClientService;
    }

    public Booking createBooking(Booking booking, Long clientId,
                                 Long propertyId, Long roomId) {
        logger.info("Try to create booking for: client id: {}, property id: {}, room id: {}.", clientId, propertyId, roomId);
        Date currentDate = new Date(new java.util.Date().getTime());
        Long bookingDays = getBookingDays(currentDate, booking.getBookingFrom());
        if (bookingDays < 0) {
            logger.error("Exception: Booking DATE FROM can't be earlier than current date.");
            throw new WrongBookingDate("Booking DATE FROM can't be earlier than current date.");
        }
        bookingDays = getBookingDays(booking.getBookingFrom(), booking.getBookingTo());
        if (bookingDays == 0) {
            logger.error("Exception: Booking DATE FROM can't be the same like booking DATE TO.");
            throw new WrongBookingDate("Booking DATE FROM can't be the same like booking DATE TO.");
        }
        if (bookingDays < 0) {
            logger.error("Exception: Booking DATE TO can't be earlier than booking DATE FROM.");
            throw new WrongBookingDate("Booking DATE TO can't be earlier than booking DATE FROM.");
        }
        if (!isRoomAvailable(booking.getBookingFrom(), booking.getBookingTo(), roomId)) {
            logger.error("Exception: Room is not available from " + booking.getBookingFrom() + " to " + booking.getBookingTo() + ".");
            throw new RoomIsNotAvailable("Room is not available from " + booking.getBookingFrom() + " to " + booking.getBookingTo() + ".");
        }
        booking.setClient(clientService.getClientById(clientId));
        booking.setProperty(propertyService.getPropertyById(propertyId));
        if (roomService.isRoomInProperty(roomId, propertyId)) {
            booking.setRoom(roomService.getRoomById(roomId));
        } else {
            logger.error("Exception: Room (id: " + roomId + ") not belong to property (id: " + propertyId + ").");
            throw new RoomNotInPropertyException("Room (id: " + roomId + ") not belong to property (id: " + propertyId + ").");
        }
        booking.setBookingDays(bookingDays);
        booking.setBookingCost(getBookingCost(roomService.getRoomById(roomId).getPricePerNight(),
                bookingDays,
                booking.getCurrency().name()));
        booking.setBookingDate(Date.valueOf(LocalDate.now()));
        logger.debug("Booking created");
        return bookingRepository.save(booking);
    }

    public Booking updateBookingDate(Long id, Date newDateFrom, Date newDateTo) {
        logger.info("Try to update booking date for booking id: {}.", id);
        Booking booking = getBookingById(id);
        if (booking == null) {
            logger.error("Exception: Booking not found by id: " + id + ".");
            throw new BookingNotFoundException("Booking not found by id: " + id + ".");
        }
        Date currentDate = new Date(new java.util.Date().getTime());
        Long bookingDays = getBookingDays(currentDate, newDateFrom);
        if (bookingDays < 0) {
            logger.error("Exception: Booking DATE FROM can't be earlier than current date.");
            throw new WrongBookingDate("Booking DATE FROM can't be earlier than current date.");
        }
        bookingDays = getBookingDays(newDateFrom, newDateTo);
        if (bookingDays == 0) {
            logger.error("Exception: Booking DATE FROM can't be the same like booking DATE TO.");
            throw new WrongBookingDate("Booking DATE FROM can't be the same like booking DATE TO.");
        }
        if (bookingDays < 0) {
            logger.error("Exception: Booking DATE TO can't be earlier than booking DATE FROM.");
            throw new WrongBookingDate("Booking DATE TO can't be earlier than booking DATE FROM.");
        }
        Date previousDateFrom = booking.getBookingFrom();
        Date previousDateTo = booking.getBookingTo();
        Long roomId = booking.getRoom().getId();
        bookingDays = booking.getBookingDays();
        if (!newDateFrom.before(previousDateFrom) && !newDateTo.after(previousDateTo)) {
            bookingDays = getBookingDays(newDateFrom, newDateTo);
        }
        if (newDateFrom.before(previousDateFrom) && !newDateTo.after(previousDateTo)) {
            if (!isRoomAvailable(newDateFrom, previousDateFrom, roomId)) {
                throw new RoomIsNotAvailable("Room is not available from " + newDateFrom + " to " + previousDateFrom + ".");
            }
            bookingDays = getBookingDays(newDateFrom, newDateTo);
        }
        if (!newDateFrom.before(previousDateFrom) && newDateTo.after(previousDateTo)) {
            if (!isRoomAvailable(previousDateTo, newDateTo, roomId)) {
                logger.error("Exception: Room is not available from " + previousDateTo + " to " + newDateTo + ".");
                throw new RoomIsNotAvailable("Room is not available from " + previousDateTo + " to " + newDateTo + ".");
            }
            bookingDays = getBookingDays(newDateFrom, newDateTo);
        }
        if (newDateFrom.before(previousDateFrom) && newDateTo.after(previousDateTo)) {
            if (!isRoomAvailable(newDateFrom, previousDateFrom, roomId)) {
                logger.error("Exception: Room is not available from " + newDateFrom + " to " + previousDateFrom + ".");
                throw new RoomIsNotAvailable("Room is not available from " + newDateFrom + " to " + previousDateFrom + ".");
            }
            if (!isRoomAvailable(previousDateTo, newDateTo, roomId)) {
                logger.error("Exception: Room is not available from " + previousDateTo + " to " + newDateTo + ".");
                throw new RoomIsNotAvailable("Room is not available from " + previousDateTo + " to " + newDateTo + ".");
            }
            bookingDays = getBookingDays(newDateFrom, newDateTo);
        }
        if (newDateFrom.before(previousDateFrom) && newDateTo.before(previousDateFrom) ||
                newDateFrom.after(previousDateTo) && newDateTo.after(previousDateTo)) {
            if (!isRoomAvailable(newDateFrom, newDateTo, roomId)) {
                logger.error("Exception: Room is not available from " + newDateFrom + " to " + newDateTo + ".");
                throw new RoomIsNotAvailable("Room is not available from " + newDateFrom + " to " + newDateTo + ".");
            }
            bookingDays = getBookingDays(newDateFrom, newDateTo);
        }
        booking.setBookingFrom(newDateFrom);
        booking.setBookingTo(newDateTo);
        booking.setBookingDays(bookingDays);
        booking.setBookingCost(getBookingCost(roomService.getRoomById(booking.getRoom().getId()).getPricePerNight(),
                bookingDays,
                booking.getCurrency().name()));
        booking.setBookingDate(Date.valueOf(LocalDate.now()));
        logger.debug("Booking created");
        return bookingRepository.save(booking);
    }

    private Long getBookingDays(Date bookingFrom, Date bookingTo) {
        logger.info("Try to get booking days.");
        Long bookingDays = ChronoUnit.DAYS.between(LocalDate.parse(bookingFrom.toString()), LocalDate.parse(bookingTo.toString()));
        logger.debug("Return {} booking days.", bookingDays);
        return bookingDays;
    }

    private BigDecimal getBookingCost(BigDecimal pricePerNight, Long bookingDays, String currency) {
        logger.info("Try to get booking cost.");
        BigDecimal bookingCost;
        if (currency.equals("PLN")) {
            bookingCost = pricePerNight.multiply(BigDecimal.valueOf(bookingDays));
        } else {
            BigDecimal exchangeRate = BigDecimal.valueOf(webClientService.getAskRate(currency));
            bookingCost = pricePerNight.divide(exchangeRate, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(bookingDays));
        }
        logger.debug("Return booking cost: {}.", bookingCost);
        return bookingCost;
    }

    public Booking getBookingById(Long id) {
        logger.info("Try to get booking by id: {}.", id);
        return bookingRepository.findById(id).orElse(null);
    }

    public BookingDTO getBookingDTOById(Long id) {
        logger.info("Try to get bookingDTO by id: {}.", id);
        return bookingDTOMapper.from(bookingRepository.findById(id).orElse(null));
    }

    public List<BookingDTO> getAllBookingsDTO() {
        logger.info("Try to get all bookingsDTO.");
        List<Booking> bookings = bookingRepository.findAll();
        logger.debug("Return {} bookings.", bookings.size());
        return bookings
                .stream()
                .map(bookingDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookingsByRoom(Long roomId) {
        logger.info("Try to get all bookings by room id: {}.", roomId);
        List<Booking> bookings = bookingRepository.getAllBookingsByRoom(roomId);
        logger.debug("Return {} bookings.", bookings.size());
        return bookings;
    }

    public List<BookingDTO> getAllBookingsDTOByRoom(Long roomId) {
        logger.info("Try to get all bookingsDTO by room id: {}.", roomId);
        List<Booking> bookings = bookingRepository.getAllBookingsByRoom(roomId);
        logger.debug("Return {} bookings.", bookings.size());
        return bookings
                .stream()
                .map(bookingDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookingsByClientId(Long clientId) {
        logger.info("Try to get all bookings by client id: {}.", clientId);
        List<Booking> bookings = bookingRepository.getAllBookingsByClientId(clientId);
        logger.debug("Return {} bookings.", bookings.size());
        return bookings;
    }

    public List<BookingDTO> getAllBookingsDTOByClientEmail(String email) {
        logger.info("Try to get all bookingsDTO by client email: {}", email);
        List<Booking> bookings = bookingRepository.getAllBookingsByClientEmail(email);
        logger.debug("Return {} bookings.", bookings.size());
        return bookings
                .stream()
                .map(bookingDTOMapper::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public int deleteBookingById(Long id) {
        logger.info("Try to delete booking by id: {}.", id);
        return bookingRepository.deleteBookingById(id);
    }

    @Transactional
    public void deleteAllBookingByClientId(Long clientId) {
        logger.info("Try to delete booking by client id: {}.", clientId);
        bookingRepository.deleteAllBookingsByClientId(clientId);
    }

    public boolean isRoomAvailable(Date from, Date to, Long roomId) {
        logger.info("Try to check is room id: {} available from: {} to: {}", roomId, from, to);
        List<Booking> bookings = getAllBookingsByRoom(roomId);
        long numberOfDays = getBookingDays(from, to);
        for (Booking booking : bookings) {
            Date bookingFrom = Date.valueOf(booking.getBookingFrom().toLocalDate().minusDays(1));
            Date bookingTo = booking.getBookingTo();
            for (long j = 0; j < numberOfDays; j++) {
                LocalDate day = from.toLocalDate().plusDays(j);
                Date dayOfBooking = Date.valueOf(day);
                if (dayOfBooking.after(bookingFrom) && dayOfBooking.before(bookingTo)) {
                    return false;
                }
            }
        }
        return true;
    }
}
