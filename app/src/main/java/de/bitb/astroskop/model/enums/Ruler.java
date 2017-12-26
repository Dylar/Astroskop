package de.bitb.astroskop.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.Getter;

import static de.bitb.astroskop.model.enums.Zodiac.*;

@Getter
public enum Ruler {
    SUN(1, LEO),
    MERCURY(2, GEMINI, VIRGO),
    VENUS(3, TAURUS, LIBRA),
    MOON(4, CANCER),
    MARS(5, ARIES),
    JUPITER(6, SAGITTARIUS),
    SATURN(7, CAPRICORN),
    URANUS(8, AQUARIUS),
    NEPTUN(9, PISCES),
    PLUTO(10, SCORPIO);

    private final Integer id;
    private final List<Zodiac> zodiacs;

    Ruler(int id, Zodiac... zodiacs) {
        this.id = id;
        this.zodiacs = Arrays.asList(zodiacs);
    }

    public static Ruler getRandom() {
        int index = new Random().nextInt(values().length-1);
        return values()[index];
    }

}
