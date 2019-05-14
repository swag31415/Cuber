package Cuber;

import java.util.HashMap;

public class CuberLogger {
    Cuber cuber;
    String fileName;

    String readableExtension = ".csv";
    String logExtension = ".cuberLog";

    public CuberLogger(Cuber cuber) {
        this.cuber = cuber;
        this.fileName = "CuberLog " + cuber.getCubeDim() + "x" + cuber.getCubeDim();
    }

    public void log() {
        Utils.printToLocalFile(fileName + readableExtension, this.cuber.toString());
        System.out.println("saved to file");
        Utils.printToLocalFile(fileName + logExtension, this.cuber.getAlgMap());
        System.out.println("logging complete");
    }

    public boolean pullFromLog() {
        try {
            HashMap<String, Integer> logMap = (HashMap<String, Integer>) Utils.getFromLocalFile(fileName + logExtension);
            this.cuber.algMap.putAll(logMap);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

}