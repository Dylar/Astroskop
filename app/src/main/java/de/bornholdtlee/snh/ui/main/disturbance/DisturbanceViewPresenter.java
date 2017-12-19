package de.bornholdtlee.snh.ui.main.disturbance;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.model.Disturbance;
import de.bornholdtlee.snh.ui.base.BasePresenter;
import de.bornholdtlee.snh.ui.main.disturbance.adapter.IDisturbanceRowView;
import de.bornholdtlee.snh.utils.NetworkUtils;

public class DisturbanceViewPresenter extends BasePresenter<IDisturbanceView> implements IInjection {

    @Inject
    protected DisturbanceController disturbanceController;

    @Inject
    protected NetworkUtils networkUtils;

    protected List<Disturbance> disturbances = new ArrayList<>();

    public DisturbanceViewPresenter(SNHApplication application, IDisturbanceView view) {
        super(application, view);
    }

    public void onResume() {
        loadAllDisturbance();
        refreshView();
    }

    private void loadAllDisturbance() {
        disturbanceController.loadAllDisturbance(new ControllerCallback<Disturbance>() {
            @Override
            public void onSuccess(List<Disturbance> response) {
                disturbances = response;
                Collections.sort(disturbances, (disturbance, t1) -> {
                    int result = disturbance.getDate().compareTo(t1.getDate());
                    if (result == 0) {
                        result = disturbance.getStart().compareTo(t1.getStart());
                    }
                    return result;
                });
                refreshView();
            }

            @Override
            public void onSuccess(Disturbance response) {
                //nothing
            }

            @Override
            public void onFailure() {
                getView().showNetworkErrorScreen();
            }
        });
    }

    public void refreshView() {
        boolean gotInternet = networkUtils.isNetworkAvailable();
        if (gotInternet) {
            if (disturbances.isEmpty()) {
                getView().showListEmpty();
            } else {
                getView().refreshView();
            }
        } else {
            getView().showNetworkErrorScreen();
        }
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onBindDisturbanceAtPosition(IDisturbanceRowView holder, int position) {
        holder.bindValues(disturbances.get(position));
    }

    public int getDisturbanceCount() {
        return disturbances.size();
    }

}
