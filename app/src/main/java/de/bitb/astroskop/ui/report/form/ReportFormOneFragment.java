package de.bornholdtlee.astroskop.ui.report.form;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.bornholdtlee.astroskop.enums.ReportDetails;
import de.bornholdtlee.astroskop.enums.ReportLocation;
import de.bornholdtlee.astroskop.ui.report.ReportTabBaseFragment;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.astroskop.model.Report;

import static de.bornholdtlee.astroskop.ui.report.ReportPresenter.TAB_ID_1;

public class ReportFormOneFragment extends ReportTabBaseFragment {

    @BindView(R.id.fragment_report_form_1_radio_error_container)
    protected View chooseLocationError;

    @BindView(R.id.fragment_report_form_1_location_details_error_container)
    protected View locationDetailsError;

    @BindView(R.id.fragment_report_form_1_no_details_selected_error_container)
    protected View noDetailsSelectedError;

    @BindView(R.id.fragment_report_form_1_radio_group_location)
    protected RadioGroup locationRadioGroup;

    @BindView(R.id.fragment_report_form_1_radio_singlehouse)
    protected RadioButton locationRadioBtnSingle;

    @BindView(R.id.fragment_report_form_1_radio_multihouse)
    protected RadioButton locationRadioBtnMulti;

    @BindView(R.id.fragment_report_form_1_radio_rest)
    protected RadioButton locationRadioBtnRest;

    @BindView(R.id.fragment_report_form_1_radio_group_details)
    protected RadioGroup detailsRadioGroup;

    @BindView(R.id.fragment_report_form_1_radio_no_details)
    protected RadioButton detailsRadioBtnNoDetails;

    @BindView(R.id.fragment_report_form_1_radio_without_power)
    protected RadioButton detailsRadioBtnWithoutPower;

    @BindView(R.id.fragment_report_form_1_radio_with_power)
    protected RadioButton detailsRadioBtnWithPower;

    @BindView(R.id.fragment_report_form_1_radio_more_houses)
    protected RadioButton detailsRadioBtnMoreHouses;

    @BindView(R.id.fragment_report_form_1_details_container)
    protected View detailsContainer;

    @BindView(R.id.fragment_report_form_1_location_details_container)
    protected View locationDetailsContainer;

    @BindView(R.id.fragment_report_form_1_more_details)
    protected EditText moreDetailsText;

    @BindView(R.id.fragment_report_form_1_location_details)
    protected EditText locationDetailsText;


    public static ReportFormOneFragment createInstance() {
        return new ReportFormOneFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moreDetailsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setMoreDetails(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });
        locationDetailsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setLocationDetails(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });
    }

    @Override
    public int getTabLayoutId() {
        return R.layout.fragment_report_form_1;
    }

    @Override
    public int getTabId() {
        return TAB_ID_1;
    }

    @Override
    public void cleanErrors() {
        super.cleanErrors();
        chooseLocationError.setVisibility(View.GONE);
        locationDetailsError.setVisibility(View.GONE);
        noDetailsSelectedError.setVisibility(View.GONE);
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_singlehouse)
    public void onSingleHouseSelected(CompoundButton button, boolean checked) {
        if (checked) {
            locationDetailsContainer.setVisibility(View.GONE);
            detailsContainer.setVisibility(View.VISIBLE);
            cleanErrors();
            getPresenter().selectedLocation(ReportLocation.SINGLEHOUSE);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_multihouse)
    public void onMultiHouseSelected(CompoundButton button, boolean checked) {
        if (checked) {
            locationDetailsContainer.setVisibility(View.GONE);
            detailsContainer.setVisibility(View.VISIBLE);
            cleanErrors();
            getPresenter().selectedLocation(ReportLocation.MULTIHOUSE);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_rest)
    public void onAnotherLocationSelected(CompoundButton button, boolean checked) {
        if (checked) {
            locationDetailsContainer.setVisibility(View.VISIBLE);
            detailsContainer.setVisibility(View.GONE);
            cleanErrors();
            getPresenter().selectedLocation(ReportLocation.REST);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_no_details)
    public void onNoDetailsSelected(CompoundButton button, boolean checked) {
        if (checked) {
            cleanErrors();
            getPresenter().selectDetails(ReportDetails.NO_DETAILS);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_without_power)
    public void onWithoutPowerSelected(CompoundButton button, boolean checked) {
        if (checked) {
            cleanErrors();
            getPresenter().selectDetails(ReportDetails.WITHOUT_POWER);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_with_power)
    public void onWithPowerSelected(CompoundButton button, boolean checked) {
        if (checked) {
            cleanErrors();
            getPresenter().selectDetails(ReportDetails.WITH_POWER);
        }
    }

    @OnCheckedChanged(R.id.fragment_report_form_1_radio_more_houses)
    public void onMoreHousesSelected(CompoundButton button, boolean checked) {
        if (checked) {
            cleanErrors();
            getPresenter().selectDetails(ReportDetails.MORE_HOUSES);
        }
    }

    @Override
    public void showNoLocationSelectedError() {
        chooseLocationError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoLocationDetailsError() {
        locationDetailsError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoDetailsSelectedError() {
        noDetailsSelectedError.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTrackerInfoId() {
        return R.string.tracker_report_disturbance_tab1;
    }

    @Override
    public void initReport(Report report) {
        if (report.getLocation() != null) {
            switch (report.getLocation()) {
                case ReportLocation.SINGLEHOUSE:
                    locationRadioBtnSingle.setChecked(true);
                    break;
                case ReportLocation.MULTIHOUSE:
                    locationRadioBtnMulti.setChecked(true);
                    break;
                case ReportLocation.REST:
                    locationRadioBtnRest.setChecked(true);
                    break;
            }
        }
        if (!TextUtils.isEmpty(report.getLocationDetails())) {
            locationDetailsText.setText(report.getLocationDetails());
        }

        if (!TextUtils.isEmpty(report.getMoreDetails())) {
            moreDetailsText.setText(report.getMoreDetails());
        }
    }

    @OnClick(R.id.fragment_report_button_to_tab2)
    public void onNextClicked() {
        getPresenter().onTab1Finished();
    }

}
