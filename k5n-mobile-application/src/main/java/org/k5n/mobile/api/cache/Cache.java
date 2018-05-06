/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.cache;

/**
 *
 * @author Waldemar Kłaczyński
 * @param <K>
 * @param <V>
 */
public interface Cache <K, V>  {
   
    V get (K key);
    
    void put (K key, V value);
    
    boolean remove (K key);
    
    void removeAll ();

}
