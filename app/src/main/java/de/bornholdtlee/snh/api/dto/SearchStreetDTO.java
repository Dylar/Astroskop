package de.bornholdtlee.snh.api.dto;

import java.util.List;

import de.bornholdtlee.snh.model.StreetSearchResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStreetDTO {

    protected List<StreetSearchResult> results;

}
