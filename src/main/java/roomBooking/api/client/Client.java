package roomBooking.api.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import roomBooking.api.booking.Booking;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "registration_date")
    @JsonIgnore
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", registrationDate=" + registrationDate +
                ", bookings=" + bookings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name)
                && Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email)
                && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(nationality, client.nationality)
                && Objects.equals(registrationDate, client.registrationDate)
                && Objects.equals(bookings, client.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, phoneNumber, nationality, registrationDate, bookings);
    }

    public boolean isAnyClientFieldNull() {
        return Stream.of(name, lastName, email, phoneNumber, nationality).anyMatch(Objects::isNull);
    }
}
