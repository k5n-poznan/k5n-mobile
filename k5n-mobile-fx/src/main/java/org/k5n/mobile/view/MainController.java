/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.k5n.mobile.api.Identity;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.system.LayoutManager;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class MainController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MobilePlatform mp = MobilePlatform.getIstance();
        Identity identity = mp.getApplication().getIdentity();

        boolean logged = identity.isLoggedIn();
        if (!logged) {
            adminButtom.setVisible(false);
            vbox.getChildren().remove(adminButtom);
        }

    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
        MobilePlatform mp = MobilePlatform.getIstance();
        mp.finish();
    }

    @FXML
    private void handleSettingsAction(ActionEvent event) {
        LayoutManager lm = LayoutManager.getIstance();
        lm.showPropertiesView();
    }

}
