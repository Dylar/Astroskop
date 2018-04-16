package de.bitb.astroskop.ui.base

import android.view.Menu
import de.bitb.astroskop.Constants.Companion.NULL_INTEGER

import de.bitb.astroskop.R

class ActionbarHandler(private val callback: ActionbarCallback) {

    fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val button1 = menu?.getItem(0)
        val icon1 = callback.actionbarButton1Icon
        button1?.isVisible = icon1 != NULL_INTEGER
        if (icon1 != NULL_INTEGER) {
            button1?.setIcon(icon1)
        }
        return true
    }

    fun onOptionsItemSelected(id: Int): Boolean {
        when (id) {
            R.id.actionbar_button_1 -> return callback.onActionbarButton1Clicked()
        }
        return false
    }

    open class ActionbarCallback {

        open val actionbarButton1Icon: Int
            get() = NULL_INTEGER

        open fun onActionbarButton1Clicked(): Boolean {
            return false
        }
    }
}
