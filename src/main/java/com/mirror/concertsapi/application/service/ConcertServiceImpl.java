package com.mirror.concertsapi.application.service;

import com.mirror.concertsapi.application.usecases.BandsFetcher;
import com.mirror.concertsapi.application.usecases.ConcertsFetcher;
import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ConcertServiceImpl implements ConcertService {
    private static final Logger LOGGER = Logger.getLogger(ConcertServiceImpl.class.getName());

    @Autowired
    private BandsFetcher bandsFetcher;

    @Autowired
    private ConcertsFetcher concertsFetcher;

    @Override
    public List<Concert> getConcerts(String[] lastfmUser, String lastfmKey, int bandsLimit, String[] latitude, String[] longitude) {

        Set<String> bands = Arrays.stream(lastfmUser)
                .map(user -> bandsFetcher.getFavoriteBands(user, lastfmKey, bandsLimit))
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return IntStream.range(0, latitude.length)
                .mapToObj(i -> concertsFetcher.getConcertsInArea(latitude[i], longitude[i]))
                .flatMap(List::stream)
                .filter(c -> bands.contains(c.getArtist()))
                .distinct()
                .sorted(Comparator.comparing(Concert::getDate))
                .toList();
    }
}