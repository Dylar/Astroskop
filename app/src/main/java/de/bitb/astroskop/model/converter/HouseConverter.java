package de.bitb.astroskop.model.converter;

import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Zodiac;
import io.objectbox.converter.PropertyConverter;

public class HouseConverter implements PropertyConverter<House, Integer> {

    @Override
    public House convertToEntityProperty(Integer databaseValue) {
        for (House house : House.values()) {
            if (house.getId().equals(databaseValue)) {
                return house;
            }
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(House house) {
        return house.getId();
    }

}