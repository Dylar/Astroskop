package de.bitb.astroskop.utils;

import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import de.bitb.astroskop.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.BaseFragment;

public final class UiUtils {

    public float convertDpToPx(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public float convertPxToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public void startRotation(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());

        view.startAnimation(rotate);
    }

    public void setAnimation(BaseActivity activity, boolean slideIn) {
        setAnimation(activity, activity.getAnimationType(), slideIn);
    }

    public void setAnimation(BaseActivity activity, AnimationType animationType, boolean slideIn) {
        if (!AnimationType.NONE.equals(animationType)) {
            activity.overridePendingTransition(slideIn ? animationType.getSlideIn() : R.anim.stay, slideIn ? R.anim.stay : animationType.getSlideOut());
        }
    }

    public void setAnimation(BaseFragment fragment, FragmentTransaction fragmentTransaction) {
        AnimationType animationType = fragment.getAnimationType();
        if (!AnimationType.NONE.equals(animationType)) {
            fragmentTransaction.setCustomAnimations(animationType.getSlideIn(), animationType.getSlideOut(), animationType.getPopIn(), animationType.getPopOut());
        }
    }
}