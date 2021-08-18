package roomBooking.api.property;

public enum Currency {

    PLN("PLN"),
    EUR("EUR"),
    GBP("GBP"),
    USD("USD");

    private String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }
}
