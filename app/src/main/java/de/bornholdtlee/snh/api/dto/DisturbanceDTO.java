package de.bornholdtlee.snh.api.dto;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class DisturbanceDTO {

    @SerializedName("STOERUNGSNACHRICHT_ID")
    protected int serverId;

    @SerializedName("DATUM_ANFANG")
    protected String date;

    @SerializedName("ZEIT_ANFANG")
    protected String start;

    @SerializedName("ZEIT_ENDE")
    protected String end;

    @SerializedName("ORTSTEILE")
    protected JsonElement district;
}
