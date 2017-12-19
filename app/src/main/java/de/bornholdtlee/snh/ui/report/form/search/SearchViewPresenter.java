package de.bornholdtlee.snh.ui.report.form.search;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.controller.ConstructionsController;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.model.StreetSearchResult;
import de.bornholdtlee.snh.ui.base.BasePresenter;
import de.bornholdtlee.snh.utils.NetworkUtils;

public class SearchViewPresenter extends BasePresenter<ISearchView> implements IInjection {

    @Inject
    protected ConstructionsController constructionsController;

    @Inject
    protected DisturbanceController disturbanceController;

    @Inject
    protected NetworkUtils networkUtils;

    private List<StreetSearchResult> results = new ArrayList<>();

    public SearchViewPresenter(SNHApplication application, ISearchView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public int getSearchCount() {
        return results.size();
    }

    public void onBindSearchAtPosition(ISearchRowView holder, int position) {
        holder.bindValues(results.get(position));
    }

    public void onResume() {
        getView().setToolbarTitle(getContext().getString(R.string.activity_search_title_street));
        refreshView();
        getTracker().trackScreenView(getContext().getString(R.string.tracker_report_disturbance_search));
    }

    public void searchStreets(String streetName) {
        results = disturbanceController.getSearchResultByStreetName(streetName);
        refreshView();
    }

    public void refreshView() {
//        if (networkUtils.isNetworkAvailable()) {
            if (results.isEmpty()) {
                getView().showListEmpty();
            } else {
                getView().refreshView();
            }
//        } else {
//            getView().showNetworkErrorScreen();
//        }
    }

    public void selectStreet(String streetName, String districtName) {
        getView().streetSelected(streetName, districtName);
    }
}
