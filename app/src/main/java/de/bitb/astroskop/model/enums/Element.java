package de.bitb.astroskop.model.enums;

import de.bitb.astroskop.R;
import lombok.Getter;

@Getter
public enum Element {
    FIRE(R.color.red),
    AIR(R.color.gray4),
    WATER(R.color.blue),
    EARTH(R.color.brown);

    private final int color;

    Element(int color) {
        this.color = color;
    }
}
