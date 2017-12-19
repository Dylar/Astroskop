package de.bornholdtlee.snh.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;

@Entity
@Getter
public class StreetSearchResult {

    @Id
    protected long dbId;

    private String street;
    private String city;
}
