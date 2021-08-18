package roomBooking.api.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.client.ClientService;
import roomBooking.api.exceptions.ClientNotFoundExceptions;
import roomBooking.api.exceptions.CommentNotFoundException;
import roomBooking.api.exceptions.PropertyNotFoundException;
import roomBooking.api.exceptions.RequiredNotNullValue;
import roomBooking.api.property.PropertyService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CommentRestController {

    private final CommentService commentService;
    private final ClientService clientService;
    private final PropertyService propertyService;

    public CommentRestController(CommentService commentService,
                                 ClientService clientService,
                                 PropertyService propertyService) {
        this.commentService = commentService;
        this.clientService = clientService;
        this.propertyService = propertyService;
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createOrUpdateComment(@RequestBody Comment comment,
                                                         @RequestParam Long clientId,
                                                         @RequestParam Long propertyId) {
        if (comment.isAnyCommentFieldNull()) {
            throw new RequiredNotNullValue("Comment has null field.");
        }
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + clientId + ".");
        }
        if (propertyService.getPropertyById(propertyId) == null) {
            throw new PropertyNotFoundException("Property not found by id: " + propertyId + ".");
        }
        if (comment.getId() == null) {
            return new ResponseEntity<>(commentService.createOrUpdateComment(comment, clientId, propertyId), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(commentService.createOrUpdateComment(comment, clientId, propertyId), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentDTOById(@PathVariable Long id) {
        if (commentService.getCommentById(id) == null) {
            throw new CommentNotFoundException("Comment not found by id: " + id + ".");
        }
        return new ResponseEntity<>(commentService.getCommentDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}/author")
    public ResponseEntity<List<CommentDTO>> getAllCommentsDTOByAuthorId(@PathVariable(name = "id") Long clientId) {
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + clientId + ".");
        }
        List<CommentDTO> commentsDTO = commentService.getAllCommentsDTOByAuthorId(clientId);
        if (commentsDTO.isEmpty()) {
            throw new CommentNotFoundException("List of comments is empty");
        }
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}/property")
    public ResponseEntity<List<CommentDTO>> getAllCommentsDTOByPropertyId(@PathVariable(name = "id") Long propertyId) {
        if (propertyService.getPropertyById(propertyId) == null) {
            throw new PropertyNotFoundException("Property not found by id: " + propertyId + ".");
        }
        List<CommentDTO> commentsDTO = commentService.getAllCommentsDTOByPropertyId(propertyId);
        if (commentsDTO.isEmpty()) {
            throw new CommentNotFoundException("List of comments is empty");
        }
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}/delete")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id) {
        if (commentService.getCommentById(id) == null) {
            throw new CommentNotFoundException("Comment not found by id: " + id + ".");
        }
        if (commentService.deleteCommentById(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/comments/{id}/delete/client")
    public ResponseEntity<?> deleteAllCommentsByClientId(@PathVariable(name = "id") Long clientId) {
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + clientId + ".");
        }
        if (commentService.deleteAllCommentByClientId(clientId) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
