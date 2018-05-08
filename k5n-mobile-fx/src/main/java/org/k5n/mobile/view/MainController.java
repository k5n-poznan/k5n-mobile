/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.k5n.mobile.api.Identity;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.exceptions.AuthorizationException;
import org.k5n.mobile.system.ViewManager;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class MainController implements Initializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    
    @FXML
    private Label sessionLabel;

    @FXML
    private Label klientNameLabel;

    @FXML
    private Button adminButtom;

    @FXML
    private AnchorPane pane;

    @FXML
    private VBox vbox;

    @Inject
    private ViewManager vm;
    
    @Inject
    private MobilePlatform mp;
    
    @Inject
    private Identity identity;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean logged = identity.isLoggedIn();
        if (!logged) {
            adminButtom.setVisible(false);
            vbox.getChildren().remove(adminButtom);
        }
    }

    @FXML
    private void handleSettingsButtonAction(ActionEvent event) throws AuthorizationException {
        vm.showConnectionView();
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        mp.finish();
    }

}
