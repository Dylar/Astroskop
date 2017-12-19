package de.bornholdtlee.snh.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.bornholdtlee.snh.model.Disturbance;
import lombok.Getter;

@Getter
public class DisturbanceDTOList {

    @SerializedName("DETAILS")
    protected List<Disturbance> disturbances;

}
