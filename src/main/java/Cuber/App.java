package Cuber;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage pStage;
    private static App instance;

    public enum fxmlFiles {
        Splash("Splash.fxml");

        private String file;

        private fxmlFiles(String file) {
            this.file = file;
        }

        public String get() {
            return this.file;
        }
    }

    public static void main(String[] args) {
        // launch(args);
        for (int i = 0; i < 10; i++) {
            long sTime = System.currentTimeMillis();
                new Cuber();
            System.out.println(System.currentTimeMillis() - sTime);
        }
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        App.pStage = primaryStage;

        primaryStage.setResizable(false);

        App.loadScene(fxmlFiles.Splash, App.pStage);
        primaryStage.show();
    }

    @Override
    public void init() {
        App.instance = this;
    }

    public static <T extends Controller> T loadScene(fxmlFiles file, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource(file.get()));
        Pane pane = loader.<Pane>load();
        stage.setScene(new Scene(pane));
        return loader.getController();
    }

    public static <T extends Pane> T getFxml(fxmlFiles file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource(file.get()));
        return loader.<T>load();
    }

    public static URL getResource(String file) {
        return App.instance.getClass().getClassLoader().getResource(file);
    }
}