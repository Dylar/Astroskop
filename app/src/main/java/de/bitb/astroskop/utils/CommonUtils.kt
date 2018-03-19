package de.bitb.astroskop.utils


import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import java.util.Locale

import de.bitb.astroskop.R

class CommonUtils {

    fun openExternUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun rankApp(context: Context) {
        val uri = Uri.parse(URL_MARKET_DETAILS_ID + context.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
                or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            context.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(URL_PLAY_GOOGLE_STORE_APPS_DETAILS_ID + context.packageName)))
        }

    }

    fun closeKeyboard(context: Context, token: IBinder) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(token, 0)
    }

    fun openKeyboard(context: Context) {
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun call(activity: Activity, phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, R.string.please_accept_permission, Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 1887)
            return
        }
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
        activity.startActivity(intent)
    }

    fun containsCaseInsensitive(string: String, contains: String): Boolean {
        val lowerString = string.toLowerCase(Locale.getDefault())
        val lowerContains = contains.toLowerCase(Locale.getDefault())
        return lowerString.contains(lowerContains)
    }

    companion object {

        val URL_PLAY_GOOGLE_STORE_APPS_DETAILS_ID = "http://play.google.com/store/apps/details?id="
        val URL_MARKET_DETAILS_ID = "market://details?id="
    }
}
