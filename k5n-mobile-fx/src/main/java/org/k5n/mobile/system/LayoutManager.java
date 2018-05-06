/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.system;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.k5n.mobile.view.LoginController;
import org.k5n.mobile.view.MainController;
import org.k5n.mobile.view.MessagesController;
import org.k5n.mobile.view.settings.ConnetionController;
import org.k5n.mobile.view.settings.PropertiesController;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class LayoutManager {
    
    private static final Logger log = Logger.getLogger(LayoutManager.class.getName());
    
    private static LayoutManager istance;

    public static LayoutManager getIstance() {
        return istance;
    }

    public static void setIstance(LayoutManager istance) {
        LayoutManager.istance = istance;
    }

    private final Scene scene;

    public LayoutManager(Scene scene) {
        this.scene = scene;
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/Main.fxml")
            );
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/Login.fxml")
            );
            scene.setRoot((Parent) loader.load());
            LoginController controller = loader.<LoginController>getController();
            controller.init();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPropertiesView() {
        try {
            Parent parent = scene.getRoot();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/settings/PropertiesSettings.fxml")
            );
            scene.setRoot((Parent) loader.load());
            PropertiesController controller = loader.<PropertiesController>getController();
            controller.initSettings(parent);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showConnectionView() {
        try {
            Parent parent = scene.getRoot();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/settings/ConnetionSettings.fxml")
            );
            scene.setRoot((Parent) loader.load());
            ConnetionController controller = loader.<ConnetionController>getController();
            controller.initSettings(parent);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showExceptionView(Throwable th) {
        try {
            Parent parent = scene.getRoot();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/messages/Error.fxml")
            );
            scene.setRoot((Parent) loader.load());
            MessagesController controller = loader.<MessagesController>getController();
            controller.initError(parent, th);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    
    

}
