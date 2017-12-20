package de.bitb.astroskop.utils;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Locale;

import de.bornholdtlee.snh.R;

public class CommonUtils {

    public static final String URL_PLAY_GOOGLE_STORE_APPS_DETAILS_ID = "http://play.google.com/store/apps/details?id=";
    public static final String URL_MARKET_DETAILS_ID = "market://details?id=";

    public void openExternUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public void rankApp(Context context) {
        Uri uri = Uri.parse(URL_MARKET_DETAILS_ID + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
                | Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(URL_PLAY_GOOGLE_STORE_APPS_DETAILS_ID + context.getPackageName())));
        }
    }

    public void closeKeyboard(Context context, IBinder token) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
    }

    public void openKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void call(Activity activity, String phoneNumber) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, R.string.please_accept_permission, Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1887);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }

    public boolean containsCaseInsensitive(String string, String contains) {
        String lowerString = string.toLowerCase(Locale.getDefault());
        String lowerContains = contains.toLowerCase(Locale.getDefault());
        return lowerString.contains(lowerContains);
    }
}
