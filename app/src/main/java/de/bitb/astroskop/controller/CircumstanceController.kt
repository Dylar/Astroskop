package de.bitb.astroskop.controller

import java.util.UUID

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.model.Circumstance_
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import io.objectbox.Box

class CircumstanceController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    var circumstanceBox: Box<Circumstance>? = null

    val allCircumstances: List<Circumstance>
        get() = circumstanceBox!!.query().order(Circumstance_.creationTimestamp).build().find()

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }
    //DATABASE

    fun createRandomCircumstance() {
        val circumstance = Circumstance()
        circumstance.uuid = UUID.randomUUID().toString()
        circumstance.creationTimestamp = System.currentTimeMillis()
        circumstance.zodiac = Zodiac.random
        circumstance.house = House.random
        circumstance.ruler = Ruler.random
        circumstanceBox!!.put(circumstance)
    }

    fun getByUUID(uuid: String): Circumstance? {
        return circumstanceBox!!.query().equal(Circumstance_.uuid, uuid).build().findFirst()
    }

    fun delete(circumstance: Circumstance) {
        circumstanceBox!!.remove(circumstance)
    }

}
