package de.bornholdtlee.snh.api.converter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import de.bornholdtlee.snh.api.dto.SearchNumberDTO;
import de.bornholdtlee.snh.helper.Logger;
import de.bornholdtlee.snh.model.NumberSearchResult;

public class SearchNumberConverter implements JsonDeserializer<SearchNumberDTO> {

    public static final String KEY_NUMBER = "number";

    @Override
    public SearchNumberDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        SearchNumberDTO search = new SearchNumberDTO();
        NumberSearchResult result;
        try {
            JSONObject jsonO = new JSONObject(json.getAsJsonObject().toString());
            jsonO = jsonO.getJSONObject(KEY_NUMBER);
            JSONArray jsonA = jsonO.getJSONArray("suggestions");
            for (int i = 0; i < jsonA.length(); i++) {
                jsonO = jsonA.getJSONObject(i);
                result = new NumberSearchResult();
                String number = jsonO.getString(KEY_NUMBER);
                String plz = jsonO.getString("plz");

                result.add(number);
                result.add(plz);
                search.add(result);
            }
        } catch (JSONException e) {
            Logger.error(e.getMessage());
        }
        return search;
    }

}
