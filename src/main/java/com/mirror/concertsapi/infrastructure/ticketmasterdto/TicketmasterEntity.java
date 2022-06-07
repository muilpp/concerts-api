package com.mirror.concertsapi.infrastructure.ticketmasterdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public interface TicketmasterEntity {
    @JsonIgnoreProperties(ignoreUnknown = true)
    record TicketmasterDTO(Embedded _embedded) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Embedded(List<Event> events) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Event(String name, Dates dates, Embedded__1 _embedded) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Dates(Start start) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Start(String localDate, String dateTime) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Embedded__1(List<Venue> venues){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Venue(String name, City city) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record City(String name) {}
}
