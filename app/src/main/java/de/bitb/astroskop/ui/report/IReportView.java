package de.bornholdtlee.astroskop.ui.report;


import de.bornholdtlee.astroskop.ui.base.IBaseView;

public interface IReportView extends IBaseView {

    void navigateToReportFormTab1Screen();

    void navigateToReportFormTab2Screen();

    void navigateToReportFormTab3Screen();

    void navigateToReportFormTab4Screen();

    void navigateToReportAlternativeScreen();

    void showNoNetworkError();

    void navigateToReportSentScreen();
}
