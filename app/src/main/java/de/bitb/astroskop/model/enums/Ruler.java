package de.bitb.astroskop.model.enums;

import java.util.Arrays;
import java.util.List;

import static de.bitb.astroskop.model.enums.Zodiac.*;

public enum Ruler {
    SUN(LEO),
    MERCURY(GEMINI,VIRGO),
    VENUS(TAURUS,LIBRA),
    MOON(CANCER),
    MARS(ARIES),
    JUPITER(SAGITTARIUS),
    SATURN(CAPRICORN),
    URANUS(AQUARIUS),
    NEPTUN(PISCES),
    PLUTO(SCORPIO);

    private final List<Zodiac> zodiacs;

    Ruler(Zodiac... zodiacs) {
        this.zodiacs = Arrays.asList(zodiacs);
    }

    public static Ruler getRandom() {
        return values()[((int) System.currentTimeMillis()) % 10];
    }
}
