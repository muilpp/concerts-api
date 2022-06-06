package com.mirror.concertsapi.infrastructure;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/concerts", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE,
        MediaType.TEXT_XML_VALUE })
public class ConcertsApiController {

    @Autowired
    public ConcertService concertService;

    @GetMapping("test")
    public List<Concert> test() {

        return concertService.getConcerts("Imelda", "sp3r", "100");
    }
}