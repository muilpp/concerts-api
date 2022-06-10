package com.mirror.concertsapi.application.service;

import com.mirror.concertsapi.application.usecases.BandsFetcher;
import com.mirror.concertsapi.application.usecases.ConcertsFetcher;
import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import com.mirror.concertsapi.infrastructure.restconfig.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {
    private static final Logger LOGGER = Logger.getLogger(ConcertServiceImpl.class.getName());

    @Autowired
    private BandsFetcher bandsFetcher;

    @Autowired
    private ConcertsFetcher concertsFetcher;

    @Override
    @Cacheable(value = CacheConfig.CONCERTS_CACHE, key = "#root.method.name")
    public Set<Concert> getConcerts(String lastfmUser, String lastfmKey, int bandsLimit, String geoPoint, String radius) {
        List<String> bands = bandsFetcher.getFavoriteBands(lastfmUser, lastfmKey, bandsLimit);

        return concertsFetcher.getConcertsInArea().stream()
                .filter(c -> bands.contains(c.getBand()))
                .collect(Collectors.toSet());
    }
}