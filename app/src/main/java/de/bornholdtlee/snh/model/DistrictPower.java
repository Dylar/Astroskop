package de.bornholdtlee.snh.model;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class DistrictPower {

    @Id
    protected long dbId;

    private int powerHamburg;
    private int powerEimsbuettel;
    private int powerNord;
    private int powerMitte;
    private int powerAltona;
    private int powerHarburg;
    private int powerWandsbek;
    private int powerBergedorf;
}
