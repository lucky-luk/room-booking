package roomBooking.api.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import roomBooking.api.comment.CommentDTO;

import java.util.List;

public class PropertyDTO {

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "property_type")
    private String propertyType;

    @JsonProperty(value = "number_of_rooms")
    private int numberOfRooms;

    @JsonProperty(value = "host_name")
    private String host;

    @JsonProperty(value = "address")
    private AddressDTO address;

    @JsonProperty(value = "parking_available")
    private boolean parkingAvailable;

    @JsonProperty(value = "restaurant_available")
    private boolean restaurantAvailable;

    @JsonProperty(value = "swimming_pool_available")
    private boolean swimmingPoolAvailable;

    @JsonProperty("pets_allowed")
    private boolean petsAllowed;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty("rooms")
    private List<RoomDTO> rooms;

    @JsonProperty(value = "comments")
    private List<CommentDTO> comments;

    public PropertyDTO(PropertyDTOBuilder b) {
        this.propertyName = b.propertyName;
        this.propertyType = b.propertyType;
        this.numberOfRooms = b.numberOfRooms;
        this.host = b.host;
        this.address = b.address;
        this.parkingAvailable = b.parkingAvailable;
        this.restaurantAvailable = b.restaurantAvailable;
        this.swimmingPoolAvailable = b.swimmingPoolAvailable;
        this.petsAllowed = b.petsAllowed;
        this.description = b.description;
        this.rooms = b.rooms;
        this.comments = b.comments;
    }

    public static PropertyDTOBuilder builder() {
        return new PropertyDTOBuilder();
    }

    public static class PropertyDTOBuilder {
        private String propertyName;
        private String propertyType;
        private int numberOfRooms;
        private String host;
        private AddressDTO address;
        private boolean parkingAvailable;
        private boolean restaurantAvailable;
        private boolean swimmingPoolAvailable;
        private boolean petsAllowed;
        private String description;
        private List<RoomDTO> rooms;
        private List<CommentDTO> comments;

        public PropertyDTOBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public PropertyDTOBuilder propertyType(String propertyType) {
            this.propertyType = propertyType;
            return this;
        }

        public PropertyDTOBuilder numberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public PropertyDTOBuilder host(String host) {
            this.host = host;
            return this;
        }

        public PropertyDTOBuilder address(AddressDTO address) {
            this.address = address;
            return this;
        }

        public PropertyDTOBuilder parkingAvailable(boolean parkingAvailable) {
            this.parkingAvailable = parkingAvailable;
            return this;
        }

        public PropertyDTOBuilder restaurantAvailable(boolean restaurantAvailable) {
            this.restaurantAvailable = restaurantAvailable;
            return this;
        }

        public PropertyDTOBuilder swimmingPoolAvailable(boolean swimmingPoolAvailable) {
            this.swimmingPoolAvailable = swimmingPoolAvailable;
            return this;
        }

        public PropertyDTOBuilder petsAllowed(boolean petsAllowed) {
            this.petsAllowed = petsAllowed;
            return this;
        }

        public PropertyDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PropertyDTOBuilder rooms(List<RoomDTO> rooms) {
            this.rooms = rooms;
            return this;
        }

        public PropertyDTOBuilder comments(List<CommentDTO> comments) {
            this.comments = comments;
            return this;
        }

        public PropertyDTO build() {
            return new PropertyDTO(this);
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getHost() {
        return host;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public boolean isRestaurantAvailable() {
        return restaurantAvailable;
    }

    public boolean isSwimmingPoolAvailable() {
        return swimmingPoolAvailable;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public String getDescription() {
        return description;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
                "propertyName='" + propertyName + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", host='" + host + '\'' +
                ", address=" + address +
                ", parkingAvailable=" + parkingAvailable +
                ", restaurantAvailable=" + restaurantAvailable +
                ", swimmingPoolAvailable=" + swimmingPoolAvailable +
                ", petsAllowed=" + petsAllowed +
                ", description='" + description + '\'' +
                ", rooms=" + rooms +
                ", comments=" + comments +
                '}';
    }
}
