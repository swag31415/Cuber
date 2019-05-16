package GUI;

import Cuber.Cuber;
import Cuber.Molds.Logger;

public class Generator implements Runnable {

    Cuber cuber;
    Logger logger;

    int iterations;
    int algLength;

    Printer printer;

    public interface Printer {
        public void print(String str);
    }

    public Generator(Cuber cuber, int iterations, int algLength, Printer printer, Logger logger) {
        this.cuber = cuber;
        this.iterations = iterations;
        this.algLength = algLength;
        this.printer = printer;
        this.logger = logger;
    }

    @Override
    public void run() {
        int cycles = 100;
        int subIterations = (iterations - (iterations % cycles)) / cycles;
        int remIterations = (iterations % cycles);

        for (int i = 1; i <= cycles; i++) {
            cuber.findAlgs(subIterations, algLength);
            printer.print(((i * subIterations) / ((double) iterations)) * 100 + "% done");
        }
        cuber.findAlgs(remIterations, algLength);
        printer.print("Best Alg Err: " + cuber.getBestAlgErr());

        logger.log();
    }
}