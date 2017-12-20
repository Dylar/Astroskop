package de.bitb.astroskop.ui.main.constructions;


import de.bitb.astroskop.ui.base.IBaseView;

public interface IDistrictView extends IBaseView {

    void setToolbarTitle(String name);

    void showNetworkErrorScreen();

    void refreshView();

    void showListEmpty();
}
