package com.mirror.concertsapi.infrastructure;

import com.mirror.concertsapi.domain.Concert;
import com.mirror.concertsapi.domain.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @GetMapping("area")
    public List<Concert> getConcerts(@RequestParam("bandsLimit") @Min(1) @Max(1000) Integer bandsLimit,
                                     @RequestParam("lastfmUser") @NotBlank String lastfmUser,
                                     @RequestParam("lastfmKey") @NotBlank String lastfmKey,
                                     @RequestParam("lat") @NotBlank String latitude,
                                     @RequestParam("long") @NotBlank String longitude) {
        return concertService.getConcerts(lastfmUser, lastfmKey, bandsLimit, latitude, longitude);
    }
}