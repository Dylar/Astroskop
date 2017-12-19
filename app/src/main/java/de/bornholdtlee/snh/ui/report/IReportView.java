package de.bornholdtlee.snh.ui.report;


import de.bornholdtlee.snh.ui.base.IBaseView;

public interface IReportView extends IBaseView {

    void navigateToReportFormTab1Screen();

    void navigateToReportFormTab2Screen();

    void navigateToReportFormTab3Screen();

    void navigateToReportFormTab4Screen();

    void navigateToReportAlternativeScreen();

    void showNoNetworkError();

    void navigateToReportSentScreen();
}
