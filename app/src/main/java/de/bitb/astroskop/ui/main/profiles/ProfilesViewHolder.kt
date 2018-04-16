package de.bitb.astroskop.ui.main.profiles

import android.view.View
import android.widget.TextView

import butterknife.BindView
import de.bitb.astroskop.R
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder

class ProfilesViewHolder(presenter: AdapterPresenter<Profile>, view: View) : BaseViewHolder<Profile>(presenter, view) {

    @BindView(R.id.adapter_profile_name)
    var nameTV: TextView? = null

    override fun bindValues(profile: Profile) {
        nameTV!!.text = profile.name
        itemView.setOnClickListener { _ -> presenter.onItemClicked(profile) }
        itemView.setOnLongClickListener { _ ->
            presenter.onItemLongClicked(profile)
            true
        }
    }
}
