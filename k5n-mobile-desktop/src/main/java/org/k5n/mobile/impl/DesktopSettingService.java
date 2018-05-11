/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.k5n.mobile.api.SettingService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class DesktopSettingService implements SettingService {

    private static final Logger LOG = Logger.getLogger(DesktopSettingService.class.getName());

    private final String storageDirectory;
    private final Properties settings = new Properties();

    public DesktopSettingService(String storageDirectory) {
        this.storageDirectory = storageDirectory;
        Path settingsFile = getSettingsFile();

        try {
            settings.load(Files.newInputStream(settingsFile, StandardOpenOption.READ));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Could not read settings.properties file.", ex);
        }

        LOG.log(Level.INFO, "The following settings were successfully read from file: {0}", settings);
    }

    @Override
    public void store(String key, String value) {
        if (value != null) {
            settings.setProperty(key, value);
            saveSettings();
            LOG.log(Level.INFO, "Updated setting {0} = \"{1}\"", new Object[]{key, value});
        } else {
            remove(key);
        }
    }

    @Override
    public void remove(String key) {
        Object value = settings.remove(key);
        saveSettings();

        LOG.log(Level.INFO, "Removed setting {0} = \"{1}\"", new Object[]{key, value});
    }

    @Override
    public String retrieve(String key) {
        return settings.getProperty(key);
    }

    private void saveSettings() {
        Path settingsFile = getSettingsFile();
        try (BufferedWriter writer = Files.newBufferedWriter(settingsFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            settings.store(writer, null);

            LOG.log(Level.FINE, "The settings were successfully written to file.");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Failed to store settings.properties file.", ex);
        }
    }

    private Path getSettingsFile() {
        Path settingsFile = Paths.get(storageDirectory, "settings.properties");
        if (!Files.exists(settingsFile)) {
            try {
                Files.createFile(settingsFile);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Failed to create settings.properties file.", ex);
            }
        }

        LOG.log(Level.INFO, "settings.properties location is {0}", settingsFile);

        return settingsFile;
    }
}
