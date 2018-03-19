package de.bitb.astroskop.model.converter

import android.text.TextUtils

import java.util.ArrayList

import io.objectbox.converter.PropertyConverter

class StringListConverter : PropertyConverter<List<String>, String> {

    override fun convertToEntityProperty(databaseValue: String): List<String> {
        val split = databaseValue.split(SEPERATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<String>()
        for (aSplit in split) {
            result.add(aSplit)
        }
        return result
    }

    override fun convertToDatabaseValue(strings: List<String>): String {
        var result = ""
        for (integer in strings) {
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
