package Cuber.Generators;

import java.util.Random;

import Cuber.Cube.Moves;
import Cuber.Molds.Generator;

public class Rand3x3Gen implements Generator {

    private Random rand;
    private Moves[] moveArray;

    public Rand3x3Gen(Moves[] moveArray) {
        this.rand = new Random();
        this.moveArray = moveArray;
    }

    public Moves[] genAlg(int algLength) {
        Moves[] alg = new Moves[algLength];

        for (int i = 0; i < algLength; i++) {
            alg[i] = moveArray[rand.nextInt(moveArray.length)];
        }

        return alg;
    }
}