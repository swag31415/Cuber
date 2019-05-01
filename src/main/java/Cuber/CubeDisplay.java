package Cuber;

public class CubeDisplay {

    public static String getSpaces(Cube cube) {
        String out = "";
        for (int i = 0; i < (cube.getDim2() * 2); i++) {
            out += " ";
        }
        return out;
    }

    public static String getRow(Cube cube, int face, int row) {
        String out = "";
        for (int i = 0; i < cube.getDim2(); i++) {
            out += cube.getPMap()[face][row][i].getSym() + " ";
        }
        return out;
    }

    public static String getFaces(Cube cube, boolean includeSpace, int... faces) {
        String out = "";
        String space = includeSpace ? getSpaces(cube) : "";

        for (int i = 0; i < cube.getDim1(); i++) {
            out += space;
            for (int face : faces) {
                out += getRow(cube, face, i);
            }
            out += "\n";
        }

        return out;
    }

    public static String getCube(Cube cube) {
        String out = "";

        out += getFaces(cube, true, 1);
        out += getFaces(cube, false, 3, 0, 4, 5);
        out += getFaces(cube, true, 2);

        return out;
    }
}