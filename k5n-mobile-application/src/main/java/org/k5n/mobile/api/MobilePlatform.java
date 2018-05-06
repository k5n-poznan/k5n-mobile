/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Orientation;
import javafx.util.Callback;
import org.k5n.mobile.api.cache.CacheManager;
import org.k5n.mobile.api.cache.DefaultCacheManager;

/**
 *
 * @author Waldemar Kłaczyński
 */
public abstract class MobilePlatform {

    private static MobilePlatform current;
    
    private Application application;
    
    public static enum LifecycleEvent {

        START,
        PAUSE,
        RESUME,
        STOP
    }

    public static MobilePlatform getIstance() {
        try {
            if (current == null) {
                current = (MobilePlatform) Class.forName(getPlatformClassName()).newInstance();
            }
            return current;
        } catch (Throwable ex) {
            Logger.getLogger(MobilePlatform.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private static String getPlatformClassName() {
        String property = System.getProperty("javafx.platform.class", "org.k5n.mobile.impl.DesktopPlatformImpl");
        return property;
    }

    public Application getApplication() {
        if(application == null) {
            application = new Application(this);
        }
        return application;
    }
    
    
    public abstract String getName();

    public abstract File getPrivateStorage() throws IOException;

    public abstract void finish();

    public abstract boolean isTablet();

    public abstract boolean isDesktop();
    
    public abstract Optional<Orientation> getOrientation();

    public abstract void launchExternalBrowser(String url) throws IOException, URISyntaxException;

    public CacheManager getCacheManager() {
        return new DefaultCacheManager();
    }
    
    public void setOnLifecycleEvent(Callback<LifecycleEvent, Void> callback) {

    }

    public abstract NotificationsService getNotificationsService();

    public abstract ScanService getScanService();

    public abstract PicturesService getPicturesService();

    public abstract PositionService getPositionService();

    public abstract SettingService getSettingService();

}
