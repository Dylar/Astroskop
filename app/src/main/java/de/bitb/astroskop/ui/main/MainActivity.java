package de.bitb.astroskop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.base.IToolbarView;
import de.bitb.astroskop.ui.base.NavigationBaseActivity;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.home.HomeFragment;
import de.bitb.astroskop.utils.HockeyAppUtils;

public class MainActivity extends NavigationBaseActivity implements IMainView, IBind, IToolbarView {

    public static final int TAB_HOME = 0;
    public static final int TAB_ANALYSIS = 1;
    public static final int TAB_CONSTRUCTIONS = 2;
    public static final int TAB_DISTURBANCE = 3;

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
            setToolbarIcon(getIcon());
            switch (position) {
                case TAB_HOME:
                    navigateToHomeScreen();
                    return true;
                case TAB_ANALYSIS:
                    return true;
                case TAB_CONSTRUCTIONS:
                    return true;
                case TAB_DISTURBANCE:
                    return true;
            }
        }
        return false;
    }

    @Override
    public void navigateToHomeScreen() {
        NavigationBaseFragment fragment = HomeFragment.createInstance();
        showFragmentClearTop(fragment, false);
        setToolbarWidthWorkaround(true, 0);
    }

    @Override
    public void navigateToScreen(int tabId) {
        setCurrentTab(tabId);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarIcon(getCurrentContent().getIcon());

        HockeyAppUtils.checkForCrashes(this);
    }

    @Override
    protected boolean allowBackPressed() {
        setToolbarIcon(R.drawable.ic_navbar_burger);
        return super.allowBackPressed();
    }

    @Override
    public int getBottomMenuLayout() {
        return R.menu.navigation_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            BaseFragment fragment = getCurrentContent();
            openMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openMenu() {
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_burger;
    }

}