package demo.fls.eshop.storefront;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/currency-rates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyRateController {
    @GetMapping("/{code}")
    public ResponseEntity<String> get(@PathVariable String code) {
        RestTemplate restTemplate = new RestTemplate();

        String forexUrl = "https://v2.api.forex/rates/latest.json?key=demo&to="+code;
        ResponseEntity<String> response = restTemplate.getForEntity(forexUrl, String.class);

        return response;
    }
}