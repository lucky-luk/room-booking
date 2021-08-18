package roomBooking.api.comment;

import org.springframework.stereotype.Service;
import roomBooking.api.booking.Booking;
import roomBooking.api.booking.BookingService;
import roomBooking.api.client.ClientService;
import roomBooking.api.exceptions.WrongDataFormatException;
import roomBooking.api.exceptions.WrongParameterException;
import roomBooking.api.property.PropertyService;
import roomBooking.api.utils.DataFormatValidator;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookingService bookingService;
    private final ClientService clientService;
    private final PropertyService propertyService;
    private final CommentDTOMapper commentDTOMapper;
    private final DataFormatValidator dataFormatValidator;

    public CommentService(CommentRepository commentRepository,
                          BookingService bookingService,
                          ClientService clientService,
                          PropertyService propertyService,
                          CommentDTOMapper commentDTOMapper,
                          DataFormatValidator dataFormatValidator) {
        this.commentRepository = commentRepository;
        this.bookingService = bookingService;
        this.clientService = clientService;
        this.propertyService = propertyService;
        this.commentDTOMapper = commentDTOMapper;
        this.dataFormatValidator = dataFormatValidator;
    }

    public Comment createOrUpdateComment(Comment comment, Long clientId, Long propertyId) {
        if (!wasClientInProperty(clientId, propertyId)) {
            throw new WrongParameterException("Client (id: " + clientId + ") has not been in property id: " + propertyId + ".");
        }
        if (!dataFormatValidator.validateRating(comment.getRating())) {
            throw new WrongDataFormatException("Rating: " + comment.getRating() + " is out of range. Rating range: 1 - 6.");
        }
        comment.setAuthor(clientService.getClientById(clientId));
        comment.setProperty(propertyService.getPropertyById(propertyId));
        comment.setCreationDate(Date.valueOf(LocalDate.now()));
        return commentRepository.save(comment);
    }

    public boolean wasClientInProperty(Long clientId, Long propertyId) {
        List<Booking> bookings = bookingService.getAllBookingsByClientId(clientId);
        for (Booking b : bookings) {
            if (propertyId.equals(b.getProperty().getId())) {
                return true;
            }
        }
        return false;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public CommentDTO getCommentDTOById(Long id) {
        return commentDTOMapper.from(getCommentById(id));
    }

    public List<Comment> getAllCommentsByAuthorId(Long clientId) {
        return commentRepository.getAllCommentsByAuthorId(clientId);
    }

    public List<CommentDTO> getAllCommentsDTOByAuthorId(Long clientId) {
        List<Comment> comments = getAllCommentsByAuthorId(clientId);
        return comments
                .stream()
                .map(commentDTOMapper::from)
                .collect(Collectors.toList());
    }

    public List<Comment> getAllCommentsByPropertyId(Long propertyId) {
        return commentRepository.getAllCommentsByPropertyId(propertyId);
    }

    public List<CommentDTO> getAllCommentsDTOByPropertyId(Long propertyId) {
        List<Comment> comments = getAllCommentsByPropertyId(propertyId);
        return comments
                .stream()
                .map(commentDTOMapper::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public int deleteCommentById(Long id) {
        return commentRepository.deleteCommentById(id);
    }

    @Transactional
    public int deleteAllCommentByClientId(Long id) {
        return commentRepository.deleteAllCommentByClientId(id);
    }
}
