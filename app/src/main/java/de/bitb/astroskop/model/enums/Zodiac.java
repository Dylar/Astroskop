package de.bitb.astroskop.model.enums;

import java.util.Random;

import lombok.Getter;

@Getter
public enum Zodiac {

    ARIES(1, House.ONE, Ruler.MARS, Element.FIRE, Quality.CARDINAL, Gender.MALE),
    TAURUS(2, House.TWO, Ruler.VENUS, Element.EARTH, Quality.FIXED, Gender.FEMALE),
    GEMINI(3, House.THREE, Ruler.MERCURY, Element.AIR, Quality.MUTABLE, Gender.MALE),
    CANCER(4, House.FOUR, Ruler.MOON, Element.WATER, Quality.CARDINAL, Gender.FEMALE),
    LEO(5, House.FIVE, Ruler.SUN, Element.FIRE, Quality.FIXED, Gender.MALE),
    VIRGO(6, House.SIX, Ruler.MERCURY, Element.EARTH, Quality.MUTABLE, Gender.FEMALE),
    LIBRA(7, House.SEVEN, Ruler.VENUS, Element.AIR, Quality.CARDINAL, Gender.MALE),
    SCORPIO(8, House.EIGHT, Ruler.PLUTO, Element.WATER, Quality.FIXED, Gender.FEMALE),
    SAGITTARIUS(9, House.NINE, Ruler.JUPITER, Element.FIRE, Quality.MUTABLE, Gender.MALE),
    CAPRICORN(10, House.TEN, Ruler.SATURN, Element.EARTH, Quality.CARDINAL, Gender.FEMALE),
    AQUARIUS(11, House.ELEVEN, Ruler.URANUS, Element.AIR, Quality.FIXED, Gender.MALE),
    PISCES(12, House.TWELVE, Ruler.NEPTUNE, Element.WATER, Quality.MUTABLE, Gender.FEMALE);

    private final Integer id;
    private final House house;
    private final Ruler ruler;
    private final Element element;
    private final Quality quality;
    private final Gender gender;

    Zodiac(int id, House house, Ruler ruler, Element element, Quality quality, Gender gender) {
        this.id = id;
        this.house = house;
        this.ruler = ruler;
        this.element = element;
        this.quality = quality;
        this.gender = gender;
    }

    public static Zodiac getRandom() {
        int index = new Random().nextInt(values().length-1);
        return values()[index];
    }

    public int getColor() {
        return getElement().getColor();
    }

}
