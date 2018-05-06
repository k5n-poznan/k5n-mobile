package org.k5n.mobile.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.k5n.mobile.data.SimpleController;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.system.LayoutManager;

public class StartController extends SimpleController {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        LayoutManager lm = LayoutManager.getIstance();
        lm.showMainView();
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        MobilePlatform mp = MobilePlatform.getIstance();
        mp.finish();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
