package de.bornholdtlee.snh.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.base.BaseActivity;
import de.bornholdtlee.snh.ui.base.IToolbarView;

public class MenuActivity extends BaseActivity implements IMenuView, IBind, IToolbarView {

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, MenuActivity.class);
        activity.startActivity(intent);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_BOTTOM_POP_BOTTOM, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarWidthWorkaround(false, 1);
        showFragment(MenuFragment.createInstance(), false);
    }

    @Override
    public void navigateToImprint() {
        showFragment(ImprintFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void navigateToPrivacy() {
        showFragment(PrivacyFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_BOTTOM_POP_BOTTOM;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_back;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setToolbarWidthWorkaround(false, 1);
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