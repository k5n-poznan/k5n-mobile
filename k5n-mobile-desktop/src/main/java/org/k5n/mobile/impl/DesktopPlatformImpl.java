/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.impl;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javafx.geometry.Orientation;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.NotificationsService;
import org.k5n.mobile.api.PicturesService;
import org.k5n.mobile.api.PositionService;
import org.k5n.mobile.api.ScanService;
import org.k5n.mobile.api.SettingService;

public final class DesktopPlatformImpl extends MobilePlatform {

    private DesktopScanService scanService;
    private DesktopPicturesService picturesService;
    private final PositionService desktopPositionService = new DesktopPositionService();
    private final SettingService desktopSettingService;

    public DesktopPlatformImpl() throws IOException {
        desktopSettingService = new DesktopSettingService(getPrivateStorage().getAbsolutePath());
    }

    @Override
    public String getName() {
        return "Saba IBank Dasktop Platform";
    }

    @Override
    public File getPrivateStorage() throws IOException {
        String home = System.getProperty("user.home");
        File path = new File(home, "ibank-storage");
        File f = new File(path, ".ibank-storage");
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        return f;
    }

    @Override
    public void finish() {
        javafx.application.Platform.exit();
    }

    @Override
    public boolean isTablet() {
        return "tablet".equals(System.getProperty("charm-desktop-form"));
    }

    @Override
    public Optional<Orientation> getOrientation() {
        return Optional.of(Orientation.VERTICAL);
    }
    
    @Override
    public void launchExternalBrowser(String url) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(url));
    }

    @Override
    public NotificationsService getNotificationsService() {
        return new DesktopNotificationsService();
    }

    @Override
    public ScanService getScanService() {
        if (scanService == null) {
            scanService = new DesktopScanService();
        }
        return scanService;
    }

    @Override
    public PicturesService getPicturesService() {
        if (picturesService == null) {
            picturesService = new DesktopPicturesService();
        }
        return picturesService;
    }

    @Override
    public PositionService getPositionService() {
        return desktopPositionService;
    }

    @Override
    public SettingService getSettingService() {
        return desktopSettingService;
    }

    @Override
    public boolean isDesktop() {
        return true;
    }

}
