package de.bornholdtlee.snh.ui.report;

import javax.inject.Inject;

import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.utils.CommonUtils;

public class ReportAlternativeFragment extends ReportBaseFragment implements IInjection, IBind {

    @Inject
    protected CommonUtils commonUtils;

    public static ReportAlternativeFragment createInstance() {
        return new ReportAlternativeFragment();
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report_alternative;
    }

    @Override
    public void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_report_disturbance_others));
    }

    @OnClick(R.id.fragment_report_alternative_police_container)
    public void woopWoopThatsTheSoundOfDaPolice() {
        commonUtils.call(getActivity(), "110");
    }

    @OnClick(R.id.fragment_report_alternative_firefighter_container)
    public void callGrisuTheDragon() {
        commonUtils.call(getActivity(), "112");
    }

    @OnClick(R.id.fragment_report_alternative_hotline_container)
    public void callTheMVP() {
        commonUtils.call(getActivity(), "08001439439");
    }

}
