package GUI;

import Cuber.Cuber;
import Cuber.CuberLogger;
import Cuber.Comparators.ColorDiff;
import Cuber.Cube.Moves;
import Cuber.Generators.Rand3x3Gen;
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

    private static final int Cycles = 100;

    private String selection;
    private Cuber cuber;
    CuberLogger logger;

    @FXML private Slider Iterations;
    @FXML private Slider CubeDim;
    @FXML private Slider AlgLength;
    @FXML private Label TOut;

    public Controller() {
        this.selection = "";
    }

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
            initBuild(cubeDim);
            TOut.setText((logger.pullFromLog() ? "Successfully pulled from log" : "Failed to pull from log") + ", Generating...");
            generate(iterations, Cycles, algLength);
        } else {
            TOut.setText(newSelection+ " Press Generate again to Confirm.");
            selection = newSelection;
        }
    }

    public void initBuild(int cubeDim) {
        this.cuber = new Cuber(cubeDim, new Rand3x3Gen(Moves.values()), new ColorDiff());
        this.logger = new CuberLogger(cuber);
    }

    private void generate(int iterations, int cycles, int algLength) {
        int subIterations = (iterations - (iterations % cycles)) / cycles;
        int remIterations = (iterations % cycles);

        for (int i = 1; i <= cycles; i++) {
            cuber.findAlgs(subIterations, algLength);
            TOut.setText(((i * subIterations) / ((double) iterations)) * 100 + "% done");
        }
        cuber.findAlgs(remIterations, algLength);
        TOut.setText("Best Alg Err: " + cuber.getBestAlgErr());
        logger.log();
    }
}