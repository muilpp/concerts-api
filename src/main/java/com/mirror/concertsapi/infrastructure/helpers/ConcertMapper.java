package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.infrastructure.ticketmasterdto.TicketmasterEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConcertMapper {

    public static List<Concert> TicketmasterDtoToConcertMapper(TicketmasterEntity.TicketmasterDTO ticketMasterDTO) {

        return ticketMasterDTO._embedded().events().stream()
                .map(e ->
                    new Concert(e.name(),
                            e._embedded().venues().get(0).city().name(),
                            e._embedded().venues().get(0).name(),
                            LocalDate.parse(e.dates().start().localDate())))
                .collect(Collectors.toList());
    }
}