package de.bitb.astroskop.utils

import android.text.format.DateFormat

class DateUtils {


    fun formatMillisToReadable(updateTimestamp: Long): String {
        return DateFormat.format("dd.MM.yyyy HH:mm", java.util.Date(updateTimestamp)).toString()
    }

    companion object {

        private val ONE_MIN_IN_MILLI = (1000 * 60).toLong()
        val FIFTEEN_MIN_IN_MILLI = ONE_MIN_IN_MILLI * 15
        private val ONE_HOUR_IN_MILLI = ONE_MIN_IN_MILLI * 60
        val ONE_DAY_IN_MILLI = ONE_HOUR_IN_MILLI * 24
        val ONE_MONTH_IN_MILLI = ONE_DAY_IN_MILLI * 30
    }
}
