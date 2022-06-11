package com.mirror.concertsapi.infrastructure.ticketmaster;

import com.mirror.concertsapi.application.usecases.ConcertsFetcher;
import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.helpers.ConcertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("file:${app.home}/config.properties")
public class TicketmasterConcertsFetcher implements ConcertsFetcher {
    private static final Logger LOGGER = Logger.getLogger(ConcertsFetcher.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ticketmaster.base.url}")
    private String ticketmasterUrl;

    @Value("${ticketmaster.api.key}")
    private String ticketmasterKey;

    public List<Concert> getConcertsInArea(String latitude, String longitude) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(ticketmasterUrl)
                .queryParam("category_ids", "10001")
                .queryParam("lat", latitude)
                .queryParam("long", longitude)
                .queryParam("radius", "100")
                .queryParam("rows", "250")
                .queryParam("apikey", ticketmasterKey)
                .encode()
                .toUriString();

        ResponseEntity<TicketmasterEntity.TicketmasterDTO> response = restTemplate.getForEntity(urlTemplate, TicketmasterEntity.TicketmasterDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ConcertMapper.ticketmasterDtoToConcertMapper(Objects.requireNonNull(response.getBody()));
        }

        LOGGER.warning("Failed to get concerts in selected area, error code: " + response.getStatusCodeValue());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
