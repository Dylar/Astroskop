package de.bitb.astroskop.ui.base;

import android.view.Menu;
import android.view.MenuItem;

import de.bitb.astroskop.R;

import static de.bitb.astroskop.Constants.NULL_INTEGER;

public class ActionbarHandler {

    private final ActionbarCallback callback;

    public ActionbarHandler(ActionbarCallback callback) {
        this.callback = callback;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem button1 = menu.getItem(0);
        int icon1 = callback.getActionbarButton1Icon();
        button1.setVisible(icon1 != NULL_INTEGER);
        if (icon1 != NULL_INTEGER) {
            button1.setIcon(icon1);
        }
        return true;
    }

    public boolean onOptionsItemSelected(int id) {
        switch (id) {
            case R.id.actionbar_button_1:
                return callback.onActionbarButton1Clicked();
        }
        return false;
    }

    public static class ActionbarCallback {

        public int getActionbarButton1Icon() {
            return NULL_INTEGER;
        }

        public boolean onActionbarButton1Clicked() {
            return false;
        }
    }
}
