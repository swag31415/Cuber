package Cuber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utils {

    public static int safeSub(int i, int length) {
        return (i > 0) ? (i - 1) : (length - 1);
    }

    public static int safeAdd(int i, int length) {
        return (i < (length - 1)) ? (i + 1) : (0);
    }

    public static String arrayToString(Object[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += (i != 0) ? ", " : "";
            result += (array[i].getClass().isArray()) ? "\n" + arrayToString((Object[]) array[i])
                    : "[" + array[i].toString() + "]";
        }
        return result + "]";
    }

    public static void printToLocalFile(String fileName, String print) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));
            writer.append(print);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printToLocalFile(String fileName, Serializable print) {
        ObjectOutputStream oStream;
        try {
            oStream = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
            oStream.writeObject(print);
            oStream.flush();
            oStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getFromLocalFile(String fileName)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream oStream;
        Object out = null;

        oStream = new ObjectInputStream(new FileInputStream(new File(fileName)));
        out = oStream.readObject();
        oStream.close();

        return out;
    }
}