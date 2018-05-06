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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.k5n.mobile.system.LayoutManager;
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

    @FXML
    private TextField user;
    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init() {

    }

    
    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    private void handleLoginButtonAction(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        try {
            MobilePlatform mp = MobilePlatform.getIstance();
            Identity identity = mp.getApplication().getIdentity();
            boolean result = identity.login(user.getText(), password.getText());
            if (result) {
                
                
            
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            LayoutManager lm = LayoutManager.getIstance();
            lm.showExceptionView(ex);
        }
    }

    @FXML
    private void handleSettingsButtonAction(ActionEvent event) throws AuthorizationException {
        LayoutManager lm = LayoutManager.getIstance();
        lm.showConnectionView();
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        MobilePlatform mp = MobilePlatform.getIstance();
        mp.finish();
    }

}
