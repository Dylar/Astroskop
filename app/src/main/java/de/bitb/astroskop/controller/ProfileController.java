package de.bitb.astroskop.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.model.Profile_;
import io.objectbox.Box;

public class ProfileController {

    @Inject
    protected Box<Profile> profileBox;

    public ProfileController(AstroApplication astroApplication) {
        astroApplication.getAppComponent().inject(this);
    }

    //DATABASE

    public void createProfile(String name) {
        Profile profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setName(name);
        profileBox.put(profile);
    }

    public Profile getByUUID(String uuid){
        return profileBox.query().equal(Profile_.uuid, uuid).build().findFirst();
    }

    public List<Profile> getAllProfiles() {
        return profileBox.getAll();
    }

    public void delete(Profile Profile) {
        profileBox.remove(Profile);
    }

}
