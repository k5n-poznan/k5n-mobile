package org.k5n.mobile.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.exceptions.AuthorizationException;
import org.k5n.mobile.system.ViewManager;

/**
 * FXML Controller class
 *
 * @author Waldemar Kłaczyński
 */
public class MessagesController implements Initializable {

    private static final Logger log = Logger.getLogger(MessagesController.class.getName());

    public static final String EVENT_TYPE_CLICK = "click";
    public static final String EVENT_TYPE_MOUSEOVER = "mouseover";
    public static final String EVENT_TYPE_MOUSEOUT = "mouseclick";

    @FXML
    private WebView browser;

    @FXML
    private Button settingsButton;
    
    private Scene parent;

    @Inject
    private Stage stage;

    @Inject
    private MobilePlatform mp;

    @Inject
    private ViewManager vm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.parent = stage.getScene();
    }

    @SuppressWarnings("Convert2Lambda")
    public void showError(Throwable th) {
        String title = "Błąd operacji";
        String message = "";
        boolean hassettings = false;

        Throwable cause = th;
        while (cause != null) {
            if (cause instanceof AuthorizationException) {
                title = "Błąd uwierzytelnienia";
                hassettings = true;
            } else if (cause instanceof AuthorizationException) {
                hassettings = true;
                title = "Błąd połączenia";
            }
            message = cause.getMessage();
            cause = cause.getCause();
        }
        
        showSettingsFunctions(hassettings);
        
        byte[] res = readResource("/fxml/messages/error.png");
        String bimg = new String(Base64.getEncoder().encode(res));
        
        WebEngine webEngine = browser.getEngine();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<img src=\"data:image/png;base64,").append(bimg).append("\" alt=\"no image\" style=\"vertical-align: middle;\"/>");
        sb.append("<b>").append(title).append("</b>");
        sb.append("<hr/>");

        sb.append(message);
        sb.append("</body>");
        sb.append("</html>");

        String content = sb.toString();
        System.out.println(content);
        webEngine.loadContent(content);

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {

                }
            }
        });
    }

    private void showSettingsFunctions(boolean show) {
        settingsButton.setVisible(show);
        settingsButton.setDisable(!show);
    }
    
    
    public byte[] readResource(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            int available = is.available();
            byte[] result = new byte[available];
            is.read(result, 0, available);

            return result;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load resource " + path, e);
        }

        return new byte[0];
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        stage.setScene(parent);
    }

    @FXML
    private void handleSettingsButtonAction(ActionEvent event) {
        vm.showPropertiesView();
    }

    protected void fireHyperlinkUpdate(String eventType, String desc) {
        try {
            mp.launchExternalBrowser(desc);
        } catch (Throwable ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

}
