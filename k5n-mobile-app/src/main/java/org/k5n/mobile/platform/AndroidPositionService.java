/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.k5n.mobile.api.PositionService;
import org.k5n.mobile.api.Position;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidPositionService implements PositionService {

    static final Logger LOGGER = Logger.getLogger(AndroidPositionService.class.getName());
    boolean activated = false;
    ObjectProperty<Position> positionProperty = new SimpleObjectProperty<>();

    private void activate() {
        activated = true;
        System.out.println("starting desktop positionprovider");
        @SuppressWarnings("SleepWhileInLoop")
        Runnable r = () -> {
            try {
                double lat0 = 50.8456;
                double lon0 = 4.7238;
                while (true) {
                    final double lat = lat0;
                    final double lon = lon0;
                    Platform.runLater(() -> {
                        Position p = new Position(lat, lon);
                        LOGGER.log(Level.INFO, "[FakePositionService] new position: {0}", p);
                        positionProperty.set(p);
                    });
                    Thread.sleep(10000);
                    lat0 = lat0 + Math.random() * .00001;
                    lon0 = lon0 + Math.random() * .00001;
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Activate error.", e);
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public ReadOnlyObjectProperty<Position> positionProperty() {
        if (!activated) {
            activate();
        }
        return positionProperty;
    }

    @Override
    public Position getPosition() {
        if (!activated) {
            activate();
        }
        return positionProperty.get();
    }
}
