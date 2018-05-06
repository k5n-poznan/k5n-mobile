/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import javafx.beans.property.ReadOnlyObjectProperty;

/**
 *
 * @author Waldemar Kłaczyński
 */
public interface PositionService {

    /**
     * A readonly property containing information about the device's current
     * location on earth. The property can contain a <code>null</code> object
     * when the position of the device could be determined.
     *
     * @return a readonly object property containing the device's current
     * location
     */
    ReadOnlyObjectProperty<Position> positionProperty();

    /**
     * The current position on earth of the device. Can return <code>null</code>
     * when the position of the device could not be determined, for instance
     * when the GPS has been turned off.
     *
     * @return the current position of the device
     */
    Position getPosition();
}
