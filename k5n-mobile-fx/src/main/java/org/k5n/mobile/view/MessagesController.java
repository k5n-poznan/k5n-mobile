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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class MessagesController implements Initializable {

    @FXML
    private TextArea errortext;
    
    private Parent parent;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initError(Parent parent, Throwable th) {
        this.parent = parent;
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
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(parent);
    }
    
}
