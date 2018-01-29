package de.bitb.astroskop.model.enums;

import de.bitb.astroskop.R;
import lombok.Getter;

@Getter
public enum Element {
    FIRE(R.color.element_fire),
    AIR(R.color.element_air),
    WATER(R.color.element_water),
    EARTH(R.color.element_earth);

    private final int color;

    Element(int color) {
        this.color = color;
    }
}
