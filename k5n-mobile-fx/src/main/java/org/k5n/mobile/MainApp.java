package org.k5n.mobile;

import org.k5n.mobile.system.ViewManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.model.internal.ManagedObjectsFinalizer;
import org.k5n.mobile.api.Identity;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.api.cache.CacheManager;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Kościół K5N");

        MobilePlatform platform = MobilePlatform.getIstance();

        Scene start = getStartScene(platform);
        stage.setScene(start);
        stage.show();

        Platform.runLater(() -> {
            InjectionManager im = Injections.createInjectionManager();
            BootstrapBag bootstrapBag = new BootstrapBag();
            bootstrapBag.setManagedObjectsFinalizer(new ManagedObjectsFinalizer(im));
            List<BootstrapConfigurator> bootstrapConfigurators = Arrays.asList();
            bootstrapConfigurators.forEach(configurator -> configurator.init(im, bootstrapBag));

            im.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(stage).to(Stage.class);

                    bind(platform).to(MobilePlatform.class);
                    bindFactory(platform::getApplication).to(org.k5n.mobile.api.Application.class);
                    bindFactory(() -> {
                        return platform.getApplication().getIdentity();
                    }).to(Identity.class);

                    bindFactory(platform::getCacheManager).to(CacheManager.class);
                    bindFactory(platform::getSettingService).to(SettingService.class);

                    bindFactory(() -> {
                        return im.createAndInitialize(ViewManager.class);
                    }).to(ViewManager.class);
                }
            });
            ViewManager vm = im.getInstance(ViewManager.class);
            vm.showMainView();
        });
    }

    private Scene getStartScene(MobilePlatform platform) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));

        Rectangle2D bounds = platform.isDesktop()
                ? new Rectangle2D(0, 0, 320, 480)
                : Screen.getPrimary().getVisualBounds();

        Parent root = loader.load();

        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add("/styles/basic.css");
        return scene;
    }

}
