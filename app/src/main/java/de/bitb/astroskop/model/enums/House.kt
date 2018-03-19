package de.bitb.astroskop.model.enums

import de.bitb.astroskop.Constants.Companion.NULL_INTEGER
import java.util.Random

import de.bitb.astroskop.helper.Logger
import lombok.Getter

import de.bitb.astroskop.model.enums.Zodiac.AQUARIUS
import de.bitb.astroskop.model.enums.Zodiac.ARIES
import de.bitb.astroskop.model.enums.Zodiac.CANCER
import de.bitb.astroskop.model.enums.Zodiac.CAPRICORN
import de.bitb.astroskop.model.enums.Zodiac.GEMINI
import de.bitb.astroskop.model.enums.Zodiac.LEO
import de.bitb.astroskop.model.enums.Zodiac.LIBRA
import de.bitb.astroskop.model.enums.Zodiac.PISCES
import de.bitb.astroskop.model.enums.Zodiac.SAGITTARIUS
import de.bitb.astroskop.model.enums.Zodiac.SCORPIO
import de.bitb.astroskop.model.enums.Zodiac.TAURUS
import de.bitb.astroskop.model.enums.Zodiac.VIRGO

@Getter
enum class House constructor(val id: Int?) {
    NONE(NULL_INTEGER),
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

    val color: Int
        get() {
            val zodiac = zodiac
            return zodiac!!.color
        }

    val zodiac: Zodiac?
        get() {
            when (this) {
                ONE -> return ARIES
                TWO -> return TAURUS
                THREE -> return GEMINI
                FOUR -> return CANCER
                FIVE -> return LEO
                SIX -> return VIRGO
                SEVEN -> return LIBRA
                EIGHT -> return SCORPIO
                NINE -> return SAGITTARIUS
                TEN -> return CAPRICORN
                ELEVEN -> return AQUARIUS
                TWELVE -> return PISCES
            }
            return null
        }

    companion object {

        val random: House
            get() {
                val index = Random().nextInt(values().size - 1)
                return values()[index]
            }

        operator fun get(id: Int): House? {
            for (house in values()) {
                if (house.id == id) {
                    return house
                }
            }
            return null
        }

        operator fun get(house: String): House? {
            try {
                return valueOf(house.toUpperCase())
            } catch (e: IllegalArgumentException) {
                Logger.error(e.message)
            }

            return null
        }
    }
}
