import Cuber.Cuber;
import Cuber.Comparators.ColorDiff;
import Cuber.Cube.Moves;
import Cuber.Generators.Rand3x3Gen;

public class Main {

    public Main() {

    }

    public static void main(int[] args) {
        int iterations = args[0];
        int cycles = 100;
        int subIterations = (iterations - (iterations % cycles)) / cycles;
        int remIterations = (iterations % cycles);
        
        Cuber cuber = new Cuber(args[1], args[2], new Rand3x3Gen(Moves.values()), new ColorDiff());
        System.out.println(cuber.pullFromLog() ? "Successfully pulled from log" : "Failed to pull from log");

        for (int i = 1; i <= cycles; i++) {
            cuber.findAlgs(subIterations);
            System.out.println(((i * subIterations) / ((double) iterations)) * 100 + "% done");
        }
        cuber.findAlgs(remIterations);
        System.out.println("Best Alg Err: " + cuber.getBestAlgErr());
        cuber.log();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int argsReq = 3;
        if (args.length == argsReq) { 
            int[] mArgs = new int[argsReq];
            for (int i = 0; i < mArgs.length; i++) {
                mArgs[i] = Integer.parseInt(args[i]);
            }
            main(mArgs);
        } else {
            System.err.println("Enter 3 ints next time, (interations, cube dimension, algorithm length)");
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}