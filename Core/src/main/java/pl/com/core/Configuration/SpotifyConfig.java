package pl.com.core.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Configuration
public class SpotifyConfig {

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    @Value("${spotify.token-url:https://accounts.spotify.com/api/token}")
    private String tokenUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String getAccessToken(RestTemplate restTemplate) {
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedCredentials);

        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        return (String) response.getBody().get("access_token");
    }
}
