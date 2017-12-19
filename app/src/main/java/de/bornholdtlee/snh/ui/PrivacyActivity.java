package de.bornholdtlee.snh.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.ui.base.BaseActivity;
import de.bornholdtlee.snh.ui.base.IToolbarView;
import de.bornholdtlee.snh.ui.menu.PrivacyFragment;

public class PrivacyActivity extends BaseActivity implements IToolbarView {

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, PrivacyActivity.class);
        activity.startActivity(intent);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_RIGHT_POP_RIGHT, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(PrivacyFragment.createInstance(), false);
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_RIGHT_POP_RIGHT;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_back;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
