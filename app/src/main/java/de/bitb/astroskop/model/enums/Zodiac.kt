package de.bitb.astroskop.model.enums

import java.util.Random

import de.bitb.astroskop.helper.Logger
import lombok.Getter

@Getter
enum class Zodiac constructor(val id: Int, val house: House, val ruler: Ruler, val element: Element, val quality: Quality, val gender: Gender) {

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

    val color: Int
        get() = element.color

    companion object {

        val random: Zodiac
            get() {
                val index = Random().nextInt(values().size - 1)
                return values()[index]
            }

        operator fun get(zodiac: String): Zodiac? {
            try {
                return valueOf(zodiac.toUpperCase())
            } catch (e: IllegalArgumentException) {
                Logger.error(e.message)
            }

            return null
        }
    }
}
