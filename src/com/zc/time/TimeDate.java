package com.zc.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeDate {

    public static String timeString(String pattern) {
        return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
    }

    public static String msToChinese(long ms) {
        return msToString(ms, "天", "时", "分", "秒");
    }

    public static String msToString(long ms) {
        return msToString(ms, "D", "H", "m", "s");
    }

    public static String msToString(long ms, String second) {
        return msToString(ms, "D", "H", "m", second);
    }

    public static String msToString(long ms, String minute, String second) {
        return msToString(ms, "D", "H", minute, second);
    }

    public static String msToString(long ms, String hour, String minute, String second) {
        return msToString(ms, "D", hour, minute, second);
    }

    public static String msToString(long ms, String date, String hour, String minute, String second) {
        if (ms >= 0 && ms < 1000) {
            return "0." + String.valueOf(1000 + ms).substring(1) + second;
        }
        if (ms >= 1000 && ms < 60000) {
            return String.valueOf(ms / 1000) + "."
                    + String.valueOf(ms % 1000 + 1000).substring(1)
                    + second;
        }
        if (ms >= 60000 && ms < 3600000) {
            return String.valueOf(ms / 60000) + minute
                    + String.valueOf(ms % 60000 / 1000) + "."
                    + String.valueOf(ms % 1000 + 1000).substring(1)
                    + second;
        }
        if (ms >= 3600000 && ms < 216000000) {
            return String.valueOf(ms / 3600000) + hour
                    + String.valueOf(ms % 3600000 / 60000) + minute
                    + String.valueOf(ms % 60000 / 1000) + "."
                    + String.valueOf(ms % 1000 + 1000).substring(1)
                    + second;
        }

        return String.valueOf(ms / 216000000) + date
                + String.valueOf(ms % 216000000 / 3600000) + hour
                + String.valueOf(ms % 3600000 / 60000) + minute
                + String.valueOf(ms % 60000 / 1000) + "."
                + String.valueOf(ms % 1000 + 1000).substring(1)
                + second;
    }

}
