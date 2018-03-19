package de.bitb.astroskop.ui.main.profiles.details

import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.IBaseView

internal interface IProfileDetailView : IBaseView {

    fun setToolbarTitle(title: String)

    fun initProfile(profile: Profile)

    fun refreshView()
}
