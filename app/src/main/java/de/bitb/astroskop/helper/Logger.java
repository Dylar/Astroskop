package de.bitb.astroskop.helper;

import android.util.Log;

import de.bornholdtlee.snh.BuildConfig;

public final class Logger {

    private Logger() {
    }

    public static void error(String message) {
        if (isAllowedToLog()) {
            Log.e(getCallingClass(), message);
        }
    }

    public static void debug(String message) {
        if (isAllowedToLog()) {
            Log.d(getCallingClass(), message);
        }
    }

    public static void info(String message) {
        if (isAllowedToLog()) {
            Log.i(getCallingClass(), message);
        }
    }

    public static void verbose(String message) {
        if (isAllowedToLog()) {
            Log.v(getCallingClass(), message);
        }
    }

    public static void warn(String message) {
        if (isAllowedToLog()) {
            Log.w(getCallingClass(), message);
        }
    }

    public static void wtf(String message) {
        if (isAllowedToLog()) {
            Log.wtf(getCallingClass(), message);
        }
    }

    private static boolean isAllowedToLog() {
        return BuildConfig.DEBUG;
    }

    private static String getCallingClass() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            if (stackTraceElements[i].getClassName().equals(Logger.class.getCanonicalName())) {
                try {
                    String[] split = stackTraceElements[i + 2].getClassName().split("\\.");
                    String className = split[split.length - 1];

                    if (className.contains("$")) {
                        className = className.split("\\$")[0];
                    }

                    return "(" + className + ".java:" + stackTraceElements[i + 2].getLineNumber() + ")";
                } catch (Exception e) {
                    return Logger.class.getSimpleName();
                }
            }
        }
        return Logger.class.getSimpleName();
    }
}