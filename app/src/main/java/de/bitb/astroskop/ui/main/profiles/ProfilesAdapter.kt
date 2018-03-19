package de.bitb.astroskop.ui.main.profiles

import android.content.Context
import android.view.View

import de.bitb.astroskop.R
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter

class ProfilesAdapter(context: Context, presenter: AdapterPresenter<Profile>) : SingleRecyclerAdapter<Profile, ProfilesViewHolder>(context, presenter) {

    protected override val layoutId: Int
        get() = R.layout.adapter_profile_item

    override fun newHolder(presenter: AdapterPresenter<Profile>, view: View): ProfilesViewHolder {
        return ProfilesViewHolder(presenter, view)
    }

}
