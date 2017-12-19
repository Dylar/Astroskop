package de.bornholdtlee.snh.ui.report.form;

import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.report.ReportBaseFragment;

public class ReportFormSentFragment extends ReportBaseFragment implements IBind {

    public static ReportFormSentFragment createInstance() {
        return new ReportFormSentFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report_form_sent;
    }

    @OnClick(R.id.fragment_report_sent_text_button_back_to_summary_container)
    public void onBack() {
        getActivity().finish();
    }

}
