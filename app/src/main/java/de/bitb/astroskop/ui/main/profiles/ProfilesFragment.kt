package de.bitb.astroskop.ui.main.profiles

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText

import butterknife.BindView
import butterknife.OnClick
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.NavigationBaseFragment
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter
import de.bitb.astroskop.ui.main.MainActivity
import de.bitb.astroskop.ui.main.profiles.details.ProfileActivity

class ProfilesFragment : NavigationBaseFragment(), IBind, IProfilesView {

    @BindView(R.id.fragment_profile_list)
    var recyclerView: RecyclerView? = null

    @BindView(R.id.fragment_profile_edittext_name)
    var nameET: EditText? = null

    private var profileAdapter: SingleRecyclerAdapter<*, *>? = null

    private var presenter: ProfilesPresenter? = null

    override val layoutId: Int
        get() = R.layout.fragment_profiles_overview

    override val navigationPosition: Int
        get() = MainActivity.TAB_PROFILES

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter = ProfilesPresenter(activity!!.application as BaseApplication, this)
        initRecyclerView()
        presenter!!.onCreate()
    }

    private fun initRecyclerView() {
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        profileAdapter = ProfilesAdapter(context, presenter)
        recyclerView!!.adapter = profileAdapter
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Profiles")
    }

    @OnClick(R.id.fragment_profile_button_create)
    fun onCreateProfile() {
        presenter!!.createProfile(nameET!!.text.toString())
    }

    override fun refreshView() {
        profileAdapter!!.notifyDataSetChanged()
    }

    override fun openProfile(profile: Profile) {
        ProfileActivity.startActivity(context, profile.uuid)
    }

    override fun openDeleteDialog(profile: Profile) {
        dialogBuilder!!.showDeleteDialog(context) { dialogInterface, i -> presenter!!.deleteProfile(profile) }
    }

    companion object {

        fun createInstance(): ProfilesFragment {
            return ProfilesFragment()
        }
    }

}