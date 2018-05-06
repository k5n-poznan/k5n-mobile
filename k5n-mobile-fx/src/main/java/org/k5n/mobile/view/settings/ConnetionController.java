/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.view.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.Properties;
import org.k5n.mobile.api.SettingService;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class ConnetionController implements Initializable {

    @FXML
    private TextField serviceurl;
    private Parent parent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initSettings(Parent parent) {
        this.parent = parent;
        MobilePlatform mp = MobilePlatform.getIstance();
        SettingService setting = mp.getSettingService();
        
        serviceurl.setText(setting.retrieve(Properties.ENDPOINT_URL));
    }
    
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        MobilePlatform mp = MobilePlatform.getIstance();
        SettingService setting = mp.getSettingService();
        
        setting.store(Properties.ENDPOINT_URL, serviceurl.getText());

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(parent);
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(parent);
    }
    
    
}
