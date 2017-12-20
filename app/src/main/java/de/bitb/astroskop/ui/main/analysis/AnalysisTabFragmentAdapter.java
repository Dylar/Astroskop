package de.bitb.astroskop.ui.main.analysis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.main.analysis.data.DataFragment;
import de.bitb.astroskop.ui.main.analysis.map.MapFragment;
import lombok.Getter;

public class AnalysisTabFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_DATA = 0;
    private static final int TAB_MAP = TAB_DATA + 1;
    private static final int TAB_SIZE = TAB_MAP + 1;

    @Getter
    private BaseFragment currentFragment;

    public AnalysisTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_DATA:
                return DataFragment.createInstance();
            case TAB_MAP:
                return MapFragment.createInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_SIZE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = (BaseFragment) object;
            if (currentFragment.isAdded()) {
                ((IAnalysisTabView) currentFragment).onVisible();
            }
        }
        super.setPrimaryItem(container, position, object);
    }

}
