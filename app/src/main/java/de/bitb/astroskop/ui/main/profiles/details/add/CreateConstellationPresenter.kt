package de.bitb.astroskop.ui.main.profiles.details.add

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Constellation
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import de.bitb.astroskop.ui.base.BasePresenter

class CreateConstellationPresenter(application: BaseApplication, view: ICreateConstellationView) : BasePresenter<ICreateConstellationView>(application, view), IInjection {

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    fun onSaveClicked(zodiac: Zodiac, house: House, ruler: Ruler) {
        val constellation = Constellation.create(zodiac, house, ruler)
        getView().finishConstellation(constellation)
    }

}
