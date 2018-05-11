package org.k5n.mobile.view.settings;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.inject.Inject;
import org.k5n.mobile.api.Properties;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.system.ViewManager;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class PropertiesController implements Initializable {

    @FXML
    private TextField serviceurl;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private CheckBox login;

    @Inject
    private SettingService setting;

    @Inject
    private ViewManager vm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceurl.setText(setting.retrieve(Properties.ENDPOINT_URL));
        username.setText(setting.retrieve(Properties.USERNAME));
        password.setText(setting.retrieve(Properties.PASSWORD));
        login.setSelected("true".equals(setting.retrieve(Properties.HAS_ACCOUNT)));
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        setting.store(Properties.ENDPOINT_URL, serviceurl.getText());
        setting.store(Properties.USERNAME, username.getText());
        setting.store(Properties.PASSWORD, password.getText());
        setting.store(Properties.HAS_ACCOUNT, login.isSelected() ? "true" : "false");
        vm.showMainView();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        vm.showMainView();
    }

}
