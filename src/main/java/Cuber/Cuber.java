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