package roomBooking.api.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c from Comment c where c.author.id = :clientId")
    List<Comment> getAllCommentsByAuthorId(Long clientId);

    @Query(value = "select c from Comment c where c.property.id = :propertyId")
    List<Comment> getAllCommentsByPropertyId(Long propertyId);

    @Modifying
    @Query(value = "delete from Comment c where c.id = :id")
    int deleteCommentById(Long id);

    @Modifying
    @Query(value = "delete from Comment c where c.author.id = :id")
    int deleteAllCommentByClientId(Long id);

    @Modifying
    @Query(value = "delete from Comment c where c.property.id = :propertyId")
    void deleteAllCommentsByPropertyId(Long propertyId);
}
