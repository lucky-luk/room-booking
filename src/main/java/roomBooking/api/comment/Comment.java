package roomBooking.api.comment;

import roomBooking.api.client.Client;
import roomBooking.api.property.Property;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getAuthor() {
        return author;
    }

    public void setAuthor(Client author) {
        this.author = author;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", property=" + property +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return rating == comment.rating && Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(property, comment.property) && Objects.equals(content, comment.content) && Objects.equals(creationDate, comment.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, property, content, rating, creationDate);
    }
}
