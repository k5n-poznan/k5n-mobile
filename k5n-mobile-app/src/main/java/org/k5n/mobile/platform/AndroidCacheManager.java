/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import java.util.HashMap;
import java.util.Map;
import org.k5n.mobile.api.cache.Cache;
import org.k5n.mobile.api.cache.CacheManager;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidCacheManager implements CacheManager {

    static Map<String, Cache> caches = new HashMap<>();

    public AndroidCacheManager() {
    }

    @Override
    public <K, V> Cache<K, V> createCache(String cacheName) {
        AndroidCache<K, V> answer = new AndroidCache<>();
        caches.put(cacheName, answer);
        return answer;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        return caches.get(cacheName);
    }

}
