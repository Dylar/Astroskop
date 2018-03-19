package de.bitb.astroskop.model.converter

import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Zodiac
import io.objectbox.converter.PropertyConverter

class HouseConverter : PropertyConverter<House, Int> {

    override fun convertToEntityProperty(databaseValue: Int?): House? {
        for (house in House.values()) {
            if (house.id == databaseValue) {
                return house
            }
        }
        return null
    }

    override fun convertToDatabaseValue(house: House): Int? {
        return house.id
    }

}