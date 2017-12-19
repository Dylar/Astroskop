package de.bornholdtlee.snh.ui.main.disturbance;


import de.bornholdtlee.snh.ui.base.IBaseView;

public interface IDisturbanceView extends IBaseView {

    void showNetworkErrorScreen();

    void showListEmpty();

    void refreshView();

}
