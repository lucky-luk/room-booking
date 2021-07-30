package roomBooking.api.booking;

import roomBooking.api.client.Client;
import roomBooking.api.property.Currency;
import roomBooking.api.property.Property;
import roomBooking.api.property.Room;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "booking_from", nullable = false)
    private Date bookingFrom;

    @Column(name = "booking_to", nullable = false)
    private Date bookingTo;

    @Formula(value = "DATEDIFF(booking_to, booking_from")
    private int bookingDays;

    @Column(name = "booking_cost")
    private BigDecimal bookingCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getBookingFrom() {
        return bookingFrom;
    }

    public void setBookingFrom(Date bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    public Date getBookingTo() {
        return bookingTo;
    }

    public void setBookingTo(Date bookingTo) {
        this.bookingTo = bookingTo;
    }

    public int getBookingDays() {
        return bookingDays;
    }

    public void setBookingDays(int bookingDays) {
        this.bookingDays = bookingDays;
    }

    public BigDecimal getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(BigDecimal bookingCost) {
        this.bookingCost = bookingCost;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", client=" + client +
                ", property=" + property +
                ", room=" + room +
                ", bookingFrom=" + bookingFrom +
                ", bookingTo=" + bookingTo +
                ", bookingDays=" + bookingDays +
                ", bookingCost=" + bookingCost +
                ", currency=" + currency +
                ", paymentMethod=" + paymentMethod +
                ", bookingDate=" + bookingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingDays == booking.bookingDays && Objects.equals(id, booking.id) && Objects.equals(client, booking.client) && Objects.equals(property, booking.property) && Objects.equals(room, booking.room) && Objects.equals(bookingFrom, booking.bookingFrom) && Objects.equals(bookingTo, booking.bookingTo) && Objects.equals(bookingCost, booking.bookingCost) && currency == booking.currency && paymentMethod == booking.paymentMethod && Objects.equals(bookingDate, booking.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, property, room, bookingFrom, bookingTo, bookingDays, bookingCost, currency, paymentMethod, bookingDate);
    }
}
