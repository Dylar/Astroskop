package de.bornholdtlee.snh.ui.main;


import de.bornholdtlee.snh.ui.base.IBaseView;

public interface IMainView extends IBaseView {
    void navigateToHomeScreen();

    void navigateToAnalysisScreen();

    void navigateToConstructionsScreen();

    void navigateToDisturbanceScreen();

    void navigateToScreen(int tabId);

    void navigateToReportDisturbanceScreen();

    void openDistrictView(String district);
}
