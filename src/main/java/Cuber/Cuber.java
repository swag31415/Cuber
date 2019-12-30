package Cuber;

import jnr.ffi.LibraryLoader;

public class Cuber {

    public static final String cuber_dll = "Cube\\CuberLib";
    private static CuberLib cuberLib;

    public interface CuberLib {
        int getLen(int dim);
        void init(byte[] _cube, int dim);
        void turn(byte[] _cube, int dim, int ind);
        void turns(byte[] _cube, int dim, int len, int[] inds);
        void disp(byte[] _cube, int dim);
    }

    public Cuber() {
        if (Cuber.cuberLib == null) {
            Cuber.cuberLib = LibraryLoader.create(CuberLib.class).load(cuber_dll);
        }
    }
}