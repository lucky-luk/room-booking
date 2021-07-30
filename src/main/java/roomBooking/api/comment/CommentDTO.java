package roomBooking.api.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CommentDTO {

    @JsonProperty(value = "author")
    private String author;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "rating")
    private int rating;

    @JsonProperty(value = "creation_date")
    private Date creationDate;

    public CommentDTO(CommentDTOBuilder b) {
        this.author = author;
        this.propertyName = propertyName;
        this.content = content;
        this.rating = rating;
        this.creationDate = creationDate;
    }

    public static CommentDTOBuilder builder() {
        return new CommentDTOBuilder();
    }

    public static class CommentDTOBuilder {
        private String author;
        private String propertyName;
        private String content;
        private int rating;
        private Date creationDate;

        public CommentDTOBuilder author(String author) {
            this.author = author;
            return this;
        }

        public CommentDTOBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public CommentDTOBuilder content(String content) {
            this.content = content;
            return this;
        }

        public CommentDTOBuilder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public CommentDTOBuilder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public CommentDTO build() {
            return new CommentDTO(this);
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "author='" + author + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }
}
