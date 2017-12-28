package de.bitb.astroskop.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.Circumstance_;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import io.objectbox.Box;

public class CircumstanceController {

    @Inject
    protected Box<Circumstance> circumstanceBox;

    public CircumstanceController(AstroApplication astroApplication) {
        astroApplication.getAppComponent().inject(this);
    }

    //DATABASE

    public void createRandomCircumstance() {
        Circumstance circumstance = new Circumstance();
        circumstance.setUuid(UUID.randomUUID().toString());
        circumstance.setCreationTimestamp(System.currentTimeMillis());
        circumstance.setZodiac(Zodiac.getRandom());
        circumstance.setHouse(House.getRandom());
        circumstance.setRuler(Ruler.getRandom());
        circumstanceBox.put(circumstance);
    }

    public Circumstance getByUUID(String uuid){
        return circumstanceBox.query().equal(Circumstance_.uuid, uuid).build().findFirst();
    }

    public List<Circumstance> getAllCircumstances() {
        return circumstanceBox.query().order(Circumstance_.creationTimestamp).build().find();
    }

    public void delete(Circumstance circumstance) {
        circumstanceBox.remove(circumstance);
    }
}
