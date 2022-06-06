package com.mirror.concertsapi.api.service;

import com.mirror.concertsapi.api.model.Concert;

import java.util.List;

public interface ConcertService {
    List<Concert> getConcerts(String band, String geoPoint, String radius);
}
