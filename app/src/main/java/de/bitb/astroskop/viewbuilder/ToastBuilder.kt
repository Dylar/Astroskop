package de.bitb.astroskop.viewbuilder


import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

import de.bitb.astroskop.R
import de.bitb.astroskop.ui.base.BaseActivity

class ToastBuilder {

    private fun showToast(context: Context, message: String, duration: Int) {
        if (BaseActivity.isAppInForeground) {
            val toast = Toast(context.applicationContext)
            val toastView = inflateLayout(context)
            val toastText = toastView.findViewById<TextView>(R.id.toast_text_view)
            toastText.text = message
            toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
            toast.duration = duration
            toast.view = toastView
            toast.show()
        }
    }

    fun showShortToast(context: Context?, stringId: Int) {
        if (context != null) {
            showShortToast(context, context.getString(stringId))
        }
    }

    fun showShortToast(context: Context, message: String) {
        showToast(context, message, Toast.LENGTH_SHORT)
    }

    fun showLongToast(context: Context?, stringId: Int) {
        if (context != null) {
            showLongToast(context, context.getString(stringId))
        }
    }

    fun showLongToast(context: Context, message: String) {
        showToast(context, message, Toast.LENGTH_LONG)
    }

    private fun inflateLayout(context: Context): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(R.layout.layout_toast, null)
    }
}
