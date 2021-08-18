package roomBooking.api.exceptions;

public class ExceptionBody {
    private String message;
    private String timestamp;
    private String path;
    private int status;

    public ExceptionBody(Builder b) {
        this.message = b.message;
        this.timestamp = b.timestamp;
        this.path = b.path;
        this.status = b.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String timestamp;
        private String path;
        private int status;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ExceptionBody build() {
            return new ExceptionBody(this);
        }
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ExceptionBody{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }
}
