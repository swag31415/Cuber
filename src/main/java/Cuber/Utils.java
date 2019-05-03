package Cuber;

public class Utils {

    public static Object[] reverse(Object[] array) {
        Object[] reversedArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[(array.length - 1) - i] = array[i];
        }
        return reversedArray;
    }

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
            result += (array[i].getClass().isArray()) ? "\n" + arrayToString((Object[]) array[i]) : "[" + array[i].toString() + "]";
        }
        return result + "]";
    }
}