package de.bitb.astroskop.model;

import java.io.Serializable;

import de.bitb.astroskop.model.converter.HouseConverter;
import de.bitb.astroskop.model.converter.RulerConverter;
import de.bitb.astroskop.model.converter.ZodiacConverter;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Constellation implements Serializable{

    @Id
    protected long id;

    @Convert(converter = ZodiacConverter.class, dbType = Integer.class)
    private Zodiac zodiac;
    @Convert(converter = HouseConverter.class, dbType = Integer.class)
    private House house;
    @Convert(converter = RulerConverter.class, dbType = Integer.class)
    private Ruler ruler;

    public boolean isHouse(){
        return ruler == null || Ruler.NONE.equals(ruler);
    }

    public static Constellation create(Zodiac zodiac, House house, Ruler ruler) {
        Constellation constellation = new Constellation();
        constellation.setZodiac(zodiac);
        constellation.setHouse(house);
        constellation.setRuler(ruler);
        return constellation;
    }
}
