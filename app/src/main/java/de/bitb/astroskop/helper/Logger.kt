package de.bitb.astroskop.helper

import android.util.Log

import de.bitb.astroskop.BuildConfig

object Logger {

    private val isAllowedToLog: Boolean
        get() = BuildConfig.DEBUG

    private val callingClass: String
        get() {
            val stackTraceElements = Thread.currentThread().stackTrace
            for (i in stackTraceElements.indices) {
                if (stackTraceElements[i].className == Logger::class.java.canonicalName) {
                    try {
                        val split = stackTraceElements[i + 2].className.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        var className = split[split.size - 1]

                        if (className.contains("$")) {
                            className = className.split("\\$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                        }

                        return "(" + className + ".java:" + stackTraceElements[i + 2].lineNumber + ")"
                    } catch (e: Exception) {
                        return Logger::class.java.simpleName
                    }

                }
            }
            return Logger::class.java.simpleName
        }

    fun error(message: String?) {
        if (isAllowedToLog) {
            Log.e(callingClass, message)
        }
    }

    fun debug(message: String) {
        if (isAllowedToLog) {
            Log.d(callingClass, message)
        }
    }

    fun info(message: String) {
        if (isAllowedToLog) {
            Log.i(callingClass, message)
        }
    }

    fun verbose(message: String) {
        if (isAllowedToLog) {
            Log.v(callingClass, message)
        }
    }

    fun warn(message: String) {
        if (isAllowedToLog) {
            Log.w(callingClass, message)
        }
    }

    fun wtf(message: String) {
        if (isAllowedToLog) {
            Log.wtf(callingClass, message)
        }
    }
}