package com.mirror.concertsapi.api.service;

import com.mirror.concertsapi.api.config.CacheConfig;
import com.mirror.concertsapi.api.model.Concert;
import com.mirror.concertsapi.api.model.dto.TicketmasterDTO;
import com.mirror.concertsapi.api.utils.ConcertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ConcertServiceImpl implements ConcertService {
    private static final Logger LOGGER = Logger.getLogger(ConcertServiceImpl.class.getName());
    @Autowired
    RestTemplate restTemplate;

    @Value("${ticketmaster.base.url}")
    private String ticketmasterUrl;

    @Value("${ticketmaster.api.key}")
    private String ticketmasterKey;

    @Override
    @Cacheable(value = CacheConfig.CONCERTS_CACHE, key = "#root.method.name")
    public List<Concert> getConcerts(String band, String geoPoint, String radius) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(ticketmasterUrl)
                .queryParam("keyword", band)
                .queryParam("resource", "events")
                .queryParam("geoPoint", geoPoint)
                .queryParam("radius", radius)
                .queryParam("apikey", ticketmasterKey)
                .encode()
                .toUriString();

        ResponseEntity<TicketmasterDTO> response = restTemplate.getForEntity(urlTemplate, TicketmasterDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ConcertMapper.TicketmasterResponseToConcertMapper(response.getBody());
        }

        LOGGER.warning("Failed to get data elements elements groups, error code: " + response.getStatusCodeValue());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}