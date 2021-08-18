package roomBooking.api.property;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "sleeping_places", nullable = false)
    private int sleepingPlaces;

    @Column(name = "single_bed", nullable = false)
    private boolean singleBed;

    @Column(name = "double_bed", nullable = false)
    private boolean doubleBed;

    @Column(name = "tv_in_room", nullable = false)
    private boolean tvInRoom;

    @Column(name = "private_kitche", nullable = false)
    private boolean privateKitchen;

    @Column(name = "wifi", nullable = false)
    private boolean wifi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getSleepingPlaces() {
        return sleepingPlaces;
    }

    public void setSleepingPlaces(int sleepingPlaces) {
        this.sleepingPlaces = sleepingPlaces;
    }

    public boolean isSingleBed() {
        return singleBed;
    }

    public void setSingleBed(boolean singleBed) {
        this.singleBed = singleBed;
    }

    public boolean isDoubleBed() {
        return doubleBed;
    }

    public void setDoubleBed(boolean doubleBed) {
        this.doubleBed = doubleBed;
    }

    public boolean isTvInRoom() {
        return tvInRoom;
    }

    public void setTvInRoom(boolean tvInRoom) {
        this.tvInRoom = tvInRoom;
    }

    public boolean isPrivateKitchen() {
        return privateKitchen;
    }

    public void setPrivateKitchen(boolean privateKitchen) {
        this.privateKitchen = privateKitchen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", property=" + property +
                ", pricePerNight=" + pricePerNight +
                ", currency=" + currency +
                ", sleepingPlaces=" + sleepingPlaces +
                ", singleBed=" + singleBed +
                ", doubleBed=" + doubleBed +
                ", tvInRoom=" + tvInRoom +
                ", privateKitchen=" + privateKitchen +
                ", wifi=" + wifi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return sleepingPlaces == room.sleepingPlaces && singleBed == room.singleBed
                && doubleBed == room.doubleBed && tvInRoom == room.tvInRoom
                && privateKitchen == room.privateKitchen && wifi == room.wifi
                && Objects.equals(id, room.id) && Objects.equals(property, room.property)
                && Objects.equals(pricePerNight, room.pricePerNight) && currency == room.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property, pricePerNight, currency, sleepingPlaces, singleBed, doubleBed,
                tvInRoom, privateKitchen, wifi);
    }

    public boolean isAnyRoomFieldNull() {
        return Stream.of(pricePerNight, sleepingPlaces, singleBed,
                doubleBed, tvInRoom, privateKitchen, wifi).anyMatch(Objects::isNull);
    }
}
