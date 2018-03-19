package de.bitb.astroskop.utils

import android.content.res.Resources
import android.support.v4.app.FragmentTransaction
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

import de.bitb.astroskop.R
import de.bitb.astroskop.enums.AnimationType
import de.bitb.astroskop.ui.base.BaseActivity
import de.bitb.astroskop.ui.base.BaseFragment

class UiUtils {

    fun convertDpToPx(dp: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPxToDp(px: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun startRotation(view: View) {
        val rotate = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.repeatCount = Animation.INFINITE
        rotate.repeatMode = Animation.RESTART
        rotate.duration = 1000
        rotate.interpolator = LinearInterpolator()

        view.startAnimation(rotate)
    }

    fun setAnimation(activity: BaseActivity, slideIn: Boolean) {
        setAnimation(activity, activity.animationType, slideIn)
    }

    fun setAnimation(activity: BaseActivity, animationType: AnimationType, slideIn: Boolean) {
        if (AnimationType.NONE != animationType) {
            activity.overridePendingTransition(if (slideIn) animationType.slideIn else R.anim.stay, if (slideIn) R.anim.stay else animationType.slideOut)
        }
    }

    fun setAnimation(fragment: BaseFragment, fragmentTransaction: FragmentTransaction) {
        val animationType = fragment.animationType
        if (AnimationType.NONE != animationType) {
            fragmentTransaction.setCustomAnimations(animationType.slideIn, animationType.slideOut, animationType.popIn, animationType.popOut)
        }
    }
}