/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Waldemar Kłaczyński
 */
public interface PicturesService {
    
   public static enum PictureSource {
        CAMERA,
        GALLERY
    }
    
    ObjectProperty<Image> retrievePicture(PictureSource source);    
    
}
