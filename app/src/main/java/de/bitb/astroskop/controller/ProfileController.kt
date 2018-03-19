package de.bitb.astroskop.controller

import java.util.UUID

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.model.Profile_
import io.objectbox.Box

class ProfileController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    var profileBox: Box<Profile>? = null

    val allProfiles: List<Profile>
        get() = profileBox!!.all

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    //DATABASE

    fun createProfile(name: String) {
        val profile = Profile()
        profile.uuid = UUID.randomUUID().toString()
        profile.name = name
        //        for (int i = 1; i < 10; i++) {
        //            Constellation constellation = new Constellation();
        //            constellation.setZodiac(Zodiac.getRandom());
        //            constellation.setHouse(House.get(i));
        //            profile.getConstellations().add(constellation);
        //        }
        //        for (int i = 0; i < 10; i++) {
        //            Constellation constellation = new Constellation();
        //            constellation.setZodiac(Zodiac.getRandom());
        //            constellation.setRuler(Ruler.getRandom());
        //            profile.getConstellations().add(constellation);
        //        }
        upsert(profile)
    }

    fun upsert(profile: Profile) {
        profileBox!!.put(profile)
    }

    fun delete(Profile: Profile) {
        profileBox!!.remove(Profile)
    }

    fun getByUUID(uuid: String): Profile? {
        return profileBox!!.query().equal(Profile_.uuid, uuid).build().findFirst()
    }

}
