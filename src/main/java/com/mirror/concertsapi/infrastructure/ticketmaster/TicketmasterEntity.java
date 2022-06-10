package com.mirror.concertsapi.infrastructure.ticketmaster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public interface TicketmasterEntity {
    @JsonIgnoreProperties(ignoreUnknown = true)
    record TicketmasterDTO(List<Event> events) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Event(List<Attraction> attractions, Venue venue, LocalEventDate local_event_date) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record LocalEventDate(@JsonInclude(JsonInclude.Include.NON_NULL) String value) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Venue(String name, Location location) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Attraction(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Location(Address address) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Address(String city) {}
}