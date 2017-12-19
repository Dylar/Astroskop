package de.bornholdtlee.snh.ui.main.analysis.data;


import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.controller.PowerController;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.ui.base.BasePresenter;
import de.bornholdtlee.snh.utils.NetworkUtils;

public class DataViewPresenter extends BasePresenter<IDataView> implements IInjection {

    public static final String FILTER_WANDSBEK = "Wandsbek";
    public static final String FILTER_ALTONA = "Altona";
    public static final String FILTER_BERGEDORF = "Bergedorf";
    public static final String FILTER_EIMSBUETTEL = "Eimsbüttel";
    public static final String FILTER_HAMBURG = "Hamburg gesamt";
    public static final String FILTER_HARBURG = "Harburg";
    public static final String FILTER_MITTE = "Hamburg Mitte";
    public static final String FILTER_NORD = "Hamburg Nord";

    @Inject
    protected PowerController powerController;
    @Inject
    protected NetworkUtils networkUtils;

    public DataViewPresenter(SNHApplication application, IDataView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void refreshView() {
        if (networkUtils.isNetworkAvailable()) {
            getView().hideNoNetworkError();
            loadGraph();
            unleashPower();
        } else {
            getView().showNoNetworkError();
        }
    }

    private void unleashPower() {
        powerController.loadDistrictPower(new ControllerCallback<DistrictPower>() {
            @Override
            public void onSuccess(List<DistrictPower> response) {
                onSuccess(response.get(0));
            }

            @Override
            public void onSuccess(DistrictPower response) {
                switch (powerController.getSelectedFilter()) {
                    case FILTER_ALTONA:
                        getView().updateCurrentPower(response.getPowerAltona(), FILTER_ALTONA);
                        break;
                    case FILTER_EIMSBUETTEL:
                        getView().updateCurrentPower(response.getPowerEimsbuettel(), FILTER_EIMSBUETTEL);
                        break;
                    case FILTER_NORD:
                        getView().updateCurrentPower(response.getPowerNord(), FILTER_NORD);
                        break;
                    case FILTER_MITTE:
                        getView().updateCurrentPower(response.getPowerMitte(), FILTER_MITTE);
                        break;
                    case FILTER_HARBURG:
                        getView().updateCurrentPower(response.getPowerHarburg(), FILTER_HARBURG);
                        break;
                    case FILTER_BERGEDORF:
                        getView().updateCurrentPower(response.getPowerBergedorf(), FILTER_BERGEDORF);
                        break;
                    case FILTER_WANDSBEK:
                        getView().updateCurrentPower(response.getPowerWandsbek(), FILTER_WANDSBEK);
                        break;
                    case FILTER_HAMBURG:
                    default:
                        getView().updateCurrentPower(response.getPowerHamburg(), FILTER_HAMBURG);
                        break;
                }
                getView().updateLastSync(powerController.getUpdateTimestamp());
            }

            @Override
            public void onFailure() {
                getView().updateLastSync(powerController.getUpdateTimestamp());
            }

        });
    }

    private void loadGraph() {
        if (FILTER_HAMBURG.equals(powerController.getSelectedFilter())) {
            powerController.loadGraph(new ControllerCallback<Integer>() {
                @Override
                public void onSuccess(List<Integer> response) {
                    List<Entry> entries = new ArrayList<>();
                    for (int i = 0; i < response.size(); i++) {
                        Integer dd = response.get(i);
                        entries.add(new Entry(i, dd));
                    }
                    getView().updateChart(entries);
                }

                @Override
                public void onSuccess(Integer response) {
//nothing
                }

                @Override
                public void onFailure() {
//nothing
                }
            });
        } else {
            getView().updateChart(null);
        }
    }

    public void onFilterClicked() {
        getView().openFilter(powerController.getSelectedFilter());
    }

    public void filterSelected(String selectedFilter) {
        powerController.setSelectedFilter(selectedFilter);
        refreshView();
    }

    public void onVisible() {
        getTracker().trackScreenView(getContext().getString(R.string.tracker_analysis_data));
        refreshView();
    }

    public void onRefreshClicked() {
        powerController.setUpdateTimestamp(0);
        refreshView();
    }
}
