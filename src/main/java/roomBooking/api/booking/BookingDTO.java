package roomBooking.api.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BookingDTO {

    @JsonProperty(value = "client_name")
    private String clientName;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "room_id")
    private Long roomId;

    @JsonProperty(value = "booking_from")
    private Date bookingFrom;

    @JsonProperty(value = "booking_to")
    private Date bookingTo;

    @JsonProperty(value = "booking_days")
    private int bookingDays;

    @JsonProperty(value = "booking_cost")
    private BigDecimal bookingCost;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "payment_method")
    private String paymentMethod;

    @JsonProperty(value = "booking_date")
    private Date bookingDate;

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
        private Long roomId;
        private Date bookingFrom;
        private Date bookingTo;
        private int bookingDays;
        private BigDecimal bookingCost;
        private String currency;
        private String paymentMethod;
        private Date bookingDate;

        public BookingDTOBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public BookingDTOBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public BookingDTOBuilder roomId(Long roomId) {
            this.roomId = roomId;
            return this;
        }

        public BookingDTOBuilder bookingFrom(Date bookingFrom) {
            this.bookingFrom = bookingFrom;
            return this;
        }

        public BookingDTOBuilder bookingTo(Date bookingTo) {
            this.bookingTo = bookingTo;
            return this;
        }

        public BookingDTOBuilder bookingDays(int bookingDays) {
            this.bookingDays = bookingDays;
            return this;
        }

        public BookingDTOBuilder bookingCost(BigDecimal bookingCost) {
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

        public BookingDTOBuilder bookingDate(Date bookingDate) {
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

    public Long getRoomId() {
        return roomId;
    }

    public Date getBookingFrom() {
        return bookingFrom;
    }

    public Date getBookingTo() {
        return bookingTo;
    }

    public int getBookingDays() {
        return bookingDays;
    }

    public BigDecimal getBookingCost() {
        return bookingCost;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getBookingDate() {
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
