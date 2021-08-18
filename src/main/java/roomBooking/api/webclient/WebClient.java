package roomBooking.api.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebClient {

    private final RestTemplate restTemplate;
    public static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/c/";

    public WebClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WebClientDTO getExchangeRateForCurrency(String currency) {
        return restTemplate.getForObject(NBP_URL + "{currency}/", WebClientDTO.class, currency);
    }
}
