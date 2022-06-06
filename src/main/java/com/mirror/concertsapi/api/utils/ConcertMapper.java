package com.mirror.concertsapi.api.utils;

import com.mirror.concertsapi.api.model.Concert;
import com.mirror.concertsapi.api.model.dto.TicketmasterDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConcertMapper {

    public static List<Concert> TicketmasterResponseToConcertMapper(TicketmasterDTO ticketMasterDTO) {

        return ticketMasterDTO.getEmbedded().getEvents().stream()
                .map(e ->
                    new Concert(e.getName(),
                            e.getEmbedded().getVenues().get(0).getCity().getName(),
                            e.getEmbedded().getVenues().get(0).getName(),
                            LocalDate.parse(e.getDates().getStart().getLocalDate())))
                .collect(Collectors.toList());
    }
}