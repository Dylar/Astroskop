package de.bornholdtlee.snh.api.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class ConstructionDTO {

    @SerializedName("id")
    protected int serverId;

    @SerializedName("startDate")
    protected String start;

    @SerializedName("endDate")
    protected String end;

    protected String street;

    @SerializedName("number")
    protected String streetNumber;

    protected String district;

    @SerializedName("length")
    protected String size;

}
