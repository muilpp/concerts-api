package com.mirror.concertsapi.infrastructure.restconfig;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Data
@Configuration
@EnableCaching
public class CacheConfig {
    public static final String CONCERTS_CACHE = "concertsCacheDatabase";

    @Bean
    public CaffeineCache databaseCache() {
        return new CaffeineCache(CONCERTS_CACHE, Caffeine.newBuilder()
                .maximumSize(5000)
                .expireAfterAccess(12, TimeUnit.HOURS)
                .build());
    }
}
