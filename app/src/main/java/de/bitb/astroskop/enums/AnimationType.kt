package de.bitb.astroskop.enums


import de.bitb.astroskop.R
import lombok.Getter

@Getter
enum class AnimationType constructor(val slideIn: Int, val slideOut: Int, val popIn: Int, val popOut: Int) {

    NONE(-1, -1, -1, -1),
    SLIDE_RIGHT_DONT_POP(R.anim.slide_in_right, R.anim.stay, R.anim.slide_in_left, R.anim.stay),
    SLIDE_RIGHT_POP_LEFT(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right),
    SLIDE_RIGHT_POP_RIGHT(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right),
    SLIDE_BOTTOM_POP_BOTTOM(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
}
