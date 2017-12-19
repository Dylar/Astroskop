package de.bornholdtlee.snh.api.converter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.helper.Logger;

public class DistrictPowerConverter implements JsonDeserializer<DistrictPower> {

    public static final String KEY_USAGE = "usage";

    @Override
    public DistrictPower deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        DistrictPower power = new DistrictPower();

        try {
            JSONObject jsonO = new JSONObject(json.getAsJsonObject().toString());
            jsonO = jsonO.getJSONObject("districtsPower");
            power.setPowerAltona(jsonO.getJSONObject("Altona").getInt(KEY_USAGE));
            power.setPowerEimsbuettel(jsonO.getJSONObject("Eimsbüttel").getInt(KEY_USAGE));
            power.setPowerNord(jsonO.getJSONObject("Nord").getInt(KEY_USAGE));
            power.setPowerMitte(jsonO.getJSONObject("Mitte").getInt(KEY_USAGE));
            power.setPowerHarburg(jsonO.getJSONObject("Harburg").getInt(KEY_USAGE));
            power.setPowerWandsbek(jsonO.getJSONObject("Wandsbek").getInt(KEY_USAGE));
            power.setPowerBergedorf(jsonO.getJSONObject("Bergedorf").getInt(KEY_USAGE));
            power.setPowerHamburg(jsonO.getJSONObject("Hamburg").getInt(KEY_USAGE));
        } catch (JSONException e) {
            Logger.debug(e.getMessage());
        }
        return power;
    }

}
