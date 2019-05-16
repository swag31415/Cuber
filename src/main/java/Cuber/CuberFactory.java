package Cuber;

import Cuber.Molds.Comparator;
import Cuber.Molds.Generator;

public class CuberFactory {

    Generator gen;
    Comparator comp;

    public CuberFactory(Generator gen, Comparator comp) {
        this.gen = gen;
        this.comp = comp;
    }

    public Cuber getCuber(int cubeDim) {
        return new Cuber(cubeDim, gen, comp);
    }
}