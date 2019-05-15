package Cuber;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Cuber.Cube.Cube;
import Cuber.Cube.Moves;
import Cuber.Molds.Comparator;
import Cuber.Molds.Generator;

public class Cuber {

    private HashMap<String, Integer> algMap;
    private int cubeDim;

    private Generator gen;
    private Comparator comp;

    private Cube solvedCube;

    public Cuber(int cubeDim, Generator gen, Comparator comp) {
        this.cubeDim = cubeDim;
        this.solvedCube = new Cube(cubeDim);
        this.gen = gen;
        this.comp = comp;
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

    public void findAlgs(int iterations, int algLength) {
        for (int i = 0; i < iterations; i++) {
            Moves[] alg = gen.genAlg(algLength);
            String algStr = Utils.arrayToString(alg);
            if (!algMap.containsKey(algStr)) {
                int err = testAlg(alg);
                algMap.put(algStr, err);
            }
        }
    }

    private int testAlg(Moves[] alg) {
        Cube testCube = new Cube(cubeDim);
        for (Moves move : alg) {
            testCube.spin(move, 1);
        }
        return comp.compareCubes(testCube, solvedCube);
    }

    public HashMap<String, Integer> getAlgMap() {
        return this.algMap;
    }

    public int getCubeDim() {
        return this.cubeDim;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator<Entry<String, Integer>> iterator = new ArrayList<HashMap.Entry<String, Integer>>(algMap.entrySet()).iterator();
        while (iterator.hasNext()) {
            Entry<String, Integer> entry = iterator.next();
            builder.append("\"" + entry.getKey() + "\", " + entry.getValue() + "\n");
        }
        return builder.toString();
    }
}