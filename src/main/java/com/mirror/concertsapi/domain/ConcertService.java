package com.mirror.concertsapi.domain;

import com.mirror.concertsapi.domain.Concert;

import java.util.List;

public interface ConcertService {
    List<Concert> getConcerts(String band, String geoPoint, String radius);
}
