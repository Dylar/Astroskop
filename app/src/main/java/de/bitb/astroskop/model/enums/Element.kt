package de.bitb.astroskop.model.enums

import de.bitb.astroskop.Constants.Companion.NULL_INTEGER
import de.bitb.astroskop.R
import lombok.Getter

@Getter
enum class Element constructor(val color: Int) {
    NONE(NULL_INTEGER),
    FIRE(R.color.element_fire),
    AIR(R.color.element_air),
    WATER(R.color.element_water),
    EARTH(R.color.element_earth)
}
