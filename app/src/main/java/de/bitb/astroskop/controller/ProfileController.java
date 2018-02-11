package de.bitb.astroskop.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.model.Profile_;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import io.objectbox.Box;

public class ProfileController extends BaseController implements IInjection{

    @Inject
    protected Box<Profile> profileBox;

    public ProfileController(AstroApplication astroApplication) {
        super(astroApplication);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    //DATABASE

    public void createProfile(String name) {
        Profile profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setName(name);
        for (int i = 1; i < 10; i++) {
            Constellation constellation = new Constellation();
            constellation.setZodiac(Zodiac.getRandom());
            constellation.setHouse(House.get(i));
            profile.getConstellations().add(constellation);
        }
        for (int i = 0; i < 10; i++) {
            Constellation constellation = new Constellation();
            constellation.setZodiac(Zodiac.getRandom());
            constellation.setRuler(Ruler.getRandom());
            profile.getConstellations().add(constellation);
        }

        profileBox.put(profile);
    }

    public Profile getByUUID(String uuid) {
        return profileBox.query().equal(Profile_.uuid, uuid).build().findFirst();
    }

    public List<Profile> getAllProfiles() {
        return profileBox.getAll();
    }

    public void delete(Profile Profile) {
        profileBox.remove(Profile);
    }

}
