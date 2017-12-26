package de.bitb.astroskop.model.enums;

import java.util.Random;

import lombok.Getter;

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

@Getter
public enum House {
    ONE(1, ARIES),
    TWO(2, TAURUS),
    THREE(3, GEMINI),
    FOUR(4, CANCER),
    FIVE(5, LEO),
    SIX(6, VIRGO),
    SEVEN(7, LIBRA),
    EIGHT(8, SCORPIO),
    NINE(9, SAGITTARIUS),
    TEN(10, CAPRICORN),
    ELEVEN(11, AQUARIUS),
    TWELVE(12, PISCES);

    private final Integer id;
    private final Zodiac zodiac;

    House(int id, Zodiac zodiac) {
        this.id = id;
        this.zodiac = zodiac;
    }

    public static House getRandom() {
        int index = new Random().nextInt(values().length-1);
        return values()[index];
    }

}
