package com.mirror.concertsapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Concert {
    private String band;
    private String city;
    private String venue;
    private LocalDate date;
}
