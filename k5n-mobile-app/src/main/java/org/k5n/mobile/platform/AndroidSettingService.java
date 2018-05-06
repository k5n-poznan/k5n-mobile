/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.logging.Logger;

import javafxports.android.FXActivity;
import org.k5n.mobile.api.SettingService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidSettingService implements SettingService {

    private static final Logger LOG = Logger.getLogger(AndroidSettingService.class.getName());

    private final SharedPreferences settings;

    public AndroidSettingService() {
        settings = FXActivity.getInstance().getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Override
    public void store(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    public void remove(String key) {
        String value = retrieve(key);

        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public String retrieve(String key) {
        return settings.getString(key, null);
    }

}
