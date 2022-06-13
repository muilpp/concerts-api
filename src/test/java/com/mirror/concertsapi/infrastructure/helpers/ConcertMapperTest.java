package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.ticketmaster.TicketmasterEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ConcertMapperTest {

    @Test
    @DisplayName("Ticketmaster concerts are mapped correctly")
    public void testTicketmasterConcertsAreMappedCorrectly() {
        TicketmasterEntity.TicketmasterDTO ticketmasterDTO = getTestConcerts();

        List<Concert> concerts = ConcertMapper.ticketmasterDtoToConcertMapper(ticketmasterDTO);

        Assert.notNull(concerts, "Concert list is null");
        Assert.noNullElements(concerts, "Concert list has null elements");
        Assertions.assertEquals(3, concerts.size());
        Assertions.assertEquals("Ulls Blaus", concerts.get(0).getVenue());
        Assertions.assertEquals("Berlin", concerts.get(1).getCity());
        Assertions.assertEquals(LocalDate.of(2021, 5, 20).toString(), concerts.get(0).getDate());
        Assertions.assertEquals(LocalDate.of(2022, 6, 21).toString(), concerts.get(1).getDate());
        Assertions.assertEquals(LocalDate.of(2023, 7, 22).toString(), concerts.get(2).getDate());
        Assertions.assertEquals("Woodstock Poland", concerts.get(2).getBand());
    }

    @Test
    @DisplayName("Ticketmaster festivals are mapped correctly")
    public void testTicketmasterFestivalsAreMappedCorrectly() {
        TicketmasterEntity.TicketmasterDTO ticketmasterDTO = getTestConcertsAndFestivals();

        List<Concert> concerts = ConcertMapper.ticketmasterDtoToConcertMapper(ticketmasterDTO);

        Assert.notNull(concerts, "Concert list is null");
        Assert.noNullElements(concerts, "Concert list has null elements");
        Assertions.assertEquals(6, concerts.size());
        Assertions.assertEquals("Ulls Blaus", concerts.get(0).getVenue());
        Assertions.assertEquals("Berlin", concerts.get(1).getCity());
        Assertions.assertEquals(LocalDate.of(2021, 5, 20).toString(), concerts.get(0).getDate());
        Assertions.assertEquals(LocalDate.of(2022, 6, 21).toString(), concerts.get(1).getDate());
        Assertions.assertEquals(LocalDate.of(2023, 7, 22).toString(), concerts.get(2).getDate());
        Assertions.assertEquals("Woodstock Poland", concerts.get(2).getBand());
        Assertions.assertEquals("Manu Chao", concerts.get(3).getBand());
        Assertions.assertEquals(LocalDate.of(2023, 7, 22).toString(), concerts.get(4).getDate());
        Assertions.assertEquals("Szczecin", concerts.get(5).getCity());
        Assertions.assertEquals("Lehendakaris Muertos", concerts.get(5).getBand());
    }

    @Test
    @DisplayName("Empty list returned if concerts object is null")
    public void emptyListReturnedIfNoConcertsFound() {
        TicketmasterEntity.TicketmasterDTO ticketmasterDTO = new TicketmasterEntity.TicketmasterDTO(null);

        List<Concert> concerts = ConcertMapper.ticketmasterDtoToConcertMapper(ticketmasterDTO);
        Assert.notNull(concerts, "Concerts list is null");
        Assert.noNullElements(concerts, "Concerts list has null elements");
        Assertions.assertEquals(0, concerts.size());
    }

    private static TicketmasterEntity.TicketmasterDTO getTestConcerts() {
        TicketmasterEntity.Venue venue1 = new TicketmasterEntity.Venue("Ulls Blaus", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Barcelona")));
        TicketmasterEntity.Event event1 = new TicketmasterEntity.Event(List.of(new TicketmasterEntity.Attraction("Gotan Project")), venue1, new TicketmasterEntity.LocalEventDate("2021-05-20T21:00:00"));

        TicketmasterEntity.Venue venue2 = new TicketmasterEntity.Venue("Suppamolly", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Berlin")));
        TicketmasterEntity.Event event2 = new TicketmasterEntity.Event(List.of(new TicketmasterEntity.Attraction("NOFX")), venue2, new TicketmasterEntity.LocalEventDate("2022-06-21T21:00:00Z"));

        TicketmasterEntity.Venue venue3 = new TicketmasterEntity.Venue("Polish forest", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Szczecin")));
        TicketmasterEntity.Event event3 = new TicketmasterEntity.Event(List.of(new TicketmasterEntity.Attraction("Woodstock Poland")), venue3, new TicketmasterEntity.LocalEventDate("2023-07-22"));

        return new TicketmasterEntity.TicketmasterDTO(Arrays.asList(event1, event2, event3));
    }

    private static TicketmasterEntity.TicketmasterDTO getTestConcertsAndFestivals() {
        TicketmasterEntity.Venue venue1 = new TicketmasterEntity.Venue("Ulls Blaus", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Barcelona")));
        TicketmasterEntity.Event event1 = new TicketmasterEntity.Event(List.of(new TicketmasterEntity.Attraction("Gotan Project")), venue1, new TicketmasterEntity.LocalEventDate("2021-05-20T21:00:00"));

        TicketmasterEntity.Venue venue2 = new TicketmasterEntity.Venue("Suppamolly", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Berlin")));
        TicketmasterEntity.Event event2 = new TicketmasterEntity.Event(List.of(new TicketmasterEntity.Attraction("NOFX")), venue2, new TicketmasterEntity.LocalEventDate("2022-06-21T21:00:00Z"));

        TicketmasterEntity.Venue venue3 = new TicketmasterEntity.Venue("Polish forest", new TicketmasterEntity.Location(new TicketmasterEntity.Address("Szczecin")));
        TicketmasterEntity.Event event3 = new TicketmasterEntity.Event(Arrays.asList(
                    new TicketmasterEntity.Attraction("Woodstock Poland"),
                    new TicketmasterEntity.Attraction("Manu Chao"),
                    new TicketmasterEntity.Attraction("The Offspring"),
                    new TicketmasterEntity.Attraction("Lehendakaris Muertos")),
                venue3, new TicketmasterEntity.LocalEventDate("2023-07-22"));

        return new TicketmasterEntity.TicketmasterDTO(Arrays.asList(event1, event2, event3));
    }
}
