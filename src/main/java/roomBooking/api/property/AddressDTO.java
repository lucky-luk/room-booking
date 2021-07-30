package roomBooking.api.property;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {

    @JsonProperty(value = "property_name")
    private String property;

    @JsonProperty(value = "cuntry")
    private String country;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "postal_code")
    private String postalCode;

    @JsonProperty(value = "street_name")
    private String street;

    @JsonProperty(value = "house_number")
    private String houseNumber;

    public AddressDTO(AddressDTOBuilder b) {
        this.property = b.property;
        this.country = b.country;
        this.city = b.city;
        this.postalCode = b.postalCode;
        this.street = b.street;
        this.houseNumber = b.houseNumber;
    }

    public static AddressDTOBuilder builder() {
        return new AddressDTOBuilder();
    }

    public static class AddressDTOBuilder {
        private String property;
        private String country;
        private String city;
        private String postalCode;
        private String street;
        private String houseNumber;

        public AddressDTOBuilder property(String property) {
            this.property = property;
            return this;
        }

        public AddressDTOBuilder country(String country) {
            this.country = country;
            return this;
        }

        public AddressDTOBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressDTOBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressDTOBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressDTOBuilder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressDTO build() {
            return new AddressDTO(this);
        }
    }

    public String getProperty() {
        return property;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "property=" + property +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
