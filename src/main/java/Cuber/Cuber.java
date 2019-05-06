package Cuber;

import java.util.Random;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Cuber.Cube.Color;
import Cuber.Cube.Cube;
import Cuber.Cube.Moves;

public class Cuber {

    HashMap<String, Integer> algMap;
    int algLength;
    int cubeDim;

    public Cuber(int cubeDim, int algLength) {
        this.cubeDim = cubeDim;
        this.algLength = algLength;
        this.algMap = new HashMap<String, Integer>();
    }

    public int getBestAlgErr() {
        for (int i = 0; i < 60; i++) {
            if (algMap.containsValue(i)) {
                return i;
            }
        }
        return -1;
    }

    public void findAlgs(int iterations) {
        for (int i = 0; i < iterations; i++) {
            Moves[] alg = genAlg(algLength);
            String algStr = Utils.arrayToString(alg);
            if (!algMap.containsKey(algStr)) {
                int err = testAlg(alg);
                algMap.put(algStr, err);
            }
        }
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

    public HashMap<String, Integer> getAlgMap() {
        return this.algMap;
    }

    public int getAlgLength() {
        return this.algLength;
    }

    public int getCubeDim() {
        return this.cubeDim;
    }

    @Override
    public String toString() {
        String out = "";
        Iterator<Entry<String, Integer>> iterator = new ArrayList<HashMap.Entry<String, Integer>>(algMap.entrySet()).iterator();
        System.out.println("Now in listlad");
        while(iterator.hasNext()) {
            out += "\"" + iterator.next().getKey() + "\", " + iterator.next().getValue() + "\n";
        }
        return out;
    }

    public void log() {
        String fileName = "CuberLog " + cubeDim + "x" + cubeDim;
        Utils.printToLocalFile(fileName, this.toString());
        System.out.println("saved to file");
        Utils.printToLocalFile(fileName, algMap);
        System.out.println("logging complete");
    }

    public boolean pullFromLog() {
        try {
            String fileName = "CuberLog " + cubeDim + "x" + cubeDim;
            HashMap<String, Integer> logMap = (HashMap<String, Integer>) Utils.getFromLocalFile(fileName);
            this.algMap.putAll(logMap);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }
}