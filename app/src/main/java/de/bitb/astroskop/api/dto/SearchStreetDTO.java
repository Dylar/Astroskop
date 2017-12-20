package de.bitb.astroskop.api.dto;

import java.util.List;

import de.bitb.astroskop.model.StreetSearchResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStreetDTO {

    protected List<StreetSearchResult> results;

}
