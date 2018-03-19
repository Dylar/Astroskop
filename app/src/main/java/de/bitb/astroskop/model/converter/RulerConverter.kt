package de.bitb.astroskop.model.converter

import de.bitb.astroskop.model.enums.Ruler
import io.objectbox.converter.PropertyConverter

class RulerConverter : PropertyConverter<Ruler, Int> {

    override fun convertToEntityProperty(databaseValue: Int?): Ruler? {
        for (ruler in Ruler.values()) {
            if (ruler.id == databaseValue) {
                return ruler
            }
        }
        return null
    }

    override fun convertToDatabaseValue(ruler: Ruler): Int? {
        return ruler.id
    }

}