package ods.raidplanner.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ping")
public class PingController {

    @Value("${frontend.ping.url}")
    private String frontendPingUrl;

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCharacterRoles() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(frontendPingUrl, String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
