package de.bitb.astroskop.utils


import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.widget.TextView

import de.bitb.astroskop.BuildConfig
import de.bitb.astroskop.Constants
import de.bitb.astroskop.helper.Logger

class VersionCodeUtils private constructor() {
    companion object {

        fun checkVersionAndDisplayVersionCode(context: Context, versionCode: TextView) {
            val buildVariant = BuildConfig.FLAVOR + if (BuildConfig.DEBUG) Constants.BUILD_TYPE_DEBUG else Constants.BUILD_TYPE_RELEASE
            versionCode.visibility = View.VISIBLE
            //        versionCode.setText(String.format(context.getString(R.string.version_code), buildVariant, getVersionName(context), getVersionCode(context)));
        }

        private fun getVersionName(context: Context): String {
            try {
                return context.packageManager.getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                Logger.error(e.message)
            }

            return ""
        }

        private fun getVersionCode(context: Context): Int {
            try {
                return context.packageManager.getPackageInfo(context.packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                Logger.error(e.message)
            }

            return 0
        }
    }

}
