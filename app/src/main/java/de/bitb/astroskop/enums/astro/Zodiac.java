package de.bitb.astroskop.enums.astro;

import lombok.Getter;

import static de.bitb.astroskop.enums.astro.Element.*;
import static de.bitb.astroskop.enums.astro.Gender.*;
import static de.bitb.astroskop.enums.astro.House.*;
import static de.bitb.astroskop.enums.astro.Quality.*;
import static de.bitb.astroskop.enums.astro.Ruler.*;

@Getter
public enum Zodiac {
    ARIES(ONE, MARS, FIRE, CARDINAL, MALE),
    TAURUS(TWO, VENUS, EARTH, FIXED, FEMALE),
    GEMINI(THREE, MERCURY, AIR, MUTABLE, MALE),
    CANCER(FOUR, MOON, WATER, CARDINAL, FEMALE),
    LEO(FIVE, SUN, FIRE, FIXED, MALE),
    VIRGO(SIX, MERCURY, EARTH, MUTABLE, FEMALE),
    LIBRA(SEVEN, VENUS, AIR, CARDINAL, MALE),
    SCORPIO(EIGHT, PLUTO, WATER, FIXED, FEMALE),
    SAGITTARIUS(NINE, JUPITER, FIRE, MUTABLE, MALE),
    CAPRICORN(TEN, SATURN, EARTH, CARDINAL, FEMALE),
    AQUARIUS(ELEVEN, URANUS, AIR, FIXED, MALE),
    PISCES(TWELVE, NEPTUN, WATER, MUTABLE, FEMALE);

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
}
