package de.bornholdtlee.snh.ui.main.constructions;


import de.bornholdtlee.snh.ui.base.IBaseView;

public interface IDistrictView extends IBaseView {

    void setToolbarTitle(String name);

    void showNetworkErrorScreen();

    void refreshView();

    void showListEmpty();
}
