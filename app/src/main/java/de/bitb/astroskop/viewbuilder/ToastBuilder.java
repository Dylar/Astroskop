package de.bitb.astroskop.viewbuilder;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.bitb.astroskop.ui.base.BaseActivity;
import de.bornholdtlee.snh.R;

public final class ToastBuilder {

    private void showToast(Context context, String message, int duration) {
        if (BaseActivity.isAppInForeground()) {
            Toast toast = new Toast(context.getApplicationContext());
            View toastView = inflateLayout(context);
            TextView toastText = toastView.findViewById(R.id.toast_text_view);
            toastText.setText(message);
            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);
            toast.setDuration(duration);
            toast.setView(toastView);
            toast.show();
        }
    }

    public void showShortToast(Context context, int stringId) {
        if (context != null) {
            showShortToast(context, context.getString(stringId));
        }
    }

    public void showShortToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public void showLongToast(Context context, int stringId) {
        if (context != null) {
            showLongToast(context, context.getString(stringId));
        }
    }

    public void showLongToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    private View inflateLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.layout_toast, null);
    }
}
