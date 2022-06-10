package com.mirror.concertsapi.infrastructure.lastfm;

import com.mirror.concertsapi.application.usecases.BandsFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class LastfmBandsFetcher implements BandsFetcher {
    private static final Logger LOGGER = Logger.getLogger(BandsFetcher.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${lastfm.base.url}")
    private String lastfmUrl;


    public List<String> getFavoriteBands(String user, String key, int limit) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(lastfmUrl)
                .queryParam("method", "user.gettopartists")
                .queryParam("period", "12month")
                .queryParam("user", user)
                .queryParam("api_key", key)
                .queryParam("format", "json")
                .queryParam("limit", limit)
                .encode()
                .toUriString();

        ResponseEntity<LastfmEntity.LastfmDTO> response = restTemplate.getForEntity(urlTemplate, LastfmEntity.LastfmDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(response.getBody()).topartists().artist().stream().map(LastfmEntity.Artist::name).toList();
        }

        LOGGER.warning("Failed to get favorite bands, error code: " + response.getStatusCodeValue());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}