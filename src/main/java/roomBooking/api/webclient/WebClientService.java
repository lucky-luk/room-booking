package roomBooking.api.webclient;

import org.springframework.stereotype.Service;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClientDTO getExchangeRate(String currency) {
        return webClient.getExchangeRateForCurrency(currency);
    }

    public Double getAskRate(String currency) {
        Rates[] rates = getExchangeRate(currency).getRates();
        return rates[0].getAsk();
    }
}
