package de.bornholdtlee.snh.ui.base;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;


public abstract class NavigationBaseActivity extends BaseActivity implements IBind, AHBottomNavigation.OnTabSelectedListener {

    public static final int VIEW_HEIGHT_DIFFERENT = 600;

    @BindView(R.id.activity_navigation)
    protected AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigationBar();
        checkKeyBoardUp();
    }

    private void setupNavigationBar() {
        setBottomNavigationVisible(true);

        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.icon_blue));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.icon_inactive_gray));
//        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.background_gray));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        setCurrentTab(0);
        bottomNavigation.setOnTabSelectedListener(this);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this, getBottomMenuLayout());
        adapter.setupWithBottomNavigation(bottomNavigation);
    }

    @Override
    protected boolean allowBackPressed() {
        BaseFragment fragment = getCurrentContent();
        if (fragment instanceof NavigationBaseFragment && ((NavigationBaseFragment) fragment).getNavigationPosition() != 0) {
            bottomNavigation.setCurrentItem(0);
            return false;
        }
        return true;
    }

    public void setBottomNavigationVisible(boolean visible) {
        bottomNavigation.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public abstract int getBottomMenuLayout();

    public void setCurrentTab(int currentTab) {
        bottomNavigation.setCurrentItem(currentTab);
    }

    public void checkKeyBoardUp() {
        getBaseView().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            getBaseView().getWindowVisibleDisplayFrame(r);
            int heightDiff = getBaseView().getRootView().getHeight() - (r.bottom - r.top);
            bottomNavigation.setVisibility(heightDiff > VIEW_HEIGHT_DIFFERENT ? View.GONE : View.VISIBLE);
        });
    }
}