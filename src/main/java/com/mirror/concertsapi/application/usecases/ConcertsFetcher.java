package com.mirror.concertsapi.application.usecases;

import com.mirror.concertsapi.domain.Concert;

import java.util.List;

public interface ConcertsFetcher {
    List<Concert> getConcertsInArea();
}
