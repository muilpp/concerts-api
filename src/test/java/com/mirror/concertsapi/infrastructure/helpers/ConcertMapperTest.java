package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.ticketmasterdto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ConcertMapperTest {

    @Test
    @DisplayName("Ticket master object is mapped to concert")
    public void testTicketmasterDTOIsMappedToConcert() {
        TicketmasterEntity.TicketmasterDTO ticketmasterDTO = getTestObject();

        List<Concert> concerts = ConcertMapper.TicketmasterDtoToConcertMapper(ticketmasterDTO);

        Assert.notNull(concerts, "Concert list is null");
        Assert.noNullElements(concerts, "Concert list has null elements");
        Assertions.assertEquals(3, concerts.size());
        Assertions.assertEquals("Ulls Blaus", concerts.get(0).getVenue());
        Assertions.assertEquals("Berlin", concerts.get(1).getCity());
        Assertions.assertEquals(LocalDate.of(2022, 6, 6), concerts.get(1).getDate());
        Assertions.assertEquals("Woodstock Poland", concerts.get(2).getBand());
    }

    private static TicketmasterEntity.TicketmasterDTO getTestObject() {
        TicketmasterEntity.Venue venue1 = new TicketmasterEntity.Venue("Ulls Blaus", new TicketmasterEntity.City("Barcelona"));
        TicketmasterEntity.Dates dates1 = new TicketmasterEntity.Dates(new TicketmasterEntity.Start(LocalDate.of(2021, 2, 22).toString(), ""));
        TicketmasterEntity.Event event1 = new TicketmasterEntity.Event("Gotan Project", dates1, new TicketmasterEntity.Embedded__1(List.of(venue1)));

        TicketmasterEntity.Venue venue2 = new TicketmasterEntity.Venue("Suppamolly", new TicketmasterEntity.City("Berlin"));
        TicketmasterEntity.Dates dates2 = new TicketmasterEntity.Dates(new TicketmasterEntity.Start(LocalDate.of(2022, 6, 6).toString(), ""));
        TicketmasterEntity.Event event2 = new TicketmasterEntity.Event("NOFX", dates2, new TicketmasterEntity.Embedded__1(List.of(venue2)));

        TicketmasterEntity.Venue venue3 = new TicketmasterEntity.Venue("Polish forest", new TicketmasterEntity.City("Szczecin"));
        TicketmasterEntity.Dates dates3 = new TicketmasterEntity.Dates(new TicketmasterEntity.Start(LocalDate.of(2023, 8, 16).toString(), ""));
        TicketmasterEntity.Event event3 = new TicketmasterEntity.Event("Woodstock Poland", dates3, new TicketmasterEntity.Embedded__1(List.of(venue3)));

        return new TicketmasterEntity.TicketmasterDTO(new TicketmasterEntity.Embedded(Arrays.asList(event1, event2, event3)));
    }
}
