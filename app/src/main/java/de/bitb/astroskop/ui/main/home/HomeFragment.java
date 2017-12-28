package de.bitb.astroskop.ui.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.IBaseView;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.MainActivity;

public class HomeFragment extends NavigationBaseFragment implements IBind, IBaseView {


    public static HomeFragment createInstance() {
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.app_name));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_HOME;
    }

    @Override
    public void refreshView() {

    }
}