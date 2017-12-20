package de.bitb.astroskop.ui.main.analysis.map;


import java.util.List;

import javax.inject.Inject;

import de.bitb.astroskop.controller.PowerController;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.DistrictPower;
import de.bitb.astroskop.utils.NetworkUtils;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.api.ControllerCallback;
import de.bitb.astroskop.ui.base.BasePresenter;


public class MapViewPresenter extends BasePresenter<IMapView> implements IInjection {

    @Inject
    protected PowerController powerController;

    @Inject
    protected NetworkUtils networkUtils;

    public MapViewPresenter(SNHApplication application, IMapView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onResume() {
        refreshView();
    }

    public void refreshView() {
        if (networkUtils.isNetworkAvailable()) {
            getView().hideNoNetworkError();
            powerController.loadDistrictPower(new ControllerCallback<DistrictPower>() {
                @Override
                public void onSuccess(List<DistrictPower> response) {
                    onSuccess(response.get(0));
                }

                @Override
                public void onSuccess(DistrictPower response) {
                    getView().tintAltona(response.getPowerAltona());
                    getView().tintEimsbuettel(response.getPowerEimsbuettel());
                    getView().tintNord(response.getPowerNord());
                    getView().tintWandsbek(response.getPowerWandsbek());
                    getView().tintBergedorf(response.getPowerBergedorf());
                    getView().tintHarburg(response.getPowerHarburg());
                    getView().tintMitte(response.getPowerMitte());
                }

                @Override
                public void onFailure() {
                    getView().tintAltona(0);
                    getView().tintEimsbuettel(0);
                    getView().tintNord(0);
                    getView().tintWandsbek(0);
                    getView().tintBergedorf(0);
                    getView().tintHarburg(0);
                    getView().tintMitte(0);
                }
            });
        } else {
            getView().showNoNetworkError();
        }
    }

    public void onVisible() {
        getTracker().trackScreenView(getContext().getString(R.string.tracker_analysis_map));
        refreshView();
    }
}
