package roomBooking.api.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RoomDTO {

    @JsonProperty("property_name")
    private String property;

    @JsonProperty(value = "price_per_night")
    private BigDecimal pricePerNight;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "sleeping_places")
    private int sleepingPlaces;

    @JsonProperty(value = "sinle_bed")
    private boolean singleBed;

    @JsonProperty(value = "double_bed")
    private boolean doubleBed;

    @JsonProperty(value = "tv_in_room")
    private boolean tvInRoom;

    @JsonProperty(value = "private_kitche")
    private boolean privateKitchen;

    @JsonProperty(value = "wifi")
    private boolean wifi;

    @JsonProperty(value = "is_available")
    private Boolean isAvailable;

    public RoomDTO(RoomDTOBuilder b) {
        this.property = b.property;
        this.pricePerNight = b.pricePerNight;
        this.currency = b.currency;
        this.sleepingPlaces = b.sleepingPlaces;
        this.singleBed = b.singleBed;
        this.doubleBed = b.doubleBed;
        this.tvInRoom = b.tvInRoom;
        this.privateKitchen = b.privateKitchen;
        this.wifi = b.wifi;
        this.isAvailable = b.isAvailable;
    }

    public static RoomDTOBuilder builder() {
        return new RoomDTOBuilder();
    }

    public static class RoomDTOBuilder {
        private String property;
        private BigDecimal pricePerNight;
        private String currency;
        private int sleepingPlaces;
        private boolean singleBed;
        private boolean doubleBed;
        private boolean tvInRoom;
        private boolean privateKitchen;
        private boolean wifi;
        private Boolean isAvailable;

        public RoomDTOBuilder property(String property) {
            this.property = property;
            return this;
        }

        public RoomDTOBuilder pricePerNight(BigDecimal pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public RoomDTOBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public RoomDTOBuilder sleepingPlaces(int sleepingPlaces) {
            this.sleepingPlaces = sleepingPlaces;
            return this;
        }

        public RoomDTOBuilder singleBed(boolean singleBed) {
            this.singleBed = singleBed;
            return this;
        }

        public RoomDTOBuilder doubleBed(boolean doubleBed) {
            this.doubleBed = doubleBed;
            return this;
        }

        public RoomDTOBuilder tvInRoom(boolean tvInRoom) {
            this.tvInRoom = tvInRoom;
            return this;
        }

        public RoomDTOBuilder privateKitchen(boolean privateKitchen) {
            this.privateKitchen = privateKitchen;
            return this;
        }

        public RoomDTOBuilder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public RoomDTOBuilder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public RoomDTO build() {
            return new RoomDTO(this);
        }
    }

    public String getProperty() {
        return property;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public String getCurrency() {
        return currency;
    }

    public int getSleepingPlaces() {
        return sleepingPlaces;
    }

    public boolean isSingleBed() {
        return singleBed;
    }

    public boolean isDoubleBed() {
        return doubleBed;
    }

    public boolean isTvInRoom() {
        return tvInRoom;
    }

    public boolean isPrivateKitchen() {
        return privateKitchen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "property='" + property + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", currency='" + currency + '\'' +
                ", sleepingPlaces=" + sleepingPlaces +
                ", singleBed=" + singleBed +
                ", doubleBed=" + doubleBed +
                ", tvInRoom=" + tvInRoom +
                ", privateKitchen=" + privateKitchen +
                ", wifi=" + wifi +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
