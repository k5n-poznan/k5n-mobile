package org.k5n.mobile;

import org.k5n.mobile.system.LayoutManager;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.k5n.mobile.api.MobilePlatform;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        MobilePlatform platform = MobilePlatform.getIstance();
        Rectangle2D bounds = platform.isDesktop()
                ? Screen.getPrimary().getBounds()
                : Screen.getPrimary().getVisualBounds();

        loader.setLocation(getViewLocation());
        Parent root = loader.load();

        Scene scene;
        if (platform.isDesktop()) {
            scene = new Scene(root);
        } else {
            scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        }

        scene.getStylesheets().add("/styles/basic.css");

        LayoutManager layout = new LayoutManager(scene);
        LayoutManager.setIstance(layout);

        stage.setTitle("Kościół K5N");
        stage.setScene(scene);
        stage.show();
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList();
    }

    private URL getViewLocation() {
        return getClass().getResource("/fxml/Main.fxml");
    }

}
