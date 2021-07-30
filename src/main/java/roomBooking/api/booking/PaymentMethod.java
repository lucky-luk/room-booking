package roomBooking.api.booking;

public enum PaymentMethod {

    CASH("cash"),
    CREDIT_CARD("credit card"),
    BANK_TRANSFER("bank transfer"),
    BLIK("blik");

    private String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }
}
