package com.mirror.concertsapi.infrastructure.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public interface LastfmEntity {
    @JsonIgnoreProperties(ignoreUnknown = true)
    record LastfmDTO(TopArtist topartists) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record TopArtist(List<Artist> artist) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Artist(String name){}
}