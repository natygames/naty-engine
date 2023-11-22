package com.nativegame.natyengine.util;

import java.util.Arrays;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class MathUtils {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private MathUtils() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static boolean isEven(int i) {
        return i % 2 == 0;
    }

    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static float sum(float[] values) {
        float sum = 0;

        int size = values.length;
        for (int i = 0; i < size; i++) {
            sum += values[i];
        }

        return sum;
    }

    public static double sum(double[] values) {
        double sum = 0;

        int size = values.length;
        for (int i = 0; i < size; i++) {
            sum += values[i];
        }

        return sum;
    }

    public static int sum(int[] values) {
        int sum = 0;

        int size = values.length;
        for (int i = 0; i < size; i++) {
            sum += values[i];
        }

        return sum;
    }

    public static long sum(long[] values) {
        long sum = 0;

        int size = values.length;
        for (int i = 0; i < size; i++) {
            sum += values[i];
        }

        return sum;
    }

    public static float[] fill(float value, int count) {
        float[] values = new float[count];
        Arrays.fill(values, value);

        return values;
    }

    public static double[] fill(double value, int count) {
        double[] values = new double[count];
        Arrays.fill(values, value);

        return values;
    }

    public static int[] fill(int value, int count) {
        int[] values = new int[count];
        Arrays.fill(values, value);

        return values;
    }

    public static long[] fill(long value, int count) {
        long[] values = new long[count];
        Arrays.fill(values, value);

        return values;
    }
    //========================================================

}
