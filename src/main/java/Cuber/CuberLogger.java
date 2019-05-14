package Cuber;

import java.util.HashMap;

public class CuberLogger {

    private Cuber cuber;
    private String fileName;

    private static final String readableExtension = ".csv";
    private static final String logExtension = ".cuberLog";

    public CuberLogger(Cuber cuber) {
        this.cuber = cuber;
        this.fileName = "CuberLog " + cuber.getCubeDim() + "x" + cuber.getCubeDim();
    }

    public void log() {
        Utils.printToLocalFile(fileName + readableExtension, this.cuber.toString());
        Utils.printToLocalFile(fileName + logExtension, this.cuber.getAlgMap());
    }

    @SuppressWarnings("unchecked") // Catch exceptions and campaign for refined generics in JAVA
    public boolean pullFromLog() {
        try {
            HashMap<String, Integer> logMap = (HashMap<String, Integer>) Utils.getFromLocalFile(fileName + logExtension);
            this.cuber.getAlgMap().putAll(logMap);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

}