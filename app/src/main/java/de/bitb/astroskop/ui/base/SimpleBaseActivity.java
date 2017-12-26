package de.bitb.astroskop.ui.base;


import android.view.Menu;
import android.view.MenuItem;

import de.bitb.astroskop.R;
import de.bitb.astroskop.enums.AnimationType;

public class SimpleBaseActivity extends BaseActivity implements IToolbarView {

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_BOTTOM_POP_BOTTOM;
    }
}
