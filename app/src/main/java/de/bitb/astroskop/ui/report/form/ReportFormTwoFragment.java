package de.bitb.astroskop.ui.report.form;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.model.NumberSearchResult;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.report.form.search.SearchNumberAdapter;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.model.Report;
import de.bitb.astroskop.ui.report.ReportTabBaseFragment;
import de.bitb.astroskop.ui.report.form.search.ReportFormSearchActivity;

import static android.app.Activity.RESULT_OK;
import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_2;
import static de.bitb.astroskop.ui.report.form.search.ReportFormSearchActivity.KEY_DISTRICT_NAME;
import static de.bitb.astroskop.ui.report.form.search.ReportFormSearchActivity.KEY_STREET_NAME;

public class ReportFormTwoFragment extends ReportTabBaseFragment {

    public static final String DEFAULT_CITY_HAMBURG = "Hamburg";

    @BindView(R.id.fragment_report_form_2_street_error_container)
    protected View streetError;

    @BindView(R.id.fragment_report_form_2_number_error_container)
    protected View numberError;

    @BindView(R.id.fragment_report_form_2_plz_error_container)
    protected View plzError;

    @BindView(R.id.fragment_report_form_2_city_error_container)
    protected View cityError;

    @BindView(R.id.fragment_report_form_2_search_street)
    protected TextView streetTv;

    @BindView(R.id.fragment_report_form_2_search_number_container)
    protected View numberContainer;

    @BindView(R.id.fragment_report_form_2_search_number)
    protected AutoCompleteTextView numberTv;

    @BindView(R.id.fragment_report_form_2_search_plz_city_container)
    protected View plzCityContainer;

    @BindView(R.id.fragment_report_form_2_search_plz)
    protected EditText plzTv;

    @BindView(R.id.fragment_report_form_2_search_city)
    protected EditText cityTv;

    private SearchNumberAdapter numberAdapter;

    public static ReportFormTwoFragment createInstance() {
        return new ReportFormTwoFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numberAdapter = new SearchNumberAdapter(getContext(), getPresenter());
        numberTv.setAdapter(numberAdapter);
        numberTv.setThreshold(1);
        numberTv.setOnItemClickListener((adapterView, view1, i, l) -> {
            NumberSearchResult result = numberAdapter.getItem(i);
            if (result != null) {
                numberTv.setText(result.get(0));
                plzTv.setText(result.get(1));
            }
            commonUtils.closeKeyboard(getContext(), numberTv.getWindowToken());
        });
        numberTv.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                numberTv.setText(numberTv.getText().toString());
                plzTv.setText("");
                return true;
            }
            return false;
        });
        numberTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().numberSelected(numberTv.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });
        plzTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().plzChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });

        cityTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().cityChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });
    }

    @Override
    public int getTabLayoutId() {
        return R.layout.fragment_report_form_2;
    }

    @Override
    public int getTabId() {
        return TAB_ID_2;
    }

    @Override
    public void initReport(Report report) {
        if (TextUtils.isEmpty(report.getStreetName())) {
            plzTv.setText("");
            cityTv.setText(DEFAULT_CITY_HAMBURG);
            numberTv.setText("");
        } else {
            streetTv.setText(report.getStreetName());
            if (TextUtils.isEmpty(report.getStreetNumber())) {
                numberTv.setText("");
                plzTv.setText("");
                cityTv.setText(DEFAULT_CITY_HAMBURG);
            } else {
                numberTv.setText(report.getStreetNumber());
                plzTv.setText(report.getPlz());
                cityTv.setText(report.getCityName());
            }
        }
        commonUtils.closeKeyboard(getContext(), streetTv.getWindowToken());
        setViewVisibility(report);
    }

    @Override
    public void setViewVisibility(Report report) {
        numberContainer.setVisibility(View.GONE);
        plzCityContainer.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(report.getStreetName())) {
            numberContainer.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(report.getStreetNumber())) {
                plzCityContainer.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void showNoStreetError() {
        streetError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoStreetNumberError() {
        numberError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoPlzError() {
        plzError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoCityError() {
        cityError.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTrackerInfoId() {
        return R.string.tracker_report_disturbance_tab2;
    }

    @Override
    public void cleanErrors() {
        runOnUiThread(() -> {
            streetError.setVisibility(View.GONE);
            numberError.setVisibility(View.GONE);
            plzError.setVisibility(View.GONE);
            cityError.setVisibility(View.GONE);
        });
    }

    @OnClick(R.id.fragment_report_form_2_search_street)
    public void openSearch() {
        cleanErrors();
        ReportFormSearchActivity.startActivity((BaseActivity) getActivity());
    }

    @OnClick(R.id.fragment_report_button_to_tab3)
    public void onNextClicked() {
        getPresenter().onTab2Finished();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ReportFormSearchActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            getPresenter().streetSelected(data.getStringExtra(KEY_STREET_NAME), data.getStringExtra(KEY_DISTRICT_NAME));
        }
    }

}
