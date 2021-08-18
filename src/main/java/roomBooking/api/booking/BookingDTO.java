package roomBooking.api.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDTO {

    @JsonProperty(value = "client_name")
    private String clientName;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "room_id")
    private String roomId;

    @JsonProperty(value = "booking_from")
    private String bookingFrom;

    @JsonProperty(value = "booking_to")
    private String bookingTo;

    @JsonProperty(value = "booking_days")
    private String bookingDays;

    @JsonProperty(value = "booking_cost")
    private String bookingCost;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "payment_method")
    private String paymentMethod;

    @JsonProperty(value = "booking_date")
    private String bookingDate;

    public BookingDTO(BookingDTOBuilder b) {
        this.clientName = b.clientName;
        this.propertyName = b.propertyName;
        this.roomId = b.roomId;
        this.bookingFrom = b.bookingFrom;
        this.bookingTo = b.bookingTo;
        this.bookingDays = b.bookingDays;
        this.bookingCost = b.bookingCost;
        this.currency = b.currency;
        this.paymentMethod = b.paymentMethod;
        this.bookingDate = b.bookingDate;
    }

    public static BookingDTOBuilder builder() {
        return new BookingDTOBuilder();
    }

    public static class BookingDTOBuilder {
        private String clientName;
        private String propertyName;
        private String roomId;
        private String bookingFrom;
        private String bookingTo;
        private String bookingDays;
        private String bookingCost;
        private String currency;
        private String paymentMethod;
        private String bookingDate;

        public BookingDTOBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public BookingDTOBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public BookingDTOBuilder roomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public BookingDTOBuilder bookingFrom(String bookingFrom) {
            this.bookingFrom = bookingFrom;
            return this;
        }

        public BookingDTOBuilder bookingTo(String bookingTo) {
            this.bookingTo = bookingTo;
            return this;
        }

        public BookingDTOBuilder bookingDays(String bookingDays) {
            this.bookingDays = bookingDays;
            return this;
        }

        public BookingDTOBuilder bookingCost(String bookingCost) {
            this.bookingCost = bookingCost;
            return this;
        }

        public BookingDTOBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public BookingDTOBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public BookingDTOBuilder bookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public BookingDTO build() {
            return new BookingDTO(this);
        }
    }

    public String getClientName() {
        return clientName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBookingFrom() {
        return bookingFrom;
    }

    public String getBookingTo() {
        return bookingTo;
    }

    public String getBookingDays() {
        return bookingDays;
    }

    public String getBookingCost() {
        return bookingCost;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "clientName='" + clientName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", roomId=" + roomId +
                ", bookingFrom=" + bookingFrom +
                ", bookingTo=" + bookingTo +
                ", bookingDays=" + bookingDays +
                ", bookingCost=" + bookingCost +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
