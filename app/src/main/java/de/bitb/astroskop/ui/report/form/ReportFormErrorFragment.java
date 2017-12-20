package de.bornholdtlee.astroskop.ui.report.form;

import butterknife.OnClick;
import de.bornholdtlee.astroskop.injection.IBind;
import de.bornholdtlee.astroskop.ui.report.ReportBaseFragment;
import de.bornholdtlee.snh.R;

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
