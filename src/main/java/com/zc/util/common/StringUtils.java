package com.zc.util.common;

public class StringUtils {

    private StringUtils() {}

    public static final String EMPTY_STRING = "";

    public static String valueOf(final char[] value) {
        return null == value ? null : String.valueOf(value);
    }

    public static boolean equals(final CharSequence src, final CharSequence tag) {
        if (src == tag) {
            return true;
        }

        if (null == src || tag == null) {
            return false;
        }

        if (src.length() != tag.length()) {
            return false;
        }

        if ((src instanceof String) && (tag instanceof String)) {
            return src.equals(tag);
        }

        int len = src.length();
        for (int index = 0; index < len; index++) {
            if (src.charAt(index) != tag.charAt(index)) {
                return false;
            }
        }

        return true;
    }

    public static String truncate(final String str, final int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    public static String truncate(final String str, final int offset, final int maxWidth) {
        if (null == str) {
            return null;
        }
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (maxWidth < 0) {
            throw new IllegalArgumentException("maxWith cannot be negative");
        }

        int len = str.length();
        if (offset > len) {
            return EMPTY_STRING;
        }

        if (len >= maxWidth) {
            int newLen = offset + maxWidth > len ? len : offset + maxWidth;
            return str.substring(offset, newLen);
        }

        return str.substring(offset);
    }

    public static String trimToEmpty(final String str) {
        String trim = trim(str);
        return isEmpty(trim) ? EMPTY_STRING : trim;
    }

    public static String trimToNull(final String str) {
        String trim = trim(str);
        return isEmpty(trim) ? null : trim;
    }

    public static String trim(final String str) {
        return null == str ? null : str.trim();
    }

    public static boolean isAllBlank(CharSequence... charSequences) {
        if (ArrayUtils.isEmpty(charSequences)) {
            return true;
        }

        for (CharSequence cs : charSequences) {
            if (isNotBlank(cs)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNoneBlank(CharSequence... charSequences) {
        return !isAnyBlank(charSequences);
    }

    public static boolean isAnyBlank(CharSequence... charSequences) {
        if (ArrayUtils.isEmpty(charSequences)) {
            return false;
        }

        for (CharSequence cs : charSequences) {
            if (isBlank(cs)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotBlank(CharSequence charSequence) {
        return !isBlank(charSequence);
    }

    public static boolean isBlank(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return true;
        }

        int len = charSequence.length();

        for (int index = 0; index < len; index++) {
            if (!Character.isWhitespace(charSequence.charAt(index))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAllEmpty(CharSequence... charSequences) {
        if (ArrayUtils.isEmpty(charSequences)) {
            return true;
        }

        for (CharSequence cs : charSequences) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNoneEmpty(CharSequence... charSequences) {
        return !isAnyEmpty(charSequences);
    }

    public static boolean isAnyEmpty(CharSequence... charSequences) {
        if (ArrayUtils.isEmpty(charSequences)) {
            return false;
        }

        for (CharSequence cs : charSequences) {
            if (isEmpty(cs)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return  null == charSequence || charSequence.length() == 0;
    }

    public static long longValue(String value) {
        return longValue(value, 0L);
    }

    public static double doubleValue(String value) {
        return doubleValue(value, 0.0);
    }

    public static float floatValue(String value) {
        return floatValue(value, 0.0f);
    }

    public static short shortValue(String value) {
        return shortValue(value, (short) 0);
    }

    public static boolean booleanValue(String value) {
        return booleanValue(value, false);
    }

    public static int intValue(String value) {
        return intValue(value, 0);
    }

    public static double doubleValue(String value, double defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static long longValue(String value, long defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static float floatValue(String value, float defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Float.parseFloat(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static short shortValue(String value, short defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Short.parseShort(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static boolean booleanValue(String value, boolean defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Boolean.parseBoolean(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static int intValue(String value, int defaultValue) {
        String val = trim(value);
        if (null == val) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return defaultValue;
    }

    public static int length(CharSequence charSequence) {
        return isEmpty(charSequence) ? 0 : charSequence.length();
    }

}
