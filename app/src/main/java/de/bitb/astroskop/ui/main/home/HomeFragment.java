package de.bitb.astroskop.ui.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.IMainView;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.injection.IBind;

public class HomeFragment extends NavigationBaseFragment implements IHomeView, IBind {

    @BindView(R.id.fragment_home_button_job_title)
    TextView jobOfferTitle;

    private IMainView mainView;
    private HomeViewPresenter presenter;

    public static HomeFragment createInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainView = (IMainView) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomeViewPresenter((SNHApplication) getActivity().getApplication(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.app_name));
    }

    @Override
    protected boolean showInfoActionBarButton() {
        return false;
    }

    @Override
    protected boolean showRefreshActionBarButton() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_HOME;
    }

    @OnClick(R.id.fragment_home_button_analysis_container)
    public void navigateToAnalysis() {
        mainView.navigateToScreen(MainActivity.TAB_ANALYSIS);
    }

    @OnClick(R.id.fragment_home_button_constructions_container)
    public void navigateToConstructions() {
        mainView.navigateToScreen(MainActivity.TAB_CONSTRUCTIONS);
    }

    @OnClick(R.id.fragment_home_button_disturbance_container)
    public void navigateToDisturbance() {
        mainView.navigateToScreen(MainActivity.TAB_DISTURBANCE);
    }

    @OnClick(R.id.fragment_home_button_report_disturbance_container)
    public void onReportDisturbanceClick() {
        mainView.navigateToReportDisturbanceScreen();
    }

    @OnClick(R.id.fragment_home_button_job_container)
    public void onJobOfferClick() {
        presenter.jobOfferClicked();
    }
}