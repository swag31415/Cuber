package Cuber.Cube;

import Cuber.Utils;
import Cuber.Cube.Color.Colors;
import Cuber.Cube.Mappings.Face;

public class Cube {

    public static final int cubeSize = 6; // Number of faces in a cube *DONT CHANGE THIS*
    private int dim;

    private Color[][][] pMap;
    private Color[][][] eMap;

    public Cube(int dim) {
        this.dim = dim;
        
        genSolved(dim);
        genEMap(this.pMap);
        genMoves(Moves.values());
    }

    public Cube spin(Moves move, int count) {
        count = count % 4;
        count += (count > 0) ? 0 : 4;
        performMapping(move.getMove(), move.getIsReversed());
        performMapping(move.getImplMove(), false);
        return (count == 1) ? this : this.spin(move, count - 1);
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
        Face[] faces = move.getMapType().getFaces();
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
        return Display.getCube(this);
    }

    public int getDim() {
        return this.dim;
    }

    public Color[][][] getPMap() {
        return this.pMap;
    }

    public void setPMap(Color[][][] pMap) {
        this.pMap = pMap;
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