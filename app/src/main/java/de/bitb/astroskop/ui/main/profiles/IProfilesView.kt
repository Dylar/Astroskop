package de.bitb.astroskop.ui.main.profiles

import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.IBaseView

internal interface IProfilesView : IBaseView {
    fun openProfile(profile: Profile)

    fun openDeleteDialog(profile: Profile)

    fun refreshView()
}
