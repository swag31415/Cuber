package Cuber;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Cuber.Cube.Color;
import Cuber.Cube.Cube;
import Cuber.Cube.Moves;

public class Cuber {

    List<Moves[]> algList;
    int algLength;
    int cubeDim;
    Moves[] bestAlg;
    int bestAlgErr;

    public Cuber(int cubeDim, int algLength) {
        this.cubeDim = cubeDim;
        this.algLength = algLength;
        this.bestAlgErr = 60;
        this.algList = new LinkedList<Moves[]>();
    }

    public int testAlg(Moves[] alg) {
        Cube testCube = new Cube(cubeDim);
        for (Moves move : alg) {
            testCube.spin(move, 1);
        }
        return compareCubes(testCube, new Cube(cubeDim));
    }

    private int compareCubes(Cube cube1, Cube cube2) {
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

    public Moves[] genAlg(int algLength) {
        Moves[] alg = new Moves[algLength];
        int moveSetSize = Moves.values().length;
        for (int i = 0; i < algLength; i++) {
            alg[i] = Moves.values()[new Random().nextInt(moveSetSize)];
        }
        return alg;
    }

    public List<Moves[]> getAlgList() {
        return this.algList;
    }

    public int getAlgLength() {
        return this.algLength;
    }

    public int getCubeDim() {
        return this.cubeDim;
    }

    public int getBestAlgErr() {
        return this.bestAlgErr;
    }

    public Moves[] getBestAlg() {
        return this.bestAlg;
    }


}