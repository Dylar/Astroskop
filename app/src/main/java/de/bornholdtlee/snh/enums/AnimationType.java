package de.bornholdtlee.snh.enums;


import de.bornholdtlee.snh.R;
import lombok.Getter;

@Getter
public enum AnimationType {

    NONE(-1, -1, -1, -1),
    SLIDE_RIGHT_DONT_POP(R.anim.slide_in_right, R.anim.stay, R.anim.slide_in_left, R.anim.stay),
    SLIDE_RIGHT_POP_LEFT(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right),
    SLIDE_RIGHT_POP_RIGHT(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right),
    SLIDE_BOTTOM_POP_BOTTOM(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom);

    private final int slideIn;
    private final int slideOut;
    private final int popIn;
    private final int popOut;

    AnimationType(int slideIn, int slideOut, int popIn, int popOut) {
        this.slideIn = slideIn;
        this.slideOut = slideOut;
        this.popIn = popIn;
        this.popOut = popOut;
    }
}
