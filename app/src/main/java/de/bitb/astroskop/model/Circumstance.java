package de.bitb.astroskop.model;


import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
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

    private Zodiac zodiac;
    private House house;
    private Ruler ruler;

}
