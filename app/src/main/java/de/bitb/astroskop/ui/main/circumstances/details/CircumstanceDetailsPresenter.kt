package de.bitb.astroskop.ui.main.circumstances.details

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.controller.CircumstanceController
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.ui.base.BasePresenter

class CircumstanceDetailsPresenter(application: BaseApplication, view: ICircumstancesDetailView) : BasePresenter<ICircumstancesDetailView>(application, view), IInjection {

    @Inject
    var circumstanceController: CircumstanceController? = null

    private var circumstance: Circumstance? = null

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    fun onCreate(circumstanceUuid: String) {
        circumstance = circumstanceController!!.getByUUID(circumstanceUuid)
        getView().initZodiacInfo(circumstance!!.zodiac!!)
        getView().initHouseInfo(circumstance!!.house!!)
        getView().initRulerInfo(circumstance!!.ruler!!)
    }

}
