package Cuber.Comparators;

import Cuber.Cube.Color;
import Cuber.Cube.Cube;
import Cuber.Molds.Comparator;

public class ColorDiff implements Comparator {

    public ColorDiff() {
    }

    public int compareCubes(Cube cube1, Cube cube2) {
        Color[][][] pMap1 = cube1.getPMap();
        Color[][][] pMap2 = cube2.getPMap();
        if (cube1.getDim() == cube2.getDim()) {
            int diff = 0;
            for (int i = 0; i < pMap1.length; i++) {
                for (int j = 0; j < pMap1[i].length; j++) {
                    for (int k = 0; k < pMap1[i][j].length; k++) {
                        diff += (pMap1[i][j][k].getColor() == pMap2[i][j][k].getColor()) ? 0 : 1;
                    }
                }
            }
            return diff;
        } else {
            return -1;
        }
    }
}