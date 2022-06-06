package com.mirror.concertsapi.infrastructure.ticketmasterdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @JsonProperty("name")
    private String name;
    @JsonProperty("dates")
    private Dates dates;
    @JsonProperty("_embedded")
    private Embedded__1 embedded;
}