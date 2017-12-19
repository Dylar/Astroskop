package de.bornholdtlee.snh.api.converter;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.bornholdtlee.snh.api.dto.SearchStreetDTO;
import de.bornholdtlee.snh.helper.Logger;
import de.bornholdtlee.snh.model.StreetSearchResult;

public class SearchStreetConverter implements JsonDeserializer<SearchStreetDTO> {

    public static final String KEY_STREET = "street";

    @Override
    public SearchStreetDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SearchStreetDTO search = new SearchStreetDTO();
        List<StreetSearchResult> results = new ArrayList<>();
        search.setResults(results);
        StreetSearchResult result;
        try {
            JSONObject jsonO = new JSONObject(json.getAsJsonObject().toString());
            jsonO = jsonO.getJSONObject(KEY_STREET);
            JSONArray jsonA = jsonO.getJSONArray("suggestions");
            for (int i = 0; i < jsonA.length(); i++) {
                jsonO = jsonA.getJSONObject(i);
                result = gson.fromJson(jsonO.toString(), StreetSearchResult.class);
                results.add(result);
            }
        } catch (JSONException e) {
            Logger.debug(e.getMessage());
        }
        return search;
    }

}
