/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.inject.Inject;
import org.k5n.mobile.system.ViewManager;
import org.k5n.mobile.data.SimpleController;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.exceptions.AuthorizationException;
import org.k5n.mobile.api.Identity;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class LoginController extends SimpleController implements Initializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    
    @FXML
    private TextField user;
    @FXML
    private TextField password;

    @Inject
    private ViewManager vm;
    
    @Inject
    private MobilePlatform mp;

    @Inject
    private Identity identity;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        try {
            boolean result = identity.login(user.getText(), password.getText());
            if (result) {
                vm.showMainView();
            }
        } catch (Throwable ex) {
            log.log(Level.SEVERE, null, ex);
            vm.showExceptionView(ex);
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
