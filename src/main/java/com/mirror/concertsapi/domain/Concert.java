package com.mirror.concertsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Concert {
    private String artist;
    private String city;
    private String venue;
    private String date;
}
