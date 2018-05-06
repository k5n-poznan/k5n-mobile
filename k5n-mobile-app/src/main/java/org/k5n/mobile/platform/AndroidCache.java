/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import android.util.LruCache;
import org.k5n.mobile.api.cache.Cache;

/**
 *
 * @author Waldemar Kłaczyński
 * @param <K>
 * @param <V>
 */
public class AndroidCache<K, V> implements Cache<K, V> {

    private final LruCache<K, V> backing = new LruCache<>(100);

    public AndroidCache() {
    }

    @Override
    public V get(K key) {
        System.out.println("Android-cache, get " + key);
        return backing.get(key);
    }

    @Override
    public void put(K key, V value) {
        backing.put(key, value);
    }

    @Override
    public boolean remove(K key) {
        return (backing.remove(key) != null);
    }

    @Override
    public void removeAll() {
        backing.evictAll();
    }

}
