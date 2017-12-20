package de.bitb.astroskop.controller;

import javax.inject.Inject;

import de.bitb.astroskop.api.ApiCallback;
import de.bitb.astroskop.model.DistrictPower;
import de.bitb.astroskop.utils.SharedPreferencesUtils;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.api.Api;
import de.bitb.astroskop.api.ControllerCallback;
import de.bitb.astroskop.model.PowerGraph;
import de.bitb.astroskop.utils.DateUtils;
import io.objectbox.Box;
import retrofit2.Call;

import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_HAMBURG;

public class PowerController {

    private static final String KEY_SHOULD_UPDATE = "powerUpdate";
    private static final String KEY_SELECTED_FILTER = "selectedFilter";

    @Inject
    protected SharedPreferencesUtils preferencesUtils;

    @Inject
    protected Api.Consumptions api;

    @Inject
    protected Box<DistrictPower> powerBox;

    @Inject
    protected Box<PowerGraph> graphBox;

    public PowerController(SNHApplication application) {
        application.getAppComponent().inject(this);
    }

    //database

    private void savePower(DistrictPower power) {
        powerBox.put(power);
    }

    private DistrictPower getPower() {
        return powerBox.getAll().get(0);
    }

    private void saveGraph(PowerGraph graph) {
        graphBox.put(graph);
    }

    private PowerGraph getGraph() {
        return graphBox.getAll().get(0);
    }

    //Api

    public void loadGraph(ControllerCallback<Integer> callback) {
        if (shouldUpdate()) {
            Call<PowerGraph> call = api.getAllGraph();
            ApiCallback<PowerGraph> apiCallback = new ApiCallback<PowerGraph>() {

                @Override
                public void onSuccess(Call call, PowerGraph graph) {
                    setUpdateTimestamp(System.currentTimeMillis());
                    graphBox.removeAll();
                    saveGraph(graph);
                    callback.onSuccess(graph.getStress());
                }

                @Override
                public void onError(Call call, Object response) {
                    callback.onFailure();
                }
            };
            call.enqueue(apiCallback);
        } else {
            callback.onSuccess(getGraph().getStress());
        }
    }

    public void loadDistrictPower(ControllerCallback<DistrictPower> callback) {
        if (shouldUpdate()) {
            Call<DistrictPower> call = api.getDistrictPower(System.currentTimeMillis());
            ApiCallback<DistrictPower> apiCallback = new ApiCallback<DistrictPower>() {

                @Override
                public void onSuccess(Call call, DistrictPower response) {
                    setUpdateTimestamp(System.currentTimeMillis());
                    powerBox.removeAll();
                    savePower(response);
                    callback.onSuccess(response);
                }

                @Override
                public void onError(Call call, Object response) {
                    callback.onFailure();
                }
            };
            call.enqueue(apiCallback);
        } else {
            callback.onSuccess(getPower());
        }
    }

    //Prefs

    private boolean shouldUpdate() {
        long timestamp = getUpdateTimestamp();
        long now = System.currentTimeMillis();
        return now > (timestamp + DateUtils.FIFTEEN_MIN_IN_MILLI);
    }

    public void setUpdateTimestamp(long timestamp) {
        preferencesUtils.putLong(KEY_SHOULD_UPDATE, timestamp);
    }

    public long getUpdateTimestamp() {
        return preferencesUtils.getLong(KEY_SHOULD_UPDATE, 0);
    }

    public String getSelectedFilter() {
        return preferencesUtils.getString(KEY_SELECTED_FILTER, FILTER_HAMBURG);
    }

    public void setSelectedFilter(String selectedFilter) {
        preferencesUtils.putString(KEY_SELECTED_FILTER, selectedFilter);
    }
}
