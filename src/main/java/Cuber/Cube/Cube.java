package Cuber.Cube;

import Cuber.Utils;
import Cuber.Cube.Color.Colors;
import Cuber.Cube.CubeMappings.Face;

public class Cube {

    private static final int cubeSize = 6; // Number of faces in a cube *DONT CHANGE THIS*
    private int dim;

    public enum Moves {

        r(CubeMappings.m, 2, 4, false), 
        m(CubeMappings.m, 1, -1, false), 
        l(CubeMappings.m, 0, 3, true),

        d(CubeMappings.e, 2, 2, false), 
        e(CubeMappings.e, 1, -1, false), 
        u(CubeMappings.e, 0, 1, true),

        f(CubeMappings.s, 2, 0, false), 
        s(CubeMappings.s, 1, -1, false), 
        b(CubeMappings.s, 0, 5, true);

        private CubeMappings mapType;
        private int pos;
        private int impl;
        private boolean isReversed;
        private Color[][] move;
        private Color[][] implMove;

        private Moves(CubeMappings mapType, int pos, int impl, boolean isReversed) {
            this.mapType = mapType;
            this.pos = pos;
            this.isReversed = isReversed;
            this.impl = impl;
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

        public void setMove(Color[][] move) {
            this.move = move;
        }

        public Color[][] getMove() {
            return this.move;
        }

        public void setImplMove(Color[][] implMove) {
            this.implMove = implMove;
        }

        public Color[][] getImplMove() {
            return this.implMove;
        }

        public int getImplicated() {
            return this.impl;
        }
    }

    private Color[][][] pMap;
    private Color[][][] eMap;

    public Cube(int dim) {
        this.dim = dim;
        
        genSolved(dim);
        genEMap(this.pMap);
        genMoves(Moves.values());
    }

    public Cube spin(Moves move) {
        performMapping(move.getMove(), move.getIsReversed());
        performMapping(move.getImplMove(), false);
        return this;
    }

    private void performMapping(Color[][] mapping, boolean isReversed) {
        Color[][] copy = deepCopyMapping(mapping);
        for (int i = 0; i < mapping.length; i++) {
            for (int j = 0; j < mapping[i].length; j++) {
                int oldPos = isReversed ? Utils.safeAdd(i, mapping.length) : Utils.safeSub(i, mapping.length);
                mapping[i][j].setColor(copy[oldPos][j].getColor());
            }
        }
    }

    private void genMoves(Moves[] moves) {
        for (Moves move : moves) {
            move.setMove(genMap(move));
            move.setImplMove(genImplMap(move));
        }
    }

    private Color[][] genMap(Moves move) {
        Face[] faces = move.mapType.getFaces();
        Color[][] map = new Color[faces.length][dim];

        for (int i = 0; i < faces.length; i++) {
            Face face = faces[i];
            Color[] set = face.getIsRow() ? getSet(face.getFace(), move.getPos(), face.getIsEntryRev(), face.getIsPosRev()) : getSet(face.getFace(), move.getPos() + 3, face.getIsEntryRev(), face.getIsPosRev());
            map[i] = set;
        }
        return map;
    }

    private Color[][] genImplMap(Moves move) {
        Color[][] mapping;
        int impl = move.getImplicated();
        if (impl >= 0) {
        mapping = new Color[4][dim];
        mapping[0] = getSet(impl, 0, false, false);
        mapping[1] = getSet(impl, 5, false, false);
        mapping[2] = getSet(impl, 2, true, false);
        mapping[3] = getSet(impl, 3, true, false);
        } else {
            mapping = new Color[0][0];
        }
        return mapping;
    }

    private void genEMap(Color[][][] pMap) {
        Color[][][] eMap = new Color[cubeSize][dim * 2][dim];

        for (int i = 0; i < eMap.length; i++) {
            for (int j = 0; j < dim; j++) {
                eMap[i][j] = pMap[i][j];
            }

            for (int j = dim; j < dim * 2; j++) {
                for (int k = 0; k < dim; k++) {
                    eMap[i][j][k] = pMap[i][k][j-dim];
                }
            }
        }

        this.eMap = eMap;
    }
    private void genSolved(int dim) {
        Color[][][] cubeMap = new Color[cubeSize][dim][dim];

        for (int i = 0; i < cubeMap.length; i++) { // Through every Face
            for (int j = 0; j < cubeMap[i].length; j++) { // Through every Row
                for (int k = 0; k < cubeMap[i][j].length; k++) { // Through every entry
                    cubeMap[i][j][k] = new Color(Colors.values()[i]); // Set value to cooresponding face
                }
            }
        }
        this.pMap = cubeMap;
    }
    
    @Override
    public String toString() {
        return CubeDisplay.getCube(this);
    }

    public int getDim() {
        return this.dim;
    }

    public Color[][][] getPMap() {
        return this.pMap;
    }

    public Color[][][] getEMap() {
        return this.eMap;
    }

    public Color[] getSet(int face, int pos, boolean isEntryRev, boolean isPosRev) {
        Color[] set = eMap[face][(isPosRev ? reverse(pos) : pos)];
        return isEntryRev ? reverse(set) : set;
    }

    private int reverse(int pos) {
        return (pos > 2) ? (reverse(pos - 3) + 3) : ((dim - 1) -pos);
    }

    private Color[] reverse(Color[] array) {
        Color[] reversedArray = new Color[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[(array.length - 1) - i] = array[i];
        }
        return reversedArray;
    }

    private Color[][] deepCopyMapping(Color[][] mapping) {
        Color[][] copy = new Color[mapping.length][];
        for (int i = 0; i < mapping.length; i++) {
            copy[i] = new Color[mapping[i].length];
            for (int j = 0; j < mapping[i].length; j++) {
                copy[i][j] = new Color(mapping[i][j].getColor());
            }
        }
        return copy;
    }
}