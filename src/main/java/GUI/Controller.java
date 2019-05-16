package GUI;

import Cuber.Cuber;
import Cuber.CuberFactory;
import Cuber.CuberLogger;
import Cuber.Comparators.ColorDiff;
import Cuber.Cube.Moves;
import Cuber.Generators.Rand3x3Gen;
import GUI.Generator.Printer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application {

    private static final int Cycles = 100;

    private String selection;

    private CuberFactory cuberFactory;

    @FXML
    private Slider Iterations;
    @FXML
    private Slider CubeDim;
    @FXML
    private Slider AlgLength;
    @FXML
    private Label TOut;

    public Controller() {
        this.selection = "";
        this.cuberFactory = new CuberFactory(new Rand3x3Gen(Moves.values()), new ColorDiff());
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
            Cuber cuber = cuberFactory.getCuber(cubeDim);
            CuberLogger logger = new CuberLogger(cuber);

            TOut.setText((logger.pullFromLog() ? "Successfully pulled from log" : "Failed to pull from log") + ", Generating...");

            Platform.runLater(new Generator(cuber, iterations, algLength, (String str) -> TOut.setText(str), logger));
        } else {
            TOut.setText(newSelection+ " Press Generate again to Confirm.");
            selection = newSelection;
        }
    }
}