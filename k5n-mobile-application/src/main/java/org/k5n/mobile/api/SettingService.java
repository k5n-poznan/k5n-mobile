/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

/**
 *
 * @author Waldemar Kłaczyński
 */
public interface SettingService {

    /**
     * Stores the setting with the specified key and value. If a setting with
     * the specified key exists, the value for that setting will be overwritten
     * with the specified value.
     *
     * @param key a key that uniquely identifies the setting
     * @param value the value associated with the key
     */
    void store(String key, String value);

    /**
     * Removes the setting for the specified key.
     *
     * @param key the key of the setting that needs to be removed
     */
    void remove(String key);

    /**
     * Retrieves the value of the setting that is identified by the specified
     * key.
     *
     * @param key the key of the setting to look up
     * @return the value associated with the setting or <code>null</code> when
     * no setting was stored with the specified key
     */
    String retrieve(String key);

}
