package de.bornholdtlee.dbsystel.utils;


import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import de.bornholdtlee.dbsystel.BuildConfig;
import de.bornholdtlee.dbsystel.Constants;
import de.bornholdtlee.dbsystel.R;
import de.bornholdtlee.dbsystel.helper.Logger;

public final class VersionCodeUtils {

    private VersionCodeUtils() {
        super();
    }

    public static void checkVersionAndDisplayVersionCode(Context context, TextView versionCode) {
        final String buildVariant = BuildConfig.FLAVOR + (BuildConfig.DEBUG ? Constants.BUILD_TYPE_DEBUG : Constants.BUILD_TYPE_RELEASE);
        versionCode.setVisibility(View.VISIBLE);
        versionCode.setText(String.format(context.getString(R.string.version_code), buildVariant, getVersionName(context), getVersionCode(context)));
    }

    private static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error(e.getMessage());
        }
        return "";
    }

    private static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error(e.getMessage());
        }
        return 0;
    }

}
