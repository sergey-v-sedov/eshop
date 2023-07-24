package demo.fls.eshop.storefront;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/currency-rates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyRateController {
    @GetMapping()
    public ResponseEntity<String> get(@RequestParam("provider") String ratesProviderUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(ratesProviderUrl, String.class);

        return response;
    }
}