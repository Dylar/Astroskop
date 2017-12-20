package de.bitb.astroskop.api.converter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import de.bitb.astroskop.api.dto.ConstructionDTO;
import de.bitb.astroskop.model.Construction;

public class ConstructionsConverter implements JsonDeserializer<Construction> {
    @Override
    public Construction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ConstructionDTO constructionDTO = context.deserialize(json, ConstructionDTO.class);
        Construction construction = new Construction();
        construction.setServerId(constructionDTO.getServerId());
        construction.setStart(constructionDTO.getStart());
        construction.setEnd(constructionDTO.getEnd());
        construction.setSize(constructionDTO.getSize());
        construction.setStreetNumber(constructionDTO.getStreetNumber());
        construction.setDistrict(constructionDTO.getDistrict());
        construction.setStreet(constructionDTO.getStreet());
        return construction;
    }

}
