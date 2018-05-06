/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.geometry.Orientation;
import javafx.util.Callback;
import javafxports.android.FXActivity;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.NotificationsService;
import org.k5n.mobile.api.PicturesService;
import org.k5n.mobile.api.PositionService;
import org.k5n.mobile.api.ScanService;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.api.cache.CacheManager;
import org.k5n.mobile.platform.notifications.AndroidNotificationsService;
import org.k5n.mobile.platform.scan.ScanServiceImpl;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidPlatform extends MobilePlatform {

    private AndroidNotificationsService androidNotificationService = null;

    static FXActivity activity;
    static {
        activity = FXActivity.getInstance();
    }

    private AndroidPositionService androidPositionService;
    private AndroidSettingService androidSettingService;
    private ScanService scanService;
    private AndroidPicturesService picturesService;

    @Override
    public String getName() {
        return "IBank Android Applicarion";
    }
    
    @Override
    public File getPrivateStorage() throws IOException {
        return activity.getFilesDir();
    }

    @Override
    public PositionService getPositionService() {
        if (androidPositionService == null) {
            androidPositionService = new AndroidPositionService();
        }
        return androidPositionService;
    }

    @Override
    public SettingService getSettingService() {
        if (androidSettingService == null) {
            androidSettingService = new AndroidSettingService();
        }
        return androidSettingService;
    }
    
    @Override
    public void setOnLifecycleEvent(Callback<LifecycleEvent, Void> callback) {
        activity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity actvt, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity actvt) {
                callback.call(LifecycleEvent.START);
            }

            @Override
            public void onActivityResumed(Activity actvt) {
                callback.call(LifecycleEvent.RESUME);
            }

            @Override
            public void onActivityPaused(Activity actvt) {
                callback.call(LifecycleEvent.PAUSE);
            }

            @Override
            public void onActivityStopped(Activity actvt) {
                callback.call(LifecycleEvent.STOP);
            }

            @Override
            public void onActivitySaveInstanceState(Activity actvt, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity actvt) {
            }
        });
    }
    
    @Override
    public void launchExternalBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }
    
    @Override
    public CacheManager getCacheManager() {
        return new AndroidCacheManager();
    }

    @Override
    public Optional<Orientation> getOrientation() {

        switch ( activity.getResources().getConfiguration().orientation ) {
            case Configuration.ORIENTATION_LANDSCAPE: return Optional.of(Orientation.HORIZONTAL);
            case Configuration.ORIENTATION_PORTRAIT: return Optional.of(Orientation.VERTICAL);
            default: return Optional.empty();
        }

    }

    @Override
    public void finish() {
        FXActivity.getInstance().finish();
    }

    @Override
    public boolean isTablet() {
        DisplayMetrics metrics = new DisplayMetrics();
        FXActivity.getInstance().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        try {
            float yInches = metrics.heightPixels / metrics.ydpi;
            float xInches = metrics.widthPixels / metrics.xdpi;
            double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
            return (diagonalInches >= 6.5);
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public NotificationsService getNotificationsService() {
        if (androidNotificationService == null) {
            androidNotificationService = new AndroidNotificationsService();
        }
        return androidNotificationService;
    }
    
    @Override
    public ScanService getScanService() {
        if (scanService == null) {
            scanService = new ScanServiceImpl();
        }
        return scanService;
    }
    
    @Override
    public PicturesService getPicturesService() {
        if (picturesService == null) {
            picturesService = new AndroidPicturesService();
        }
        return picturesService;
    }

    @Override
    public boolean isDesktop() {
        return false;
    }

}
