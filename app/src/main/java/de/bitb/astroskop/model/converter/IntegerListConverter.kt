package de.bitb.astroskop.model.converter

import android.text.TextUtils

import java.util.ArrayList

import io.objectbox.converter.PropertyConverter

class IntegerListConverter : PropertyConverter<List<Int>, String> {

    override fun convertToEntityProperty(databaseValue: String): List<Int> {
        val split = databaseValue.split(SEPERATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<Int>()
        for (aSplit in split) {
            result.add(Integer.valueOf(aSplit))
        }
        return result
    }

    override fun convertToDatabaseValue(integers: List<Int>): String {
        var result = ""
        for (integer in integers) {
            if (!TextUtils.isEmpty(result)) {
                result += SEPERATOR
            }
            result += integer
        }
        return result
    }

    companion object {

        private val SEPERATOR = ";"
    }

}
