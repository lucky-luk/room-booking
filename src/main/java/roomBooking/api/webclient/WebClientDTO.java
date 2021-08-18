package roomBooking.api.webclient;

public class WebClientDTO {

    private String table;
    private String currency;
    private String code;
    private Rates[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Rates[] getRates() {
        return rates;
    }

    public void setRates(Rates[] rates) {
        this.rates = rates;
    }
}
