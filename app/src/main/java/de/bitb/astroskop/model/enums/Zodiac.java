package de.bitb.astroskop.model.enums;

import java.util.Random;

import lombok.Getter;

@Getter
public enum Zodiac {

    ARIES(House.ONE, Ruler.MARS, Element.FIRE, Quality.CARDINAL, Gender.MALE),
    TAURUS(House.TWO, Ruler.VENUS, Element.EARTH, Quality.FIXED, Gender.FEMALE),
    GEMINI(House.THREE, Ruler.MERCURY, Element.AIR, Quality.MUTABLE, Gender.MALE),
    CANCER(House.FOUR, Ruler.MOON, Element.WATER, Quality.CARDINAL, Gender.FEMALE),
    LEO(House.FIVE, Ruler.SUN, Element.FIRE, Quality.FIXED, Gender.MALE),
    VIRGO(House.SIX, Ruler.MERCURY, Element.EARTH, Quality.MUTABLE, Gender.FEMALE),
    LIBRA(House.SEVEN, Ruler.VENUS, Element.AIR, Quality.CARDINAL, Gender.MALE),
    SCORPIO(House.EIGHT, Ruler.PLUTO, Element.WATER, Quality.FIXED, Gender.FEMALE),
    SAGITTARIUS(House.NINE, Ruler.JUPITER, Element.FIRE, Quality.MUTABLE, Gender.MALE),
    CAPRICORN(House.TEN, Ruler.SATURN, Element.EARTH, Quality.CARDINAL, Gender.FEMALE),
    AQUARIUS(House.ELEVEN, Ruler.URANUS, Element.AIR, Quality.FIXED, Gender.MALE),
    PISCES(House.TWELVE, Ruler.NEPTUN, Element.WATER, Quality.MUTABLE, Gender.FEMALE);

    private final House house;
    private final Ruler ruler;
    private final Element element;
    private final Quality quality;
    private final Gender gender;

    Zodiac(House house, Ruler ruler, Element element, Quality quality, Gender gender) {
        this.house = house;
        this.ruler = ruler;
        this.element = element;
        this.quality = quality;
        this.gender = gender;
    }

    public static Zodiac getRandom() {
        return values()[((int) System.currentTimeMillis()) % 12];
    }
}
