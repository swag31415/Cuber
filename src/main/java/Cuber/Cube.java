package Cuber;

public class Cube {

    private static final int cubeSize = 6; // Number of faces in a cube *DONT CHANGE THIS*
    private int dim1, dim2;

    public enum Colors {
        White('W'), Blue('B'), Green('G'), Orange('O'), Red('R'), Yellow('Y');

        char sym;

        private Colors(char sym) {
            this.sym = sym;
        }
    }

    private Colors[][][] pMap;

    public Cube(int dim) {
        this.dim1 = dim;
        this.dim2 = dim;
        this.pMap = genSolved(dim1, dim2);
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
        return isReversed ? reverse(pMap[face][row]) : pMap[face][row];
    }

    public Colors[] getColumn(int face, int column, boolean isReversed) {
        Colors[] values = new Colors[dim1];
        for (int i = 0; i < dim1; i++) {
            values[i] = pMap[face][i][column];
        }
        return isReversed ? reverse(values) : values;
    }

    public Colors[] reverse(Colors[] array) {
        Colors[] reversedArray = new Colors[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[(array.length - 1) - i] = array[i];
        }
        return reversedArray;
    }
}