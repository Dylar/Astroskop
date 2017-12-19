package de.bornholdtlee.snh.controller;

import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.api.Api;
import de.bornholdtlee.snh.api.ApiCallback;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.api.dto.DisturbanceDTOList;
import de.bornholdtlee.snh.api.dto.SearchNumberDTO;
import de.bornholdtlee.snh.model.Disturbance;
import de.bornholdtlee.snh.model.NumberSearchResult;
import de.bornholdtlee.snh.model.Report;
import de.bornholdtlee.snh.model.StreetSearchResult;
import de.bornholdtlee.snh.utils.CommonUtils;
import de.bornholdtlee.snh.utils.DateUtils;
import de.bornholdtlee.snh.utils.SharedPreferencesUtils;
import io.objectbox.Box;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class DisturbanceController {

    private static final String KEY_UPDATE_STREETS = "shouldUpdateStreet";
    private static final String KEY_UPDATE_DISTURBANCE = "shouldUpdateDisturbance";

    @Inject
    protected Api.Disturbance disturbanceApiInterface;

    @Inject
    protected Api.Constructions apiInterface;

    @Inject
    protected SharedPreferencesUtils prefUtils;

    @Inject
    protected Box<Disturbance> disturbanceBox;

    @Inject
    protected Box<StreetSearchResult> searchBox;
    @Inject
    protected CommonUtils commonUtils;

    public DisturbanceController(SNHApplication application) {
        application.getAppComponent().inject(this);
    }

    //Database

    public List<Disturbance> getAllDisturbance() {
        return disturbanceBox.getAll();
    }

    public void saveStreetSearchResults(List<StreetSearchResult> street) {
        searchBox.put(street);
    }

    public List<StreetSearchResult> getSearchResultByStreetName(String streetName) {
        return searchBox.query().filter(street -> commonUtils.containsCaseInsensitive(street.getStreet(), streetName)).build().find();
    }

    //API

    public void loadAllDisturbance(ControllerCallback<Disturbance> callback) {
        if (shouldLoadDisturbance()) {
            Call<DisturbanceDTOList> call = disturbanceApiInterface.getAllDisturbance();
            ApiCallback<DisturbanceDTOList> apiCallback = new ApiCallback<DisturbanceDTOList>() {

                @Override
                public void onSuccess(Call call, DisturbanceDTOList response) {
                    List<Disturbance> disturbances = response.getDisturbances();
                    disturbanceBox.removeAll();
                    disturbanceBox.put(disturbances);
                    callback.onSuccess(disturbances);
                    setUpdateTimestamp(KEY_UPDATE_DISTURBANCE);
                }

                @Override
                public void onError(Call call, Object response) {
                    super.onError(call, response);
                    callback.onFailure();
                }

            };
            call.enqueue(apiCallback);
        } else {
            callback.onSuccess(getAllDisturbance());
        }
    }

    public void loadAllStreetSearchResults(ControllerCallback<StreetSearchResult> callback) {
        if (shouldLoadStreetSearchResults()) {
            Call<List<StreetSearchResult>> call = apiInterface.getAllStreetSearchResult();
            ApiCallback<List<StreetSearchResult>> apiCallback = new ApiCallback<List<StreetSearchResult>>() {

                @Override
                public void onSuccess(Call call, List<StreetSearchResult> response) {
                    saveStreetSearchResults(response);
                    setUpdateTimestamp(KEY_UPDATE_STREETS);
                    callback.onSuccess(response);
                }

                @Override
                public void onError(Call call, Object response) {
                    callback.onFailure();
                }
            };
            call.enqueue(apiCallback);
        } else {
            callback.onFailure();
        }
    }

    public void searchNumberInWeb(String street, String city, String keyword, ControllerCallback<NumberSearchResult> callback) {
        Call<SearchNumberDTO> call = apiInterface.searchNumbersInWeb(street, city, keyword);
        ApiCallback<SearchNumberDTO> apiCallback = new ApiCallback<SearchNumberDTO>() {

            @Override
            public void onSuccess(Call call, SearchNumberDTO response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(Call call, Object response) {
                super.onError(call, response);
                callback.onFailure();
            }
        };

        call.enqueue(apiCallback);
    }

    public void sendReport(Report report, ControllerCallback<Void> callback) {
        Call<ResponseBody> call = apiInterface.sendReport(report);
        ApiCallback<ResponseBody> apiCallback = new ApiCallback<ResponseBody>() {

            @Override
            public void onSuccess(Call call, ResponseBody response) {
                callback.onSuccess();
            }

            @Override
            public void onError(Call call, Object response) {
                super.onError(call, response);
                callback.onFailure();
            }
        };

        call.enqueue(apiCallback);
    }

    //Prefs

    private void setUpdateTimestamp(String key) {
        prefUtils.putLong(key, System.currentTimeMillis());
    }

    private boolean shouldLoadDisturbance() {
        long timestamp = prefUtils.getLong(KEY_UPDATE_DISTURBANCE, 0);
        long now = System.currentTimeMillis();
        return now > (timestamp + DateUtils.ONE_DAY_IN_MILLI);
    }

    private boolean shouldLoadStreetSearchResults() {
        long timestamp = prefUtils.getLong(KEY_UPDATE_STREETS, 0);
        long now = System.currentTimeMillis();
        return now > (timestamp + DateUtils.ONE_MONTH_IN_MILLI);
    }

}
