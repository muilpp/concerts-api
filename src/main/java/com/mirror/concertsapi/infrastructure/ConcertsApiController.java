package com.mirror.concertsapi.infrastructure;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/concerts", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE,
        MediaType.TEXT_XML_VALUE })
@Validated
public class ConcertsApiController {

    @Autowired
    public ConcertService concertService;

    @GetMapping("{lastfmUser}/{lastfmKey}/{lat}/{long}")
    public List<Concert> getConcerts(@PathVariable("lastfmUser") @Valid  String[] lastfmUser,
                                     @PathVariable("lastfmKey") @NotNull String lastfmKey,
                                     @PathVariable("lat") @Valid String[] latitude,
                                     @PathVariable("long") @Valid String[] longitude,
                                     @RequestParam("bandsLimit") @Min(1) @Max(1000) Integer bandsLimit) {

        if (latitude.length != longitude.length)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Latitude and longitude parameters must have the same length");

        return concertService.getConcerts(lastfmUser, lastfmKey, bandsLimit, latitude, longitude);
    }
}