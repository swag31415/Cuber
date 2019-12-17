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

                        print[line] += this.cube[ordr[i] * dim * dim + j * dim + k];
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
        Cube cube = new Cube(5);
        cube.disp();
    }
}