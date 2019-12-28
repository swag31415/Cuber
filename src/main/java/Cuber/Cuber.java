package Cuber;

import jnr.ffi.LibraryLoader;

public class Cuber {

    public static final String cube_dll = "Cube\\cube";

    public interface Cube {
        void init(int dim);
        void e_turn(int ind);
        void m_turn(int ind);
        void s_turn(int ind);
        void disp();
    }

    public Cuber() {
        Cube cube = LibraryLoader.create(Cube.class).load(cube_dll);
        cube.init(3);
        cube.disp();
        cube.e_turn(0);
        cube.m_turn(1);
        cube.s_turn(0);
        cube.disp();
    }
}