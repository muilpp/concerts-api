package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.infrastructure.lastfm.LastfmEntity;
import java.util.Collections;
import java.util.List;

public class BandMapper {

    public static List<String> lastfmDtoToBandMapper(LastfmEntity.LastfmDTO lastfmDTO) {
        if (lastfmDTO == null || lastfmDTO.topartists() == null)
            return Collections.emptyList();

        return lastfmDTO.topartists().artist().stream()
                .map(LastfmEntity.Artist::name)
                .toList();
    }
}
