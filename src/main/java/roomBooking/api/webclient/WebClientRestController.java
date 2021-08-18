package roomBooking.api.webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WebClientRestController {

    private final WebClientService webClientService;

    public WebClientRestController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping("/currency")
    public WebClientDTO getExchangeRate(@RequestParam String currency) {
        return webClientService.getExchangeRate(currency);
    }

    @GetMapping("/ask")
    public Double getAsk(@RequestParam String currency) {
        return webClientService.getAskRate(currency);
    }
}
