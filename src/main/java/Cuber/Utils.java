package Cuber;

public class Utils {

    public static Object[] reverse(Object[] array) {
        Object[] reversedArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[(array.length - 1) - i] = array[i];
        }
        return reversedArray;
    }
}