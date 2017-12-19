package de.bornholdtlee.snh.api.converter;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import de.bornholdtlee.snh.api.dto.DisturbanceDTO;
import de.bornholdtlee.snh.model.Disturbance;

public class DisturbanceConverter implements JsonDeserializer<Disturbance> {
    @Override
    public Disturbance deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DisturbanceDTO disturbanceDTO = context.deserialize(json, DisturbanceDTO.class);

        Disturbance disturbance = new Disturbance();
        disturbance.setDate(disturbanceDTO.getDate());
        disturbance.setEnd(disturbanceDTO.getEnd());
        disturbance.setStart(disturbanceDTO.getStart());
        disturbance.setServerId(disturbanceDTO.getServerId());

        JsonArray array = disturbanceDTO.getDistrict().getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            disturbance.getDistrict().add(array.get(i).getAsString());
        }
        return disturbance;
    }

}
