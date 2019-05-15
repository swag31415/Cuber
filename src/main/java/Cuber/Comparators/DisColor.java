package Cuber.Comparators;

import static Cuber.Cube.Cube.cubeSize;

import java.util.Arrays;

import Cuber.Utils;
import Cuber.Cube.Color;
import Cuber.Cube.Cube;
import Cuber.Molds.Comparator;

public class DisColor implements Comparator {

    public DisColor() {
    }

    @Override
    public int compareCubes(Cube cube1, Cube cube2) {
        Color[][][] pMap1 = cube1.getPMap();
        Color[][][] pMap2 = cube2.getPMap();
        int[] diffs = new int[cubeSize];
        if (cube1.getDim() == cube2.getDim()) {
            for (int i = 0; i < pMap1.length; i++) {
                for (int j = 0; j < pMap1[i].length; j++) {
                    for (int k = 0; k < pMap1[i][j].length; k++) {
                        for (int l = 0; l < diffs.length; l++) {
                            diffs[l] += (pMap1[Utils.safeSub(i, pMap1.length)][j][k].getColor() == pMap2[i][j][k].getColor()) ? 0 : 1;
                        }
                    }
                }
            }
            Arrays.sort(diffs);
            return diffs[0];
        } else {
            return -1;
        }
    }

}