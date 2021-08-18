package roomBooking.api.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDTO {

    @JsonProperty(value = "author")
    private String author;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "rating")
    private String rating;

    @JsonProperty(value = "creation_date")
    private String creationDate;

    public CommentDTO(CommentDTOBuilder b) {
        this.author = b.author;
        this.propertyName = b.propertyName;
        this.content = b.content;
        this.rating = b.rating;
        this.creationDate = b.creationDate;
    }

    public static CommentDTOBuilder builder() {
        return new CommentDTOBuilder();
    }

    public static class CommentDTOBuilder {
        private String author;
        private String propertyName;
        private String content;
        private String rating;
        private String creationDate;

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

        public CommentDTOBuilder rating(String rating) {
            this.rating = rating;
            return this;
        }

        public CommentDTOBuilder creationDate(String creationDate) {
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

    public String getRating() {
        return rating;
    }

    public String getCreationDate() {
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
