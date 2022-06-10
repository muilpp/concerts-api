package com.mirror.concertsapi.domain;

import java.util.Set;

public interface ConcertService {
    Set<Concert> getConcerts(String lastfmUser, String lastfmKey, int bandsLimit, String geoPoint, String radius);
}
