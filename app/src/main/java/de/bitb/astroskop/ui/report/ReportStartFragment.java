package de.bitb.astroskop.ui.report;

import android.content.Context;

import butterknife.OnClick;
import de.bitb.astroskop.injection.IBind;
import de.bornholdtlee.snh.R;

public class ReportStartFragment extends ReportBaseFragment implements IBind {

    private IReportView reportDisturbanceView;

    public static ReportStartFragment createInstance() {
        return new ReportStartFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report_disturbance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reportDisturbanceView = (IReportView) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_report_disturbance));
    }

    protected boolean showInfoActionBarButton() {
        return false;
    }

    protected boolean showRefreshActionBarButton() {
        return false;
    }

    protected boolean showFinishActionBarButton() {
        return true;
    }

    @OnClick(R.id.fragment_report_disturbance_button_report_disturbance_container)
    public void onReportClicked() {
        reportDisturbanceView.navigateToReportFormTab1Screen();
    }

    @OnClick(R.id.fragment_report_disturbance_button_report_disturbance_container_alt)
    public void onReportAlternativeClicked() {
        reportDisturbanceView.navigateToReportAlternativeScreen();
    }
}