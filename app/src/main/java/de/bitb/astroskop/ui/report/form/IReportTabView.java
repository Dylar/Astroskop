package de.bitb.astroskop.ui.report.form;


import de.bitb.astroskop.model.Report;
import de.bitb.astroskop.ui.base.IBaseView;

public interface IReportTabView extends IBaseView {

    int getTabId();

    void initReport(Report report);

    void setViewVisibility(Report report);

    void cleanErrors();

    void showNoLocationSelectedError();

    void showNoLocationDetailsError();

    void showNoStreetError();

    void showNoStreetNumberError();

    void showNoPlzError();

    void showNoCityError();

    void showNoCustomerNameError();

    void showNoCustomerFamilyNameError();

    void showNoPhoneError();

    void showEmailError();

    int getTrackerInfoId();

    void showNoDetailsSelectedError();

    void showNoCompanyError();
}
