package roomBooking.api.property;

import roomBooking.api.comment.Comment;
import roomBooking.api.host.Host;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;

    @Column(name = "number_of_rooms", nullable = false)
    private int numberOfRooms;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "parking_available", nullable = false)
    private boolean parkingAvailable;

    @Column(name = "restaurant_available", nullable = false)
    private boolean restaurantAvailable;

    @Column(name = "swimming_pool_available", nullable = false)
    private boolean swimmingPoolAvailable;

    @Column(name = "pets_allowed", nullable = false)
    private boolean petsAllowed;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public boolean isRestaurantAvailable() {
        return restaurantAvailable;
    }

    public void setRestaurantAvailable(boolean restaurantAvailable) {
        this.restaurantAvailable = restaurantAvailable;
    }

    public boolean isSwimmingPoolAvailable() {
        return swimmingPoolAvailable;
    }

    public void setSwimmingPoolAvailable(boolean swimmingPoolAvailable) {
        this.swimmingPoolAvailable = swimmingPoolAvailable;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", propertyType=" + propertyType +
                ", numberOfRooms=" + numberOfRooms +
                ", host=" + host +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return numberOfRooms == property.numberOfRooms && parkingAvailable == property.parkingAvailable && restaurantAvailable == property.restaurantAvailable && swimmingPoolAvailable == property.swimmingPoolAvailable && petsAllowed == property.petsAllowed && Objects.equals(id, property.id) && Objects.equals(propertyName, property.propertyName) && propertyType == property.propertyType && Objects.equals(host, property.host) && Objects.equals(address, property.address) && Objects.equals(description, property.description) && Objects.equals(rooms, property.rooms) && Objects.equals(comments, property.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyName, propertyType, numberOfRooms, host, address, parkingAvailable, restaurantAvailable, swimmingPoolAvailable, petsAllowed, description, rooms, comments);
    }
}
