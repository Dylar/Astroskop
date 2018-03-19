package de.bitb.astroskop.model.converter

import de.bitb.astroskop.model.enums.Zodiac
import io.objectbox.converter.PropertyConverter

class ZodiacConverter : PropertyConverter<Zodiac, Int> {

    override fun convertToEntityProperty(databaseValue: Int?): Zodiac? {
        for (zodiac in Zodiac.values()) {
            if (zodiac.id == databaseValue) {
                return zodiac
            }
        }
        return null
    }

    override fun convertToDatabaseValue(zodiac: Zodiac): Int? {
        return zodiac.id
    }

}