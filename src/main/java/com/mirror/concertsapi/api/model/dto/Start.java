package com.mirror.concertsapi.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Start {

    @JsonProperty("localDate")
    private String localDate;
    @JsonProperty("dateTime")
    private String dateTime;
}