package com.mirror.concertsapi.infrastructure.helpers;

import com.mirror.concertsapi.infrastructure.lastfm.LastfmEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

public class BandsMapperTest {

    @Test
    @DisplayName("Bands are map from LastFM response")
    public void bandsAreMappedCorrectly() {
        LastfmEntity.TopArtist artists = new LastfmEntity.TopArtist(
                Arrays.asList(  new LastfmEntity.Artist("NOFX"),
                                new LastfmEntity.Artist("Metallica"),
                                new LastfmEntity.Artist("Pearl Jam")));

        LastfmEntity.LastfmDTO lastfmDTO = new LastfmEntity.LastfmDTO(artists);

        List<String> bands = BandMapper.lastfmDtoToBandMapper(lastfmDTO);

        Assert.notNull(bands, "Bands object is null");
        Assert.noNullElements(bands, "Bands has null elements");
        Assertions.assertEquals(3, bands.size());
        Assertions.assertEquals("Metallica", bands.get(1));
    }

    @Test
    @DisplayName("Empty list returned if bands object is null")
    public void emptyListReturnedIfNoBandsFound() {
        LastfmEntity.LastfmDTO lastfmDTO = new LastfmEntity.LastfmDTO(null);

        List<String> bands = BandMapper.lastfmDtoToBandMapper(lastfmDTO);
        Assert.notNull(bands, "Bands object is null");
        Assert.noNullElements(bands, "Bands has null elements");
        Assertions.assertEquals(0, bands.size());
    }
}
