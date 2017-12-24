package de.bitb.astroskop.ui.base;


import android.view.Menu;
import android.view.MenuItem;

import de.bitb.astroskop.R;
import de.bitb.astroskop.enums.AnimationType;

public class SimpleBaseActivity extends BaseActivity implements IToolbarView {

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_cancel: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getIcon() {
        return 0;
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_BOTTOM_POP_BOTTOM;
    }
}
