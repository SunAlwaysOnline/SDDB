package edu.usts.sddb.util;

public class MathUtil {

    /**
     * 取数组中最大数，并返回其下标
     */
    public static int getMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 取数组中最小数，并返回其下标
     */
    public static int getMin(int[] arr) {
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[min]) {
                min = i;
            }
        }
        return min;
    }
}
