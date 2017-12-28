package de.bitb.astroskop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.IToolbarView;
import de.bitb.astroskop.ui.base.NavigationBaseActivity;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.circumstances.CircumstanceFragment;
import de.bitb.astroskop.ui.main.home.HomeFragment;
import de.bitb.astroskop.utils.HockeyAppUtils;

public class MainActivity extends NavigationBaseActivity implements IBind, IToolbarView {

    public static final int TAB_HOME = 0;
    public static final int TAB_CIRCUMSTANCES = 1;
    public static final int TAB_PROFILES = 2;
    public static final int TAB_SETTINGS = 3;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigateToHomeScreen();
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (!wasSelected) {
            switch (position) {
                case TAB_HOME:
                    navigateToHomeScreen();
                    return true;
                case TAB_CIRCUMSTANCES:
                    navigateToCircumstanceScreen();
                    return true;
                case TAB_PROFILES:
                    navigateToProfilesScreen();
                    return true;
                case TAB_SETTINGS:
                    return true;
            }
        }
        return false;
    }

    private void navigateToHomeScreen() {
        HomeFragment fragment = HomeFragment.createInstance();
        showFragmentClearTop(fragment);
    }

    private void navigateToCircumstanceScreen() {
        CircumstanceFragment fragment = CircumstanceFragment.createInstance();
        showFragment(fragment);
    }

    private void navigateToProfilesScreen() {

    }

    @Override
    public void onResume() {
        super.onResume();

        HockeyAppUtils.checkForCrashes(this);
    }

    @Override
    public int getBottomMenuLayout() {
        return R.menu.navigation_menu;
    }

}