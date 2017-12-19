package de.bornholdtlee.snh.ui.report;

import android.text.TextUtils;

import javax.inject.Inject;

import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.enums.ReportDetails;
import de.bornholdtlee.snh.enums.ReportLocation;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.model.NumberSearchResult;
import de.bornholdtlee.snh.model.Report;
import de.bornholdtlee.snh.ui.base.BasePresenter;
import de.bornholdtlee.snh.ui.report.form.IReportTabView;
import de.bornholdtlee.snh.utils.NetworkUtils;
import lombok.Getter;
import lombok.Setter;

import static de.bornholdtlee.snh.ui.report.form.ReportFormThreeFragment.TITLE_COMPANY;

public class ReportPresenter extends BasePresenter<IReportView> implements IInjection {

    public static final int TAB_ID_1 = 1;
    public static final int TAB_ID_2 = 2;
    public static final int TAB_ID_3 = 3;
    public static final int TAB_ID_4 = 4;

    @Inject
    protected DisturbanceController disturbanceController;

    @Inject
    protected NetworkUtils networkUtils;

    @Getter
    private IReportTabView tabView;

    @Setter
    private Report report;

    public ReportPresenter(SNHApplication application, IReportView view) {
        super(application, view);
        report = new Report();
    }

    public void setTabView(IReportTabView tabView) {
        this.tabView = tabView;
        getTracker().trackScreenView(getContext().getString(tabView.getTrackerInfoId()));
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onResume() {
        getTabView().initReport(report);
    }

    public void onTab1Clicked() {
        if (tabView.getTabId() > TAB_ID_1) {
            getView().navigateToReportFormTab1Screen();
        }
    }

    public void onTab2Clicked() {
        if (tabView.getTabId() > TAB_ID_2) {
            getView().navigateToReportFormTab2Screen();
        }
    }

    public void onTab3Clicked() {
        if (tabView.getTabId() > TAB_ID_3) {
            getView().navigateToReportFormTab3Screen();
        }
    }

    public void onTab4Clicked() {
        if (tabView.getTabId() > TAB_ID_4) {
            getView().navigateToReportFormTab4Screen();
        }
    }

    //TAB 1

    public void selectedLocation(ReportLocation singlehouse) {
        report.setLocation(singlehouse);
    }

    public void setMoreDetails(String details) {
        report.setMoreDetails(details);
    }

    public void setLocationDetails(String locationDetails) {
        report.setLocationDetails(locationDetails);
    }

    public void selectDetails(ReportDetails details) {
        report.setDetails(details);
    }

    public void onTab1Finished() {
        if (report.getLocation() == null) {
            getTabView().showNoLocationSelectedError();
        } else {
            boolean finishTab = true;
            switch (report.getLocation()) {
                case SINGLEHOUSE:
                case MULTIHOUSE:
                    finishTab = report.getDetails() != null;
                    if (!finishTab) {
                        getTabView().showNoDetailsSelectedError();
                    }
                    break;
                case REST:
                    finishTab = !TextUtils.isEmpty(report.getLocationDetails());
                    if (!finishTab) {
                        getTabView().showNoLocationDetailsError();
                    }
                    break;
            }
            if (finishTab) {
                getView().navigateToReportFormTab2Screen();
            }
        }
    }

    //TAB 2

    public void streetSelected(String streetName, String cityName) {
        report.setStreetName(streetName);
        report.setCityName(cityName);
        report.setStreetNumber(null);
        report.setPlz(null);
        getTabView().initReport(report);
    }

    public void searchNumber(String number, ControllerCallback<NumberSearchResult> callback) {
        getTabView().cleanErrors();
        disturbanceController.searchNumberInWeb(report.getStreetName(), report.getCityName(), number, callback);
    }

    public void numberSelected(String number) {
        report.setStreetNumber(number);
        getTabView().setViewVisibility(report);
    }

    public void onTab2Finished() {
        if (TextUtils.isEmpty(report.getStreetName())) {
            getTabView().showNoStreetError();
        } else if (TextUtils.isEmpty(report.getStreetNumber())) {
            getTabView().showNoStreetNumberError();
        } else if (TextUtils.isEmpty(report.getPlz())) {
            getTabView().showNoPlzError();
        } else if (TextUtils.isEmpty(report.getCityName())) {
            getTabView().showNoCityError();
        } else {
            getView().navigateToReportFormTab3Screen();
        }
    }

    public void onTab3Finished() {
        if (TextUtils.isEmpty(report.getCustomerName())) {
            getTabView().showNoCustomerNameError();
        } else if (TextUtils.isEmpty(report.getCustomerFamilyName())) {
            getTabView().showNoCustomerFamilyNameError();
        } else if (TITLE_COMPANY.equals(report.getTitle()) && TextUtils.isEmpty(report.getCompany())) {
            getTabView().showNoCompanyError();
        } else if (TextUtils.isEmpty(report.getPhone())) {
            getTabView().showNoPhoneError();
        } else {
            String email = report.getEmail();
            if (TextUtils.isEmpty(email) || email.contains("@") && report.getEmail().contains(".")) {
                getView().navigateToReportFormTab4Screen();
            } else {
                getTabView().showEmailError();
            }
        }
    }

    public void selectedTitle(String title) {
        report.setTitle(title);
        getTabView().cleanErrors();
    }

    public void setCustomerName(String customerName) {
        report.setCustomerName(customerName);
        getTabView().cleanErrors();
    }

    public void setCustomerFamilyName(String customerFamilyName) {
        report.setCustomerFamilyName(customerFamilyName);
        getTabView().cleanErrors();
    }

    public void setCompany(String company) {
        report.setCompany(company);
        getTabView().cleanErrors();
    }

    public void setPhone(String phone) {
        report.setPhone(phone);
        getTabView().cleanErrors();
    }

    public void setEmail(String email) {
        report.setEmail(email);
        getTabView().cleanErrors();
    }

    public void plzChanged(String plz) {
        report.setPlz(plz);
        getTabView().cleanErrors();
    }

    public void cityChanged(String city) {
        report.setCityName(city);
        getTabView().cleanErrors();
    }

    public void finishReport() {
        if (networkUtils.isNetworkAvailable()) {
            disturbanceController.sendReport(report, new ControllerCallback<Void>() {
                @Override
                public void onSuccess() {
                    super.onSuccess();
                    getView().navigateToReportSentScreen();
                }

                @Override
                public void onFailure() {
                    super.onFailure();
                    getView().showNoNetworkError();
                }
            });
        } else {
            getView().showNoNetworkError();
        }
    }

}
