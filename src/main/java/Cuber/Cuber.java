package Cuber;

import jnr.ffi.LibraryLoader;

public class Cuber {

    public static final String cuber_dll = "Cube\\CuberLib";
    private static CuberLib cuberLib;

    public interface CuberLib {
        int getLen(int dim);
        void init(byte[] cube, int dim);
        void turn(byte[] cube, int dim, int ind);
        void turns(byte[] cube, int dim, int[] inds, int len);
        void disp(byte[] cube, int dim);
        int compare(byte[] cube1, byte[] cube2, int dim);
    }

    public Cuber() {
        if (Cuber.cuberLib == null) {
            Cuber.cuberLib = LibraryLoader.create(CuberLib.class).load(cuber_dll);
        }
    }

    private byte[] gen_cube(int dim) {
        byte[] cube = new byte[cuberLib.getLen(dim)];
        cuberLib.init(cube, dim);
        return cube;
    }
}