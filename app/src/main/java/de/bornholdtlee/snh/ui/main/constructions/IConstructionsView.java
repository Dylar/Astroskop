package de.bornholdtlee.snh.ui.main.constructions;


import de.bornholdtlee.snh.ui.base.IBaseView;

public interface IConstructionsView extends IBaseView {

    void showNetworkErrorScreen();

    void openDistrictView(String district);

    void refreshView();

    void showListEmpty();
}
