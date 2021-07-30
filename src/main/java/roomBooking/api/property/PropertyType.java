package roomBooking.api.property;

public enum PropertyType {

    HOTEL("hotel"),
    HOSTEL("hostel"),
    VILLA("villa"),
    APARTMENT("apartment"),
    FLAT("flat");

    private String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }
}
