package com.mirror.concertsapi.application.usecases;

import java.util.List;

public interface BandsFetcher {

    List<String> getFavoriteBands(String user, String key, int limit);
}