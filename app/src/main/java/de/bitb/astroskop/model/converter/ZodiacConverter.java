package de.bitb.astroskop.model.converter;

import de.bitb.astroskop.model.enums.Zodiac;
import io.objectbox.converter.PropertyConverter;

public class ZodiacConverter implements PropertyConverter<Zodiac, Integer> {

    @Override
    public Zodiac convertToEntityProperty(Integer databaseValue) {
        for (Zodiac zodiac : Zodiac.values()) {
            if (zodiac.getId().equals(databaseValue)) {
                return zodiac;
            }
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(Zodiac zodiac) {
        return zodiac.getId();
    }

}