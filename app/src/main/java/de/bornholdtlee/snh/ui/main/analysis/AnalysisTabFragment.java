package de.bornholdtlee.snh.ui.main.analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.base.NavigationBaseFragment;

import static de.bornholdtlee.snh.ui.main.MainActivity.TAB_ANALYSIS;

public class AnalysisTabFragment extends NavigationBaseFragment implements IAnalysisView, IBind {

    @BindView(R.id.fragment_analysis_no_network_error)
    protected View networkErrorView;

    @BindView(R.id.fragment_scan_tab_layout)
    protected TabLayout scanTabLayout;

    @BindView(R.id.fragment_scan_viewpager)
    protected ViewPager scanViewPager;

    private AnalysisTabFragmentAdapter adapter;

    public static AnalysisTabFragment createInstance() {
        return new AnalysisTabFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initTabs(scanTabLayout);
        initViewPager(scanViewPager, scanTabLayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.fragment_analysis_title));
    }

    @Override
    public void onRefreshClicked() {
        adapter.getCurrentFragment().onRefreshClicked();
    }

    @Override
    public void onInfoClicked() {
        adapter.getCurrentFragment().onInfoClicked();
    }

    private void initTabs(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_tab_title_data));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_tab_title_map));
    }

    private void initViewPager(ViewPager scanViewPager, TabLayout scanTabLayout) {
        adapter = new AnalysisTabFragmentAdapter(getChildFragmentManager());
        scanViewPager.setAdapter(adapter);
        scanViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scanTabLayout));
        scanTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                LinearLayout tabLayout = (LinearLayout)((ViewGroup) scanTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
//                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
//                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
                scanViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                LinearLayout tabLayout = (LinearLayout)((ViewGroup) scanTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
//                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
//                tabTextView.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //nothing
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public int getNavigationPosition() {
        return TAB_ANALYSIS;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.getCurrentFragment().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showNoNetworkError() {
        scanTabLayout.setVisibility(View.GONE);
        networkErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoNetworkError() {
        scanTabLayout.setVisibility(View.VISIBLE);
        networkErrorView.setVisibility(View.GONE);
    }
}
