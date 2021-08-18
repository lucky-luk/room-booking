package roomBooking.api.property;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDTO {

    @JsonProperty("property_name")
    private String property;

    @JsonProperty(value = "price_per_night")
    private String pricePerNight;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "sleeping_places")
    private String sleepingPlaces;

    @JsonProperty(value = "sinle_bed")
    private String singleBed;

    @JsonProperty(value = "double_bed")
    private String doubleBed;

    @JsonProperty(value = "tv_in_room")
    private String tvInRoom;

    @JsonProperty(value = "private_kitche")
    private String privateKitchen;

    @JsonProperty(value = "wifi")
    private String wifi;

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
    }

    public static RoomDTOBuilder builder() {
        return new RoomDTOBuilder();
    }

    public static class RoomDTOBuilder {
        private String property;
        private String pricePerNight;
        private String currency;
        private String sleepingPlaces;
        private String singleBed;
        private String doubleBed;
        private String tvInRoom;
        private String privateKitchen;
        private String wifi;

        public RoomDTOBuilder property(String property) {
            this.property = property;
            return this;
        }

        public RoomDTOBuilder pricePerNight(String pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public RoomDTOBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public RoomDTOBuilder sleepingPlaces(String sleepingPlaces) {
            this.sleepingPlaces = sleepingPlaces;
            return this;
        }

        public RoomDTOBuilder singleBed(String singleBed) {
            this.singleBed = singleBed;
            return this;
        }

        public RoomDTOBuilder doubleBed(String doubleBed) {
            this.doubleBed = doubleBed;
            return this;
        }

        public RoomDTOBuilder tvInRoom(String tvInRoom) {
            this.tvInRoom = tvInRoom;
            return this;
        }

        public RoomDTOBuilder privateKitchen(String privateKitchen) {
            this.privateKitchen = privateKitchen;
            return this;
        }

        public RoomDTOBuilder wifi(String wifi) {
            this.wifi = wifi;
            return this;
        }

        public RoomDTO build() {
            return new RoomDTO(this);
        }
    }

    public String getProperty() {
        return property;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSleepingPlaces() {
        return sleepingPlaces;
    }

    public String isSingleBed() {
        return singleBed;
    }

    public String isDoubleBed() {
        return doubleBed;
    }

    public String isTvInRoom() {
        return tvInRoom;
    }

    public String isPrivateKitchen() {
        return privateKitchen;
    }

    public String isWifi() {
        return wifi;
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
                '}';
    }
}
