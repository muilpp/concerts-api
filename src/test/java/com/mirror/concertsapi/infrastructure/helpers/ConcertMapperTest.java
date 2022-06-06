package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.ticketmasterdto.*;
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
        TicketmasterDTO ticketmasterDTO = getTestObject();

        List<Concert> concerts = ConcertMapper.TicketmasterDtoToConcertMapper(ticketmasterDTO);

        Assert.notNull(concerts, "Concert list is null");
        Assert.noNullElements(concerts, "Concert list has null elements");
        Assert.isTrue(concerts.size() == 3, "Concerts size is not equal to 3");
        Assert.isTrue(concerts.get(0).getVenue().equals("Ulls Blaus"), "Venue is not Ulls Blaus");
        Assert.isTrue(concerts.get(1).getCity().equals("Berlin"), "City is not Berlin");
        Assert.isTrue(concerts.get(1).getDate().isEqual(LocalDate.of(2022, 6, 6)), "");
        Assert.isTrue(concerts.get(2).getBand().equals("Woodstock Poland"), "Band is not Woodstock Poland");
    }

    private static TicketmasterDTO getTestObject() {
        Venue venue1 = new Venue("Ulls Blaus", new City("Barcelona"));
        Dates dates1 = new Dates(new Start(LocalDate.of(2021, 2, 22).toString(), ""));
        Event event1 = new Event("Gotan Project", dates1, new Embedded__1(List.of(venue1)));

        Venue venue2 = new Venue("Suppamolly", new City("Berlin"));
        Dates dates2 = new Dates(new Start(LocalDate.of(2022, 6, 6).toString(), ""));
        Event event2 = new Event("NOFX", dates2, new Embedded__1(List.of(venue2)));

        Venue venue3 = new Venue("Polish forest", new City("Szczecin"));
        Dates dates3 = new Dates(new Start(LocalDate.of(2023, 8, 16).toString(), ""));
        Event event3 = new Event("Woodstock Poland", dates3, new Embedded__1(List.of(venue3)));

        return new TicketmasterDTO(new Embedded(Arrays.asList(event1, event2, event3)));
    }
}
