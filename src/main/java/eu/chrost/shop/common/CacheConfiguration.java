package eu.chrost.shop.common;

import eu.chrost.shop.products.Product;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.List;

@Configuration
@EnableCaching
@Profile("!test")
class CacheConfiguration {

    //TODO: Introduce disk storage
    //TODO: Investigate the error "Cache already exists" in tests (currently cache is disabled in them)

    @Bean
    public JCacheCacheManager jCacheCacheManager() {
        return new JCacheCacheManager(cacheManager());
    }

    @Bean
    public CacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        cacheManager.createCache("product", productCacheConfig());
        cacheManager.createCache("products", productsCacheConfig());
        return cacheManager;
    }

    private MutableConfiguration<Long, Product> productCacheConfig() {
        return new MutableConfiguration<Long, Product>()
                .setTypes(Long.class, Product.class)
                .setStoreByValue(false);
    }

    private MutableConfiguration<SimpleKey, List> productsCacheConfig() {
        return new MutableConfiguration<SimpleKey, List>()
                .setTypes(SimpleKey.class, List.class)
                .setStoreByValue(false);
    }
}
