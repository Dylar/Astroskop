package de.bornholdtlee.snh.utils;

import android.text.format.DateFormat;

public class DateUtils {

    private static final long ONE_MIN_IN_MILLI = 1000 * 60;
    public static final long FIFTEEN_MIN_IN_MILLI = ONE_MIN_IN_MILLI * 15;
    private static final long ONE_HOUR_IN_MILLI = ONE_MIN_IN_MILLI * 60;
    public static final long ONE_DAY_IN_MILLI = ONE_HOUR_IN_MILLI * 24;
    public static final long ONE_MONTH_IN_MILLI = ONE_DAY_IN_MILLI * 30;


    public String formatMillisToReadable(long updateTimestamp) {
        return DateFormat.format("dd.MM.yyyy HH:mm", new java.util.Date(updateTimestamp)).toString();
    }
}
