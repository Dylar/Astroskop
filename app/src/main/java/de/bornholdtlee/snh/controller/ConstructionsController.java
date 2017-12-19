package de.bornholdtlee.snh.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.api.Api;
import de.bornholdtlee.snh.api.ApiCallback;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.model.Construction;
import de.bornholdtlee.snh.model.Street;
import de.bornholdtlee.snh.utils.CommonUtils;
import de.bornholdtlee.snh.utils.DateUtils;
import de.bornholdtlee.snh.utils.SharedPreferencesUtils;
import io.objectbox.Box;
import retrofit2.Call;

public class ConstructionsController {

    private static final String KEY_SHOULD_UPDATE = "shoudlupdateconstructions";

    @Inject
    protected Api.Constructions apiInterface;
    @Inject
    protected Box<Construction> constructionBox;
    @Inject
    protected SharedPreferencesUtils prefUtils;
    @Inject
    protected CommonUtils commonUtils;

    public ConstructionsController(SNHApplication application) {
        application.getAppComponent().inject(this);
    }

    //DB

    private List<Construction> getAllConstruction() {
        return constructionBox.getAll();
    }

    public List<Street> getStreetsByDistrict(String district) {
        List<String> districts = new ArrayList<>();
        districts.add(district);
        return getStreetsByDistrict(districts);
    }

    public List<Street> getStreetsByDistrict(List<String> districts) {
        Set<Street> streets = new HashSet<>();
        List<Construction> constructions = constructionBox.query().filter(construction -> districts.contains(construction.getDistrict())).build().find();
        for (Construction construction : constructions) {
            streets.add(getStreetFromConstruction(construction));
        }
        ArrayList<Street> result = new ArrayList<>(streets);
        Collections.sort(result, (street1, t1) -> street1.getName().compareTo(t1.getName()));
        return result;
    }

    public List<Street> getStreetsByName(String streetName) {
        Set<Street> streets = new HashSet<>();
        List<Construction> constructions = constructionBox.query().filter(construction -> commonUtils.containsCaseInsensitive(construction.getStreet(), streetName)).build().find();
        for (Construction construction : constructions) {
            streets.add(getStreetFromConstruction(construction));
        }
        ArrayList<Street> result = new ArrayList<>(streets);
        Collections.sort(result, (street1, t1) -> street1.getName().compareTo(t1.getName()));
        return result;
    }

    public List<String> getDistrictsList() {
        Set<String> districts = new HashSet<>();
        List<Construction> constructions = getAllConstruction();
        for (Construction construction : constructions) {
            districts.add(construction.getDistrict());
        }
        ArrayList<String> result = new ArrayList<>(districts);
        Collections.sort(result, String::compareTo);
        return result;
    }

    private Street getStreetFromConstruction(Construction construction) {
        Street street = new Street();
        street.setName(construction.getStreet());
        street.setNumber(construction.getStreetNumber());
        street.setConstructionsEnd(construction.getEnd());
        street.setConstructionsStart(construction.getStart());
        street.setConstructionsSize(construction.getSize());
        return street;
    }

    //API

    public void loadAllConstructions(ControllerCallback<Construction> callback) {
        if (shouldUpdate()) {
            Call<List<Construction>> call = apiInterface.getAllConstructions();
            ApiCallback<List<Construction>> apiCallback = new ApiCallback<List<Construction>>() {

                @Override
                public void onSuccess(Call call, List<Construction> response) {
                    constructionBox.removeAll();
                    constructionBox.put(response);
                    callback.onSuccess(response);
                    setUpdateTimestamp();
                }

                @Override
                public void onError(Call call, Object response) {
                    callback.onFailure();
                }
            };
            call.enqueue(apiCallback);
        } else {
            callback.onSuccess(getAllConstruction());
        }
    }

    //Prefs

    private boolean shouldUpdate() {
        long timestamp = prefUtils.getLong(KEY_SHOULD_UPDATE, 0);
        long now = System.currentTimeMillis();
        return now > (timestamp + DateUtils.ONE_DAY_IN_MILLI);
    }

    private void setUpdateTimestamp() {
        prefUtils.putLong(KEY_SHOULD_UPDATE, System.currentTimeMillis());
    }
}
