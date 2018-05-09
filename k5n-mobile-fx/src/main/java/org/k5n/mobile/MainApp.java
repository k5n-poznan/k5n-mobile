package org.k5n.mobile;

import org.k5n.mobile.system.ViewManager;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.internal.inject.Injections;
import org.k5n.mobile.api.Identity;
import org.k5n.mobile.api.K5NApplication;
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
            ViewManager vm = new ViewManager();
            ServiceLocator loc = Injections.createLocator(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(stage).to(Stage.class);

                    bind(platform).to(MobilePlatform.class);
                    
                    bindFactory(new Factory<K5NApplication>() {
                        @Override
                        public K5NApplication provide() {
                            return platform.getApplication();
                        }

                        @Override
                        public void dispose(K5NApplication t) {
                        }
                    }).to(K5NApplication.class);
                    
                    bindFactory(new Factory<Identity>() {
                        @Override
                        public Identity provide() {
                            return platform.getApplication().getIdentity();
                        }

                        @Override
                        public void dispose(Identity t) {
                        }
                    }).to(Identity.class);

                    bindFactory(new Factory<CacheManager>() {
                        @Override
                        public CacheManager provide() {
                            return platform.getCacheManager();
                        }

                        @Override
                        public void dispose(CacheManager t) {
                        }
                    }).to(CacheManager.class);

                    bindFactory(new Factory<SettingService>() {
                        @Override
                        public SettingService provide() {
                            return platform.getSettingService();
                        }

                        @Override
                        public void dispose(SettingService t) {
                        }
                    }).to(SettingService.class);

                    bindFactory(new Factory<ViewManager>() {
                        @Override
                        public ViewManager provide() {
                            return vm;
                        }

                        @Override
                        public void dispose(ViewManager t) {
                        }
                    }).to(ViewManager.class);
                    
                }
            });
            loc.inject(vm);
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
