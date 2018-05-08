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
import javafx.stage.Stage;
import javax.inject.Inject;
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
    
    private Scene parent;
    
    @Inject
    private Stage stage;

    @Inject
    private MobilePlatform mp;
    
    @Inject
    private SettingService setting;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parent = stage.getScene();
        serviceurl.setText(setting.retrieve(Properties.ENDPOINT_URL));
    }    

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        setting.store(Properties.ENDPOINT_URL, serviceurl.getText());
        stage.setScene(parent);
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        stage.setScene(parent);
    }
    
}
