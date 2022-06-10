package com.mirror.concertsapi.application.service;

import com.mirror.concertsapi.application.usecases.BandsFetcher;
import com.mirror.concertsapi.application.usecases.ConcertsFetcher;
import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import com.mirror.concertsapi.infrastructure.restconfig.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ConcertServiceImpl implements ConcertService {
    private static final Logger LOGGER = Logger.getLogger(ConcertServiceImpl.class.getName());

    @Autowired
    private BandsFetcher bandsFetcher;

    @Autowired
    private ConcertsFetcher concertsFetcher;

    @Override
    @Cacheable(value = CacheConfig.CONCERTS_CACHE, key = "#root.method.name")
    public List<Concert> getConcerts(String lastfmUser, String lastfmKey, int bandsLimit, String latitude, String longitude) {
        List<String> bands = bandsFetcher.getFavoriteBands(lastfmUser, lastfmKey, bandsLimit);

        return concertsFetcher.getConcertsInArea(latitude, longitude).stream()
                .filter(c -> bands.contains(c.getBand()))
                .distinct()
                .sorted(Comparator.comparing(Concert::getDate))
                .toList();
    }
}