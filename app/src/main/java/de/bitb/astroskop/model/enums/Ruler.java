package de.bitb.astroskop.model.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.bitb.astroskop.helper.Logger;
import lombok.Getter;

import static de.bitb.astroskop.Constants.NULL_INTEGER;
import static de.bitb.astroskop.model.enums.Zodiac.*;

@Getter
public enum Ruler {
    NONE(NULL_INTEGER),
    SUN(1),
    MERCURY(2),
    VENUS(3),
    MOON(4),
    MARS(5),
    JUPITER(6),
    SATURN(7),
    URANUS(8),
    NEPTUNE(9),
    PLUTO(10);

    private final Integer id;

    Ruler(int id) {
        this.id = id;
    }

    public static Ruler getRandom() {
        int index = new Random().nextInt(values().length - 1);
        return values()[index];
    }

    public List<Zodiac> getZodiacs() {
        List<Zodiac> zodiacs = new ArrayList<>();
        switch (this) {
            case SUN:
                zodiacs.add(LEO);
                break;
            case MERCURY:
                zodiacs.add(GEMINI);
                zodiacs.add(VIRGO);
                break;
            case VENUS:
                zodiacs.add(TAURUS);
                zodiacs.add(LIBRA);
                break;
            case MOON:
                zodiacs.add(CANCER);
                break;
            case MARS:
                zodiacs.add(ARIES);
                break;
            case JUPITER:
                zodiacs.add(SAGITTARIUS);
                break;
            case SATURN:
                zodiacs.add(CAPRICORN);
                break;
            case URANUS:
                zodiacs.add(AQUARIUS);
                break;
            case NEPTUNE:
                zodiacs.add(PISCES);
                break;
            case PLUTO:
                zodiacs.add(SCORPIO);
                break;
        }
        return zodiacs;
    }

    public static Ruler get(String ruler) {
        try {
            return valueOf(ruler.toUpperCase());
        }catch (IllegalArgumentException e){
            Logger.error(e.getMessage());
        }
        return null;
    }
}
