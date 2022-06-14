package com.mirror.concertsapi.application.service;

import com.mirror.concertsapi.application.usecases.BandsFetcher;
import com.mirror.concertsapi.application.usecases.ConcertsFetcher;
import com.mirror.concertsapi.domain.Concert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConcertServiceImplTest {

    @MockBean
    private BandsFetcher bandsFetcher;

    @MockBean
    private ConcertsFetcher concertsFetcher;

    @Autowired
    private ConcertServiceImpl concertService;

    @Test
    @DisplayName("Concerts are fetched and cross referenced against bands")
    public void concertsAreFilteredAndFetchedCorrectly() {
        //Given the bands and concerts lists
        Mockito.when(bandsFetcher.getFavoriteBands(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Arrays.asList("Tove Styrke", "The Proclaimers", "Molotow Soda", "The Zen Circus"));

        Mockito.when(concertsFetcher.getConcertsInArea(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Arrays.asList(
                        new Concert("The Zen Circus", "Torino", "CAP10100", "2020-06-30"),
                        new Concert("Tove Styrke", "Perpignan", "Aimé Giral", "2021-05-30"),
                        new Concert("Akli D", "Berlin", "SO 36", "2022-04-30"),
                        new Concert("Renaud", "London", "Belsize Park", "2023-06-30"),
                        new Concert("The Velvet Undergoround", "NY", "CBGB", "2024-06-30"),
                        new Concert("The Proclaimers", "LA", "Hollywood Bowl", "2025-06-20"),
                        new Concert("Molotow Soda", "Barcelona", "Mariachi", "2026-07-20")));

        //When request is triggered
        List<Concert> concerts = concertService.getConcerts(new String[]{"fakeUser"}, "fakeKey", 100, new String[]{"latitude"}, new String[]{"longitude"});

        //Then the response contains the expected concert objects
        Assertions.assertNotNull(concerts);
        Assertions.assertEquals(4, concerts.size());
        Assertions.assertEquals("The Zen Circus", concerts.get(0).getArtist());
        Assertions.assertEquals("Aimé Giral", concerts.get(1).getVenue());
        Assertions.assertEquals("2025-06-20", concerts.get(2).getDate());
        Assertions.assertEquals("Barcelona", concerts.get(3).getCity());
    }
}