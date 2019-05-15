import Cuber.Cuber;
import Cuber.CuberLogger;
import Cuber.Comparators.ColorDiff;
import Cuber.Comparators.DisColor;
import Cuber.Cube.Moves;
import Cuber.Generators.Rand3x3Gen;
import GUI.Controller;

public class Main {

    public static void main(String[] args) {
        Controller cont = new Controller();
        cont.run();
    }
}