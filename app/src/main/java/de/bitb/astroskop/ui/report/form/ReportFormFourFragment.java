package de.bitb.astroskop.ui.report.form;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.enums.ReportLocation;
import de.bitb.astroskop.ui.report.ReportTabBaseFragment;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.model.Report;

import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_4;
import static de.bitb.astroskop.ui.report.form.ReportFormThreeFragment.TITLE_COMPANY;
import static de.bitb.astroskop.ui.report.form.ReportFormThreeFragment.TITLE_MAN;
import static de.bitb.astroskop.ui.report.form.ReportFormThreeFragment.TITLE_NOMAN;

public class ReportFormFourFragment extends ReportTabBaseFragment {

    @BindView(R.id.fragment_report_form_4_location)
    protected TextView locationTv;

    @BindView(R.id.fragment_report_form_4_details)
    protected TextView detailsTv;

    @BindView(R.id.fragment_report_form_4_more_details)
    protected TextView moreDetailsTv;

    @BindView(R.id.fragment_report_form_4_street)
    protected TextView streetTv;
    @BindView(R.id.fragment_report_form_4_number)
    protected TextView numberTv;
    @BindView(R.id.fragment_report_form_4_plz)
    protected TextView plzTv;
    @BindView(R.id.fragment_report_form_4_city)
    protected TextView cityTv;

    @BindView(R.id.fragment_report_form_4_title)
    protected TextView titleTv;
    @BindView(R.id.fragment_report_form_4_company)
    protected TextView companyTv;
    @BindView(R.id.fragment_report_form_4_company_container)
    protected View companyContainer;
    @BindView(R.id.fragment_report_form_4_costumer_name)
    protected TextView nameTv;
    @BindView(R.id.fragment_report_form_4_costumer_familyname)
    protected TextView familyNameTv;
    @BindView(R.id.fragment_report_form_4_phone)
    protected TextView phoneTv;
    @BindView(R.id.fragment_report_form_4_email)
    protected TextView emailTv;

    public static ReportFormFourFragment createInstance() {
        return new ReportFormFourFragment();
    }

    @Override
    public int getTabLayoutId() {
        return R.layout.fragment_report_form_4;
    }

    @Override
    public int getTabId() {
        return TAB_ID_4;
    }

    @Override
    public void initReport(Report report) {
        ReportLocation location = report.getLocation();
        locationTv.setText(location.getTextId());
        switch (location) {
            case SINGLEHOUSE:
            case MULTIHOUSE:
                detailsTv.setText(report.getDetails().getTextId());
                break;
            case REST:
                detailsTv.setText(report.getLocationDetails());
                break;
        }
        moreDetailsTv.setText(TextUtils.isEmpty(report.getMoreDetails()) ? "-" : report.getMoreDetails());

        streetTv.setText(report.getStreetName());
        plzTv.setText(report.getPlz());
        numberTv.setText(report.getStreetNumber());
        cityTv.setText(report.getCityName());

        String title = report.getTitle();
        switch (title) {
            case TITLE_MAN:
                title = getString(R.string.fragment_report_form_3_title_man);
                report.setCompany("");
                break;
            case TITLE_NOMAN:
                title = getString(R.string.fragment_report_form_3_title_noman);
                report.setCompany("");
                break;
            case TITLE_COMPANY:
                title = getString(R.string.fragment_report_form_3_title_company);
                break;
        }
        titleTv.setText(title);
        if (TextUtils.isEmpty(report.getCompany())) {
            companyContainer.setVisibility(View.INVISIBLE);
        } else {
            companyTv.setText(report.getCompany());
        }
        familyNameTv.setText(report.getCustomerFamilyName());
        nameTv.setText(report.getCustomerName());
        phoneTv.setText(report.getPhone());
        emailTv.setText(TextUtils.isEmpty(report.getEmail()) ? "-" : report.getEmail());

    }

    @Override
    public int getTrackerInfoId() {
        return R.string.tracker_report_disturbance_tab4;
    }

    @OnClick(R.id.fragment_report_form_4_change_1)
    public void onChangeTab1Clicked() {
        getPresenter().onTab1Clicked();
    }

    @OnClick(R.id.fragment_report_form_4_change_2)
    public void onChangeTab2Clicked() {
        getPresenter().onTab2Clicked();
    }

    @OnClick(R.id.fragment_report_form_4_change_3)
    public void onChangeTab3Clicked() {
        getPresenter().onTab3Clicked();
    }

    @OnClick(R.id.fragment_report_button_finish)
    public void onFinishReport() {
        getPresenter().finishReport();
    }
}
