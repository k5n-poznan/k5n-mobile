package org.k5n.mobile.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.k5n.mobile.api.MobilePlatform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

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

    private Scene parent;

    @Inject
    private Stage stage;

    @Inject
    private MobilePlatform mp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.parent = stage.getScene();
        WebEngine webEngine = browser.getEngine();
    }

    @SuppressWarnings("Convert2Lambda")
    public void showError(Throwable th) {
        String message = "";
        Throwable cause = th.getCause();
        while (cause != null) {
            message = cause.getMessage();
            cause = cause.getCause();
        }

        WebEngine webEngine = browser.getEngine();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(message);
        sb.append("</body>");
        sb.append("</html>");

        webEngine.loadContent(sb.toString());

        EventListener listener = (Event ev) -> {
            String domEventType = ev.getType();
            if (domEventType.equals(EVENT_TYPE_CLICK)) {
                String href = ((Element) ev.getTarget()).getAttribute("href");
                if (href == null) {
                    return;
                }
                Platform.runLater(() -> {
                    fireHyperlinkUpdate(domEventType, href);
                });
                ev.preventDefault();;
            }
        };

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    Document doc = webEngine.getDocument();
                    Element el = doc.getElementById("a");
                    NodeList lista = doc.getElementsByTagName("a");
                    for (int i = 0; i < lista.getLength(); i++) {
                        ((EventTarget) lista.item(i)).addEventListener(EVENT_TYPE_CLICK, listener, false);
                    }
                }
            }
        });

    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        stage.setScene(parent);
    }

    protected void fireHyperlinkUpdate(String eventType, String desc) {
        try {
            mp.launchExternalBrowser(desc);
        } catch (Throwable ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

}
