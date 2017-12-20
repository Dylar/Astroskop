package de.bornholdtlee.astroskop.ui.report.form.search;

import de.bornholdtlee.astroskop.ui.base.IBaseView;

public interface ISearchView extends IBaseView {

    void setToolbarTitle(String string);

    void streetSelected(String streetName, String districtName);

    void showListEmpty();

    void refreshView();

    void showNetworkErrorScreen();

}
