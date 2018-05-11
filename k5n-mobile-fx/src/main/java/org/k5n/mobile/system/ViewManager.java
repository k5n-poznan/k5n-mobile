/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.system;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.glassfish.hk2.api.ServiceLocator;
import org.k5n.mobile.api.Identity;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.Properties;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.view.MainController;
import org.k5n.mobile.view.MessagesController;
import org.k5n.mobile.view.settings.ConnetionController;
import org.k5n.mobile.view.settings.PropertiesController;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class ViewManager {

    private static final Logger log = Logger.getLogger(ViewManager.class.getName());

    @Inject
    private Stage stage;

    @Inject
    private ServiceLocator im;

    @Inject
    private MobilePlatform mp;

    @Inject
    private Identity identity;

    @Inject
    private SettingService setting;

    public FXMLLoader load(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setControllerFactory(im::createAndInitialize);
        return loader;
    }

    public Scene getScene(FXMLLoader loader) throws IOException {
        Rectangle2D bounds = mp.isDesktop()
                ? new Rectangle2D(0, 0, 320, 480)
                : Screen.getPrimary().getVisualBounds();

        Parent root = loader.load();
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add("/styles/basic.css");
        return scene;
    }

    public FXMLLoader show(String fxml) throws IOException {
        FXMLLoader loader = load(fxml);
        stage.setScene(getScene(loader));
        return loader;
    }

    public void showMainView() {
        try {
            FXMLLoader loader = show("/fxml/Main.fxml");
            MainController controller = loader.<MainController>getController();
            if ("true".equals(setting.retrieve(Properties.HAS_ACCOUNT))) {
                Platform.runLater(() -> {
                    try {
                        boolean loggedIn = identity.isLoggedIn();
                        if (!loggedIn) {
                            String username = setting.retrieve(Properties.USERNAME);
                            String password = setting.retrieve(Properties.PASSWORD);
                            loggedIn = identity.login(username, password);
                        }
                        controller.setLogged(loggedIn);
                    } catch (Throwable ex) {
                        controller.setLogged(false);
                        log.log(Level.SEVERE, null, ex);
                        showExceptionView(ex);
                    }
                });
            } else {
                controller.setLogged(false);
            }
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showLoginView() {
        try {
            show("/fxml/Login.fxml");
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showPropertiesView() {
        try {
            FXMLLoader loader = show("/fxml/settings/PropertiesSettings.fxml");
            PropertiesController controller = loader.<PropertiesController>getController();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showConnectionView() {
        try {
            FXMLLoader loader = show("/fxml/settings/ConnetionSettings.fxml");
            ConnetionController controller = loader.<ConnetionController>getController();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void showExceptionView(Throwable th) {
        try {
            FXMLLoader loader = show("/fxml/messages/Error.fxml");
            MessagesController controller = loader.<MessagesController>getController();
            controller.showError(th);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

}
