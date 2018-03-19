package de.bitb.astroskop.model.enums

import de.bitb.astroskop.Constants.Companion.NULL_INTEGER
import java.util.ArrayList
import java.util.Random

import de.bitb.astroskop.helper.Logger
import lombok.Getter

import de.bitb.astroskop.model.enums.Zodiac.*

@Getter
enum class Ruler private constructor(val id: Int?) {
    NONE(NULL_INTEGER),
    SUN(1),
    MERCURY(2),
    VENUS(3),
    MOON(4),
    MARS(5),
    JUPITER(6),
    SATURN(7),
    URANUS(8),
    NEPTUNE(9),
    PLUTO(10);

    val zodiacs: List<Zodiac>
        get() {
            val zodiacs = ArrayList<Zodiac>()
            when (this) {
                SUN -> zodiacs.add(LEO)
                MERCURY -> {
                    zodiacs.add(GEMINI)
                    zodiacs.add(VIRGO)
                }
                VENUS -> {
                    zodiacs.add(TAURUS)
                    zodiacs.add(LIBRA)
                }
                MOON -> zodiacs.add(CANCER)
                MARS -> zodiacs.add(ARIES)
                JUPITER -> zodiacs.add(SAGITTARIUS)
                SATURN -> zodiacs.add(CAPRICORN)
                URANUS -> zodiacs.add(AQUARIUS)
                NEPTUNE -> zodiacs.add(PISCES)
                PLUTO -> zodiacs.add(SCORPIO)
            }
            return zodiacs
        }

    companion object {

        val random: Ruler
            get() {
                val index = Random().nextInt(values().size - 1)
                return values()[index]
            }

        operator fun get(ruler: String): Ruler? {
            try {
                return valueOf(ruler.toUpperCase())
            } catch (e: IllegalArgumentException) {
                Logger.error(e.message)
            }

            return null
        }
    }
}
