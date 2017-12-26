package de.bitb.astroskop.model;


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

@Getter
@Setter
@Entity
public class Circumstance {
    @Id
    protected long id;

    @Convert(converter = ZodiacConverter.class, dbType = Integer.class)
    private Zodiac zodiac;
    @Convert(converter = HouseConverter.class, dbType = Integer.class)
    private House house;
    @Convert(converter = RulerConverter.class, dbType = Integer.class)
    private Ruler ruler;

}
