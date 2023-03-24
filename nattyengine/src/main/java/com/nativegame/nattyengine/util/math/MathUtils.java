package com.nativegame.nattyengine.util.math;

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

    public static float[] fill(float value, int size) {
        float[] values = new float[size];
        Arrays.fill(values, value);

        return values;
    }

    public static double[] fill(double value, int size) {
        double[] values = new double[size];
        Arrays.fill(values, value);

        return values;
    }

    public static int[] fill(int value, int size) {
        int[] values = new int[size];
        Arrays.fill(values, value);

        return values;
    }

    public static long[] fill(long value, int size) {
        long[] values = new long[size];
        Arrays.fill(values, value);

        return values;
    }
    //========================================================

}
