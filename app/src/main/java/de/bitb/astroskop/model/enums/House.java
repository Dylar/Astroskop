package de.bitb.astroskop.model.enums;

import static de.bitb.astroskop.model.enums.Zodiac.AQUARIUS;
import static de.bitb.astroskop.model.enums.Zodiac.ARIES;
import static de.bitb.astroskop.model.enums.Zodiac.CANCER;
import static de.bitb.astroskop.model.enums.Zodiac.CAPRICORN;
import static de.bitb.astroskop.model.enums.Zodiac.GEMINI;
import static de.bitb.astroskop.model.enums.Zodiac.LEO;
import static de.bitb.astroskop.model.enums.Zodiac.LIBRA;
import static de.bitb.astroskop.model.enums.Zodiac.PISCES;
import static de.bitb.astroskop.model.enums.Zodiac.SAGITTARIUS;
import static de.bitb.astroskop.model.enums.Zodiac.SCORPIO;
import static de.bitb.astroskop.model.enums.Zodiac.TAURUS;
import static de.bitb.astroskop.model.enums.Zodiac.VIRGO;

public enum House {
    ONE(ARIES),
    TWO(TAURUS),
    THREE(GEMINI),
    FOUR(CANCER),
    FIVE(LEO),
    SIX(VIRGO),
    SEVEN(LIBRA),
    EIGHT(SCORPIO),
    NINE(SAGITTARIUS),
    TEN(CAPRICORN),
    ELEVEN(AQUARIUS),
    TWELVE(PISCES);

    private final Zodiac zodiac;

    House(Zodiac zodiac) {
        this.zodiac = zodiac;
    }

    public static House getRandom() {
        return values()[((int) System.currentTimeMillis()) % 12];
    }
}
