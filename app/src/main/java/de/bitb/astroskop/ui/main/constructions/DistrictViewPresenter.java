package de.bitb.astroskop.ui.main.constructions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import de.bitb.astroskop.controller.ConstructionsController;
import de.bitb.astroskop.controller.DisturbanceController;
import de.bitb.astroskop.utils.NetworkUtils;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Street;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.ui.main.constructions.adapter.IDistrictRowView;

public class DistrictViewPresenter extends BasePresenter<IDistrictView> implements IInjection {

    @Inject
    protected ConstructionsController constructionsController;

    @Inject
    protected DisturbanceController disturbanceController;

    @Inject
    protected NetworkUtils networkUtils;

    private String district;
    private List<Street> streets;
    private Set<Street> openStreets;

    public DistrictViewPresenter(SNHApplication application, IDistrictView view, String districtID) {
        super(application, view);
        if (districtID == null) {
            this.streets = new ArrayList<>();
        } else {
            this.district = districtID;
            this.streets = constructionsController.getStreetsByDistrict(district);
        }
        this.openStreets = new HashSet<>();
    }

    public DistrictViewPresenter(SNHApplication application, IDistrictView districtView) {
        this(application, districtView, null);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public int getConstructionsCount() {
        return streets.size();
    }

    public void onBindConstructionAtPosition(IDistrictRowView holder, int position) {
        holder.bindValues(streets.get(position));
    }

    public void onResume() {
        getTracker().trackScreenView(getContext().getString(R.string.tracker_constructions_district));
        if (district != null) {
            getView().setToolbarTitle(district);
        }
        refreshView();
    }

    public boolean isStreetOpen(Street street) {
        return openStreets.contains(street);
    }

    public void openStreet(Street street) {
        boolean open = !openStreets.remove(street);
        if (open) {
            openStreets.add(street);
        }
    }

    public void searchStreets(String streetName) {
        openStreets.clear();
        streets = constructionsController.getStreetsByName(streetName);
        Collections.sort(streets, (street, t1) -> street.getName().compareTo(t1.getName()));
        refreshView();
    }

    public void refreshView() {
        if (networkUtils.isNetworkAvailable()) {
            if (streets.isEmpty()) {
                getView().showListEmpty();
            } else {
                getView().refreshView();
            }
        } else {
            getView().showNetworkErrorScreen();
        }
    }
}
