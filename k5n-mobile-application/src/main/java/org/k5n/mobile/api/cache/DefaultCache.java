/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Waldemar Kłaczyński
 * @param <K>
 * @param <V>
 */
public class DefaultCache <K, V> implements Cache<K,V> {

    private final Map<K, SoftReference<V>> map = new HashMap<>();

    public DefaultCache () {
    }
    
    @Override
    public V get(K key) {
        SoftReference<V> ref = map.get(key);
        if (ref != null) {
            return ref.get();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException ("Cache key should not be null");
        }
        if (value == null) {
            throw new NullPointerException ("Cache value should not be null");
        }
        SoftReference<V> ref = new SoftReference<>(value);
        map.put(key, ref);
    }

    @Override
    public boolean remove(K key) {
        boolean answer = map.containsKey(key);
        map.remove(key);
        return answer;
    }

    @Override
    public void removeAll() {
        map.clear();
    }

}
