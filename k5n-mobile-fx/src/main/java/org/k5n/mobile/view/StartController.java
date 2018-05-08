package org.k5n.mobile.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.inject.Inject;
import org.k5n.mobile.data.SimpleController;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.system.ViewManager;

public class StartController extends SimpleController {

    @FXML
    private Label label;
    
    @Inject
    private MobilePlatform mp;
    
    @Inject
    private ViewManager vm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Jestem Tutaj!");
        label.setText("Kliknięto To");
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        mp.finish();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        vm.showLoginView();

    }
}
