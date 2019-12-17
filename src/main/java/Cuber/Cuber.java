package Cuber;

public class Cuber {

    private class Cube {
        int dim;
        int[] cube;

        public Cube(int dim) {
            this.dim = dim;
            this.cube = new int[dim * dim * 6];
            for (int i = 0; i < cube.length; i++) {
                this.cube[i] = i / (dim * dim);
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
                switch (i) {
                case 0:
                    for (int j = 0; j < dim; j++) {
                        for (int k = 0; k < dim; k++) {
                            print[j] += this.cube[ordr[i] * dim *dim + j * dim + k];
                            if (k != dim - 1) {
                                print[j] += ", ";
                            }
                        }
                    }
                    break;
                case 5:
                    for (int j = 0; j < dim; j++) {
                        for (int k = 0; k < dim; k++) {
                            print[j + 2 * dim] += this.cube[ordr[i] * dim *dim + j * dim + k];
                            if (k != dim - 1) {
                                print[j + 2 * dim] += ", ";
                            }
                        }
                    }
                    break;

                case 1:
                case 2:
                case 3:
                case 4:
                    for (int j = 0; j < dim; j++) {
                        for (int k = 0; k < dim; k++) {
                            print[j + dim] += this.cube[ordr[i] * dim *dim + j * dim + k];
                            if (k != dim - 1) {
                                print[j + dim] += ", ";
                            }
                        }
                        print[j + dim] += "  ";
                    }
                    break;
                }
            }

            for (String line : print) {
                System.out.println(line);
            }
        }
    }

    public Cuber() {
        Cube cube = new Cube(5);
        cube.disp();
    }
}