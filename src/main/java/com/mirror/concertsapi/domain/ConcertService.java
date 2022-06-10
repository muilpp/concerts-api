package com.mirror.concertsapi.domain;

import java.util.List;

public interface ConcertService {
    List<Concert> getConcerts(String lastfmUser, String lastfmKey, int bandsLimit, String latitude, String longitude);
}
