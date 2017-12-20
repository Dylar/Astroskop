package de.bitb.astroskop.ui.report;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.ui.report.form.IReportTabView;
import de.bitb.astroskop.utils.CommonUtils;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.model.Report;
import lombok.Getter;

import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_1;
import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_2;
import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_3;
import static de.bitb.astroskop.ui.report.ReportPresenter.TAB_ID_4;

public abstract class ReportTabBaseFragment extends ReportBaseFragment implements IReportTabView, IInjection {

    @BindView(R.id.fragment_report_form_tab_1)
    protected TextView tab1;
    @BindView(R.id.fragment_report_form_tab_2)
    protected TextView tab2;
    @BindView(R.id.fragment_report_form_tab_3)
    protected TextView tab3;
    @BindView(R.id.fragment_report_form_tab_4)
    protected TextView tab4;

    @Inject
    protected CommonUtils commonUtils;

    @Getter
    private ReportPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.presenter = ((ReportActivity) context).getPresenter();
        this.presenter.setTabView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getTabLayoutId(), null, false);
        ViewGroup content = root.findViewById(R.id.fragment_report_form_container);
        content.removeAllViews();
        content.addView(view);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int currentTab = getTabId();
        switch (currentTab) {
            case TAB_ID_1:
                tab1.setBackgroundResource(R.drawable.underline_blue);
                tab1.setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                break;
            case TAB_ID_2:
                tab2.setBackgroundResource(R.drawable.underline_blue);
                tab2.setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                break;
            case TAB_ID_3:
                tab3.setBackgroundResource(R.drawable.underline_blue);
                tab3.setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                break;
            case TAB_ID_4:
                tab4.setBackgroundResource(R.drawable.underline_blue);
                tab4.setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().setTabView(this);
        getPresenter().onResume();
        commonUtils.closeKeyboard(getContext(), tab4.getWindowToken());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report_form_base;
    }

    public abstract int getTabLayoutId();

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_back;
    }

    @OnClick(R.id.fragment_report_form_tab_1)
    public void onTab1Clicked() {
        presenter.onTab1Clicked();
    }

    @OnClick(R.id.fragment_report_form_tab_2)
    public void onTab2Clicked() {
        presenter.onTab2Clicked();
    }

    @OnClick(R.id.fragment_report_form_tab_3)
    public void onTab3Clicked() {
        presenter.onTab3Clicked();
    }

    @OnClick(R.id.fragment_report_form_tab_4)
    public void onTab4Clicked() {
        presenter.onTab4Clicked();
    }

    @Override
    public void setViewVisibility(Report report) {
//implement is needed
    }

    @Override
    public void showNoLocationSelectedError() {
//implement is needed
    }

    @Override
    public void showNoLocationDetailsError() {
//implement is needed
    }

    @Override
    public void showNoCustomerNameError() {
//implement is needed
    }

    @Override
    public void showNoCustomerFamilyNameError() {
//implement is needed
    }

    @Override
    public void showNoPhoneError() {
//implement is needed
    }

    @Override
    public void showEmailError() {
//implement is needed
    }

    @Override
    public void cleanErrors() {
//implement is needed
    }

    @Override
    public void showNoStreetError() {
//implement is needed
    }

    @Override
    public void showNoStreetNumberError() {
//implement is needed
    }

    @Override
    public void showNoDetailsSelectedError() {
//implement is needed
    }

    @Override
    public void showNoPlzError() {
//implement is needed
    }

    @Override
    public void showNoCityError() {
//implement is needed
    }

    @Override
    public void showNoCompanyError() {
//implement is needed
    }
}
