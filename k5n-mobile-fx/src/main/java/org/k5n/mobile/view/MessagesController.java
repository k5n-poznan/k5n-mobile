package org.k5n.mobile.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class MessagesController implements Initializable {

    @FXML
    private TextArea errortext;
    
    private Scene parent;

    @Inject
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.parent = stage.getScene();
    }    

    public void showError(Throwable th) {
        String message = "";
        Throwable cause = th.getCause();
        while(cause != null) {
            message = cause.getMessage();
            cause = cause.getCause();
        }
        errortext.setText(message);
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        stage.setScene(parent);
    }
    
}
