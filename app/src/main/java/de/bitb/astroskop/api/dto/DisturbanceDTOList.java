package de.bitb.astroskop.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.bitb.astroskop.model.Disturbance;
import lombok.Getter;

@Getter
public class DisturbanceDTOList {

    @SerializedName("DETAILS")
    protected List<Disturbance> disturbances;

}
