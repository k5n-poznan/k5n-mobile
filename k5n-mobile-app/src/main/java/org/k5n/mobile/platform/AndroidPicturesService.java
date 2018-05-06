/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.k5n.mobile.api.PicturesService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidPicturesService implements PicturesService {

    @Override
    public ObjectProperty<Image> retrievePicture(PictureSource source) {
        return new SimpleObjectProperty<>();
    }
    
}
