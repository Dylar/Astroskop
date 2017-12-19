package de.bornholdtlee.snh.ui.report.form;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.model.Report;
import de.bornholdtlee.snh.ui.report.ReportTabBaseFragment;

import static de.bornholdtlee.snh.ui.report.ReportPresenter.TAB_ID_3;

public class ReportFormThreeFragment extends ReportTabBaseFragment {

    public static final String TITLE_MAN = "man";
    public static final String TITLE_NOMAN = "noman";
    public static final String TITLE_COMPANY = "company";
    public static final int TITLE_SELECTION_MAN = 0;
    public static final int TITLE_SELECTION_NOMAN = 1;
    public static final int TITLE_SELECTION_COMPANY = 2;

    @BindView(R.id.fragment_report_form_3_title_spinner)
    protected Spinner titleSpinner;

    @BindView(R.id.fragment_report_form_3_name_error_container)
    protected View nameError;

    @BindView(R.id.fragment_report_form_3_familyname_error_container)
    protected View familyNameError;

    @BindView(R.id.fragment_report_form_3_phone_error_container)
    protected View phoneError;

    @BindView(R.id.fragment_report_form_3_email_error_container)
    protected View emailError;

    @BindView(R.id.fragment_report_form_3_name)
    protected EditText nameEt;

    @BindView(R.id.fragment_report_form_3_familyname)
    protected EditText familyNameEt;

    @BindView(R.id.fragment_report_form_3_company_container)
    protected ViewGroup companyContainer;

    @BindView(R.id.fragment_report_form_3_company)
    protected EditText companyEt;

    @BindView(R.id.fragment_report_form_3_company_error_container)
    protected View companyError;

    @BindView(R.id.fragment_report_form_3_tele)
    protected EditText teleEt;

    @BindView(R.id.fragment_report_form_3_email)
    protected EditText emailEt;

    public static ReportFormThreeFragment createInstance() {
        return new ReportFormThreeFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEmailEt();
        initNameEt();
        initFamilyNameEt();
        initCompanyEt();
        initPhoneEt();

        String[] items = new String[]{
                getString(R.string.fragment_report_form_3_title_man),
                getString(R.string.fragment_report_form_3_title_noman),
                getString(R.string.fragment_report_form_3_title_company)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        titleSpinner.setAdapter(adapter);
        titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                companyContainer.setVisibility(View.GONE);
                cleanErrors();
                switch (position) {
                    case TITLE_SELECTION_MAN:
                        getPresenter().selectedTitle(TITLE_MAN);
                        break;
                    case TITLE_SELECTION_NOMAN:
                        getPresenter().selectedTitle(TITLE_NOMAN);
                        break;
                    case TITLE_SELECTION_COMPANY:
                        companyContainer.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                        companyContainer.setVisibility(View.VISIBLE);
                        companyContainer.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
                        getPresenter().selectedTitle(TITLE_COMPANY);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }

    private void initPhoneEt() {
        teleEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setPhone(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
//nothing
            }
        });
    }

    private void initCompanyEt() {

        companyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setCompany(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
//nothing
            }
        });
    }

    private void initFamilyNameEt() {
        familyNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setCustomerFamilyName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
//nothing
            }
        });
    }

    private void initNameEt() {
        nameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setCustomerName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
//nothing
            }
        });
    }

    private void initEmailEt() {
        emailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
//nothing
            }
        });
    }

    @OnClick(R.id.fragment_report_form_3_title_spinner_arrow)
    public void onSpinnerClicked() {
        titleSpinner.performClick();
    }

    @Override
    public int getTabLayoutId() {
        return R.layout.fragment_report_form_3;
    }

    @Override
    public int getTabId() {
        return TAB_ID_3;
    }

    @Override
    public void initReport(Report report) {
        cleanErrors();
        nameEt.setText(report.getCustomerName());
        familyNameEt.setText(report.getCustomerFamilyName());
        teleEt.setText(report.getPhone());
        emailEt.setText(report.getEmail());
        companyEt.setText(report.getCompany());
        if (!TextUtils.isEmpty(report.getTitle())) {
            switch (report.getTitle()) {
                case TITLE_MAN:
                    titleSpinner.setSelection(TITLE_SELECTION_MAN);
                    break;
                case TITLE_NOMAN:
                    titleSpinner.setSelection(TITLE_SELECTION_NOMAN);
                    break;
                case TITLE_COMPANY:
                    titleSpinner.setSelection(TITLE_SELECTION_COMPANY);
                    break;
            }
        }
    }

    @Override
    public void cleanErrors() {
        nameError.setVisibility(View.GONE);
        familyNameError.setVisibility(View.GONE);
        companyError.setVisibility(View.GONE);
        phoneError.setVisibility(View.GONE);
        emailError.setVisibility(View.GONE);
    }

    @Override
    public void showNoCompanyError() {
        companyError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoCustomerNameError() {
        nameError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoCustomerFamilyNameError() {
        familyNameError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoPhoneError() {
        phoneError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmailError() {
        emailError.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTrackerInfoId() {
        return R.string.tracker_report_disturbance_tab3;
    }

    @OnClick(R.id.fragment_report_button_to_tab4)
    public void onNextClicked() {
        getPresenter().onTab3Finished();
    }
}
