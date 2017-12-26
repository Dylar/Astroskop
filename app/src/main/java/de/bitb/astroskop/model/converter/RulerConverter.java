package de.bitb.astroskop.model.converter;

import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import io.objectbox.converter.PropertyConverter;

public class RulerConverter implements PropertyConverter<Ruler, Integer> {

    @Override
    public Ruler convertToEntityProperty(Integer databaseValue) {
        for (Ruler ruler : Ruler.values()) {
            if (ruler.getId().equals(databaseValue)) {
                return ruler;
            }
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(Ruler ruler) {
        return ruler.getId();
    }

}