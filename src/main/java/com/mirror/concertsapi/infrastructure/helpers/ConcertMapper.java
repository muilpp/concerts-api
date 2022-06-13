package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.ticketmaster.TicketmasterEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class ConcertMapper {

    public static List<Concert> ticketmasterDtoToConcertMapper(TicketmasterEntity.TicketmasterDTO ticketMasterDTO) {
        if (ticketMasterDTO == null || ticketMasterDTO.events() == null)
            return Collections.emptyList();

        return ticketMasterDTO.events().stream()
                .map(e -> {
                            if (e.attractions() == null)
                                return List.of(new Concert("",
                                        e.venue().location().address().city(),
                                        e.venue().name(),
                                        parseDate(e.local_event_date())));
                            else {
                                return e.attractions().stream()
                                        .map(a -> new Concert(a.name(),
                                            e.venue().location().address().city(),
                                            e.venue().name(),
                                            parseDate(e.local_event_date())))
                                        .toList();
                            }
                        })
                .flatMap(List::stream)
                .toList();
    }

    private static String parseDate(TicketmasterEntity.LocalEventDate date) {
        if (date == null)
            return "";
        else if (date.value().contains("Z"))
            return ZonedDateTime.parse(date.value()).toLocalDate().toString();
        else if (date.value().contains("T"))
            return LocalDateTime.parse(date.value()).toLocalDate().toString();
        else
            return LocalDate.parse(date.value()).toString();
    }
}