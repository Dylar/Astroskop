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
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12);

    private final Integer id;

    House(int id) {
        this.id = id;
    }

    public static House getRandom() {
        int index = new Random().nextInt(values().length-1);
        return values()[index];
    }

    public int getColor() {
        Zodiac zodiac = getZodiac();
        return zodiac.getColor();
    }

    public Zodiac getZodiac() {
        switch (this){
            case ONE:
                return ARIES;
            case TWO:
                return TAURUS;
            case THREE:
                return GEMINI;
            case FOUR:
                return CANCER;
            case FIVE:
                return LEO;
            case SIX:
                return VIRGO;
            case SEVEN:
                return LIBRA;
            case EIGHT:
                return SCORPIO;
            case NINE:
                return SAGITTARIUS;
            case TEN:
                return CAPRICORN;
            case ELEVEN:
                return AQUARIUS;
            case TWELVE:
                return PISCES;
        }
        return null;
    }

    public static House get(int id) {
        for (House house : values()) {
            if(house.getId().equals(id)){
                return house;
            }
        }
        return null;
    }
}
