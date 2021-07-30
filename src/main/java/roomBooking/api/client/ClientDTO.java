package roomBooking.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import roomBooking.api.booking.BookingDTO;

import java.util.Date;
import java.util.List;

public class ClientDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @JsonProperty(value = "nationality")
    private String nationality;

    @JsonProperty(value = "registration_date")
    private Date registrationDate;

    @JsonProperty(value = "bookings")
    private List<BookingDTO> bookings;

    private ClientDTO(ClientDTOBuilder b) {
        this.name = b.name;
        this.lastName = b.lastName;
        this.email = b.email;
        this.phoneNumber = b.phoneNumber;
        this.nationality = b.nationality;
        this.registrationDate = b.registrationDate;
        this.bookings = b.bookings;
    }

    public static ClientDTOBuilder builder() {
        return new ClientDTOBuilder();
    }

    public static class ClientDTOBuilder {
        private String name;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String nationality;
        private Date registrationDate;
        private List<BookingDTO> bookings;

        public ClientDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClientDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ClientDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientDTOBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ClientDTOBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public ClientDTOBuilder registrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public ClientDTOBuilder bookings(List<BookingDTO> bookings) {
            this.bookings = bookings;
            return this;
        }

        public ClientDTO build() {
            return new ClientDTO(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", registrationDate=" + registrationDate +
                ", bookings=" + bookings +
                '}';
    }
}
