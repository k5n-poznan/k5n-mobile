/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class DefaultCacheManager implements CacheManager {

    @SuppressWarnings("rawtypes")
    static Map<String, Cache> caches = new HashMap<>();
    
    @Override
    public <K, V> Cache<K, V> createCache(String name) {
        Cache<K,V> answer = new DefaultCache<>();
        caches.put(name, answer);
        return answer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        return caches.get(cacheName);
    }
    
}
