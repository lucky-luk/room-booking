package roomBooking.api.host;

import roomBooking.api.property.Property;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hosts")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "nip_number", length = 10)
    private String nipNumber;

    @Column(name = "regon_number", length = 9)
    private String regonNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host")
    private List<Property> properties = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    public void setNipNumber(String nipNumber) {
        this.nipNumber = nipNumber;
    }

    public String getRegonNumber() {
        return regonNumber;
    }

    public void setRegonNumber(String regonNumber) {
        this.regonNumber = regonNumber;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", nipNumber='" + nipNumber + '\'' +
                ", regonNumber='" + regonNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", properties=" + properties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(id, host.id) && Objects.equals(companyName, host.companyName) && Objects.equals(nipNumber, host.nipNumber) && Objects.equals(regonNumber, host.regonNumber) && Objects.equals(email, host.email) && Objects.equals(phoneNumber, host.phoneNumber) && Objects.equals(registrationDate, host.registrationDate) && Objects.equals(properties, host.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, nipNumber, regonNumber, email, phoneNumber, registrationDate, properties);
    }
}
