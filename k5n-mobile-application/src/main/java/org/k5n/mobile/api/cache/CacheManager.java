/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.cache;

/**
 *
 * @author Waldemar Kłaczyński
 */
public interface CacheManager {
    
    <K,V> Cache<K,V> createCache(String cacheName);
    
    <K,V> Cache<K,V> getCache(String cacheName);    
    
}
