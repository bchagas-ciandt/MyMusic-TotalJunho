package com.ciandt.summit.bootcamp2022.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

public class CacheConfig implements JCacheManagerCustomizer {
    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache("buscarMusicas", new MutableConfiguration<>()
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 10)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));
    }
}
