package de.bitb.astroskop.utils;

import android.app.Activity;

import net.hockeyapp.android.CrashManager;

import de.bornholdtlee.snh.BuildConfig;

public final class HockeyAppUtils {

    private HockeyAppUtils() {
    }

    public static void checkForCrashes(Activity activity) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID);
    }
}