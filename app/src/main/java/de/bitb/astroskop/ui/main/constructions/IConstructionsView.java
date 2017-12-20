package de.bitb.astroskop.ui.main.constructions;


import de.bitb.astroskop.ui.base.IBaseView;

public interface IConstructionsView extends IBaseView {

    void showNetworkErrorScreen();

    void openDistrictView(String district);

    void refreshView();

    void showListEmpty();
}
