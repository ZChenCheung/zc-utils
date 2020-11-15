package com.zc.util.common;

import java.lang.reflect.Array;

public class ArrayUtils {

    public static final int INDEX_NOT_FOUND = -1;

    public static int indexOf(final Object[] array, int offset, final Object obj) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }

        if (offset < 0) {
            return 0;
        }

        for (int index = 0; index < getLength(array); index++) {
            if (array[index].equals(obj)) {
                return index;
            }
        }

        return INDEX_NOT_FOUND;
    }

    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(final Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(final long[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final double[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final float[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final int[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final short[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final boolean[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final byte[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final char[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }


}
