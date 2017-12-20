package de.bitb.astroskop.ui.main.disturbance;


import de.bitb.astroskop.ui.base.IBaseView;

public interface IDisturbanceView extends IBaseView {

    void showNetworkErrorScreen();

    void showListEmpty();

    void refreshView();

}
