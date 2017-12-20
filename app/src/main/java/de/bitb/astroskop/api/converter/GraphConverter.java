package de.bitb.astroskop.api.converter;


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

import de.bitb.astroskop.helper.Logger;
import de.bitb.astroskop.model.PowerGraph;

public class GraphConverter implements JsonDeserializer<PowerGraph> {

    @Override
    public PowerGraph deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<Integer> stress = new ArrayList<>();
        List<Integer> importe = new ArrayList<>();
        List<Integer> generated = new ArrayList<>();
        PowerGraph consumption = new PowerGraph();
        consumption.setStress(stress);
        consumption.setImporte(importe);
        consumption.setGenerated(generated);
        try {
            JSONObject jsonO = new JSONObject(json.getAsJsonObject().toString());
            JSONArray jsonA = jsonO.getJSONArray("graphset");
            jsonO = jsonA.getJSONObject(0);
            jsonA = jsonO.getJSONArray("series");
            for (int i = 0; i < jsonA.length(); i++) {
                jsonO = jsonA.getJSONObject(i);
                List<Integer> graph;
                String type = jsonO.getString("text");
                if (type.contains("Last")) {
                    graph = stress;
                } else if (type.contains("importe")) {
                    graph = importe;
                } else {
                    graph = generated;
                }

                JSONArray values = jsonO.getJSONArray("values");
                int len = values.length();
                for (int a = 0; a < len; a++) {
                    graph.add(values.getInt(a));
                }
            }
        } catch (JSONException e) {
            Logger.debug(e.getMessage());
        }
        return consumption;
    }

}
