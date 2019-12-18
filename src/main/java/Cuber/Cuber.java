package Cuber;

import java.util.Random;

public class Cuber {

    enum Slice {
        e, m, s, er, mr, sr;
    }

    private class Cube {
        int dim;
        int[] cube;

        public Cube(int dim) {
            this.dim = dim;
            this.cube = new int[dim * dim * 6];
            for (int i = 0; i < cube.length; i++) {
                this.cube[i] = 
                i / (dim * dim);
                // new Random().nextInt(6);
            }
        }

        public void turn(Slice s, int ind) {
            int[] temp = this.cube.clone();
            switch (s) {
            case e:
                int[] ordr = {3, 0, 4, 5};
                for (int i = 0; i < ordr.length; i++) {
                    for (int j = 0; j < dim; j++) {
                        this.cube[ordr[i] * dim * dim + ind * dim + j] = temp[ordr[(i + 1) % ordr.length] * dim * dim + ind * dim + j];
                    }
                }
                if (ind == 0) {
                    this.rotateFace(1, true);
                } else if (ind == dim - 1) {
                    this.rotateFace(2, false);
                }
                break;
            case m:
                int[] ordr2 = {1, 0, 2, 5};
                for (int i = 0; i < ordr2.length; i++) {
                    for (int j = 0; j < dim; j++) {
                        this.cube[ordr2[i] * dim * dim + j * dim + ind] = temp[ordr2[(i + 1) % ordr2.length] * dim * dim + j * dim + ind];
                    }
                }
                if (ind == 0) {
                    this.rotateFace(3, true);
                } else if (ind == dim - 1) {
                    this.rotateFace(4, false);
                }
                break;
            case s:
                break;
            case er:
                break;
            case mr:
                break;
            case sr:
                break;
            }
        }

        private void rotateFace(int face, boolean isClockwise) {
            int[] temp = this.cube.clone();
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    this.cube[face * dim * dim + i * dim + j] = temp[face * dim * dim + (isClockwise ? (dim - j - 1) * dim + i : j * dim + (dim - i - 1))];
                }
            }
        }

        public void disp() {
            int ordr[] = { 1, 3, 0, 4, 5, 2 };
            String[] print = new String[dim * 3];

            for (int i = 0; i < print.length; i++) {
                print[i] = "";
                if ((i < dim) || (i >= dim * 2)) {
                    for (int j = 0; j < dim; j++) {
                        print[i] += "   ";
                    }
                }
            }

            for (int i = 0; i < ordr.length; i++) {
                for (int j = 0; j < dim; j++) {
                    for (int k = 0; k < dim; k++) {
                        int line = -1;
                        if (i == 0) {
                            line = j;
                        } else if (i == 5) {
                            line = j + 2 * dim;
                        } else {
                            line = j + dim;
                        }

                        print[line] += this.cube[ordr[i] * dim * dim + j * dim + (i == ordr.length - 2 ? (dim - k - 1) : k)];
                        if (k != dim - 1) {
                            print[line] += ", ";
                        } else {
                            print[line] += "  ";
                        }
                    }
                }
            }

            for (String line : print) {
                System.out.println(line);
            }
        }
    }

    public Cuber() {
        Cube cube = new Cube(2);
        cube.disp();
        cube.turn(Slice.m, 0);
        System.out.println();
        cube.disp();
        cube.turn(Slice.e, 0);
        System.out.println();
        cube.disp();
    }
}