package de.bornholdtlee.snh.ui.report.form;

import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.report.ReportBaseFragment;

public class ReportFormErrorFragment extends ReportBaseFragment implements IBind {

    public static ReportFormErrorFragment createInstance() {
        return new ReportFormErrorFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report_form_error;
    }

    @OnClick(R.id.fragment_report_error_button_back_to_summary_container)
    public void onBack() {
        getActivity().onBackPressed();
    }

}
