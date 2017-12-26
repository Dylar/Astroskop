package de.bitb.astroskop.ui.main;


import de.bitb.astroskop.ui.base.IBaseView;

public interface IMainView extends IBaseView {
    void navigateToHomeScreen();

    void navigateToCircumstanceScreen();

    void navigateToScreen(int tabId);
}
