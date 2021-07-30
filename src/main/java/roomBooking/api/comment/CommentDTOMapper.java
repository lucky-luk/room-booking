package roomBooking.api.comment;

import org.springframework.stereotype.Component;
import roomBooking.api.utils.DTOMapper;

@Component
public class CommentDTOMapper implements DTOMapper<Comment, CommentDTO> {
    @Override
    public CommentDTO from(Comment from) {
        CommentDTO commentDTO = CommentDTO
                .builder()
                .author(from.getAuthor().getName() + " " + from.getAuthor().getLastName())
                .propertyName(from.getProperty().getPropertyName())
                .content(from.getContent())
                .rating(from.getRating())
                .creationDate(from.getCreationDate())
                .build();
        return commentDTO;
    }
}
