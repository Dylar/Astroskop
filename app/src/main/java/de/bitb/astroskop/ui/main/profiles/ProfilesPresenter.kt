package de.bitb.astroskop.ui.main.profiles

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.controller.ProfileController
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.BasePresenter
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.IAdapterView

class ProfilesPresenter(application: BaseApplication, view: IProfilesView) : BasePresenter<IProfilesView>(application, view), IInjection, AdapterPresenter<Profile> {

    @Inject
    var profileController: ProfileController? = null

    var profiles: List<Profile>? = null
        private set

    override val adapterItemCount: Int
        get() = profiles!!.size

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun onCreate() {
        refreshView()
    }

    private fun refreshView() {
        profiles = profileController!!.allProfiles
        view.refreshView()
    }

    override fun onBindAtPosition(holder: IAdapterView<Profile>, position: Int) {
        holder.bindValues(profiles!![position])
    }

    override fun onItemClicked(model: Profile) {
        view.openProfile(model)
    }

    override fun onItemLongClicked(model: Profile) {
        view.openDeleteDialog(model)
    }

    fun createProfile(name: String) {
        profileController!!.createProfile(name)
        refreshView()
    }

    fun deleteProfile(profile: Profile) {
        profileController!!.delete(profile)
        refreshView()
    }
}
