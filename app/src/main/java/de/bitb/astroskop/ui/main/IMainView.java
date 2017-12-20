package de.bitb.astroskop.ui.main;


import de.bitb.astroskop.ui.base.IBaseView;

public interface IMainView extends IBaseView {
    void navigateToHomeScreen();

    void navigateToAnalysisScreen();

    void navigateToConstructionsScreen();

    void navigateToDisturbanceScreen();

    void navigateToScreen(int tabId);

    void navigateToReportDisturbanceScreen();

    void openDistrictView(String district);
}
