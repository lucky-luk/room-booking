package roomBooking.api.host;

import com.fasterxml.jackson.annotation.JsonProperty;
import roomBooking.api.property.Property;

import java.util.Date;
import java.util.List;

public class HostDTO {

    @JsonProperty(value = "company_name")
    private String companyName;

    @JsonProperty(value = "nip_number")
    private String nipNumber;

    @JsonProperty(value = "regon_number")
    private String regonNumber;

    @JsonProperty(value = "email_address")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @JsonProperty(value = "registration_date")
    private Date registrationDate;

    @JsonProperty(value = "properties")
    private List<Property> properties;

    public HostDTO(HostDTOBuilder b) {
        this.companyName = b.companyName;
        this.nipNumber = b.nipNumber;
        this.regonNumber = b.regonNumber;
        this.email = b.email;
        this.phoneNumber = b.phoneNumber;
        this.registrationDate = b.registrationDate;
        this.properties = b.properties;
    }

    public static HostDTOBuilder builder() {
        return new HostDTOBuilder();
    }

    public static class HostDTOBuilder {
        private String companyName;
        private String nipNumber;
        private String regonNumber;
        private String email;
        private String phoneNumber;
        private Date registrationDate;
        private List<Property> properties;

        public HostDTOBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public HostDTOBuilder nipNumber(String nipNumber) {
            this.nipNumber = nipNumber;
            return this;
        }

        public HostDTOBuilder regonNumber(String regonNumber) {
            this.regonNumber = regonNumber;
            return this;
        }

        public HostDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public HostDTOBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public HostDTOBuilder registrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public HostDTOBuilder properties(List<Property> properties) {
            this.properties = properties;
            return this;
        }

        public HostDTO build() {
            return new HostDTO(this);
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    public String getRegonNumber() {
        return regonNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "HostDTO{" +
                "companyName='" + companyName + '\'' +
                ", nipNumber='" + nipNumber + '\'' +
                ", regonNumber='" + regonNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", properties=" + properties +
                '}';
    }
}
