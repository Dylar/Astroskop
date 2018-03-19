package de.bitb.astroskop.ui.main.profiles.details

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.controller.ProfileController
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Constellation
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.BasePresenter

class ProfileDetailPresenter(application: BaseApplication, view: IProfileDetailView) : BasePresenter<IProfileDetailView>(application, view), IInjection {

    @Inject
    var profileController: ProfileController? = null

    private var profile: Profile? = null

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun onCreate(uuid: String) {
        profile = profileController!!.getByUUID(uuid)
        view.setToolbarTitle(profile!!.name)
        view.initProfile(profile)
        refreshView()
    }

    private fun refreshView() {
        view.refreshView()
    }

    fun addConstellation(constellation: Constellation) {
        profile!!.constellations.add(constellation)
        profileController!!.upsert(profile)
        view.initProfile(profile)
    }
}
