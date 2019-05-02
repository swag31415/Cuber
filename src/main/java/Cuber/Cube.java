package Cuber;

import java.util.Map;

import Cuber.CubeMappings.Face;

public class Cube {

    private static final int cubeSize = 6; // Number of faces in a cube *DONT CHANGE THIS*
    private int dim1, dim2;

    public enum Colors {
        White('W'), Blue('B'), Green('G'), Orange('O'), Red('R'), Yellow('Y');

        private char sym;

        private Colors(char sym) {
            this.sym = sym;
        }

        public char getSym() {
            return sym;
        }
    }

    private enum Moves {

        r(CubeMappings.m, 2, false), 
        m(CubeMappings.m, 1, false), 
        l(CubeMappings.m, 0, true),

        d(CubeMappings.e, 2, false), 
        e(CubeMappings.e, 1, false), 
        u(CubeMappings.e, 0, true),

        f(CubeMappings.s, 2, false), 
        s(CubeMappings.s, 1, false), 
        b(CubeMappings.s, 0, true);

        private CubeMappings mapType;
        private int pos;
        private boolean isReversed;
        private Map<Integer, Colors[]> map;

        private Moves(CubeMappings mapType, int pos, boolean isReversed) {
            this.mapType = mapType;
            this.pos = pos;
            this.isReversed = isReversed;
        }

        public CubeMappings getMapType() {
            return this.mapType;
        }

        public int getPos() {
            return this.pos;
        }

        public boolean getIsReversed() {
            return this.isReversed;
        }
    }

    private Colors[][][] pMap;

    public Cube(int dim) {
        this.dim1 = dim;
        this.dim2 = dim;
        this.pMap = genSolved(dim1, dim2);
    }

    private Mapping genMap(Moves move) {
        Mapping map = new Mapping();
        for (Face face : move.mapType.getFaces()) {
            Colors[] set = null;
            switch (face.getOri()) {
            case Row:
                set = getRow(face.getFace(), move.pos, face.getIsRev());
                break;

            case Column:
                set = getColumn(face.getFace(), move.pos, face.getIsRev());
                break;
            }
            map.addElement(face.getFace(), set);
        }
        return map;
    }

    private Colors[][][] genSolved(int dim1, int dim2) {
        Colors[][][] cubeMap = new Colors[cubeSize][dim1][dim2];

        for (int i = 0; i < cubeMap.length; i++) { // Through every Face
            for (int j = 0; j < cubeMap[i].length; j++) { // Through every Row
                for (int k = 0; k < cubeMap[i][j].length; k++) { // Through every entry
                    cubeMap[i][j][k] = Colors.values()[i]; // Set value to cooresponding face
                }
            }
        }

        return cubeMap;
    }
    
    @Override
    public String toString() {
        return CubeDisplay.getCube(this);
    }

    public int getDim1() {
        return this.dim1;
    }

    public int getDim2() {
        return this.dim2;
    }

    public Colors[][][] getPMap() {
        return this.pMap;
    }

    public Colors[] getRow(int face, int row, boolean isReversed) {
        return isReversed ? (Colors[]) Utils.reverse(pMap[face][row]) : pMap[face][row];
    }

    public Colors[] getColumn(int face, int column, boolean isReversed) {
        Colors[] values = new Colors[dim1];
        for (int i = 0; i < dim1; i++) {
            values[i] = pMap[face][i][column];
        }
        return isReversed ? (Colors[]) Utils.reverse(values) : values;
    }

    public Colors[] getSet(int face, int set, boolean isReversed) {
        return (set > 2) ? getColumn(face, set - 3, isReversed) : getRow(face, set, isReversed);
    }
}