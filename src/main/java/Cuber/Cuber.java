package Cuber;

import jnr.ffi.LibraryLoader;

public class Cuber {

    public static final String cube_dll = "Cube\\cube";

    public interface Cube {
        void init(int dim);
        void e_turn(int ind);
        void m_turn(int ind);
        void s_turn(int ind);
        void turn(int ind);
        void turns(int len, int[] inds);
        void disp();
    }

    public Cuber() {
        Cube cube = LibraryLoader.create(Cube.class).load(cube_dll);
        cube.init(3);
        cube.disp();
        cube.turns(3, new int[]{0, 4, 6});
        cube.disp();
    }
}