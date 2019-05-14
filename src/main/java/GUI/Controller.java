package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application {

    String selection = "";

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI.fxml"));
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private void Generate(ActionEvent event) {
        int iterations = Iterations.valueProperty().intValue();
        int cubeDim = CubeDim.valueProperty().intValue();
        int algLength = AlgLength.valueProperty().intValue();

        String newSelection = iterations + " of length " + algLength + " algorithms on a " + cubeDim + "x" + cubeDim + "?";

        if (selection.equals(newSelection)) {
            TOut.setText("Generating...");
        } else {
            TOut.setText(newSelection+ " Press Generate again to Confirm.");
            selection = newSelection;
        }
    }

    @FXML private Slider Iterations;
    @FXML private Slider CubeDim;
    @FXML private Slider AlgLength;
    @FXML private Label TOut;
}