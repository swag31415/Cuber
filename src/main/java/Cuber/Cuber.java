package Cuber;

import jnr.ffi.LibraryLoader;

public class Cuber {

    public static final String cube_dll = "Cube\\cube";

    public interface Cube {
        void init(int dim);
        void disp();
        byte get(int face, int y, int x);
    }

    public Cuber() {
        Cube cube = LibraryLoader.create(Cube.class).load(cube_dll);
        cube.init(3);
        cube.disp();
    }
}