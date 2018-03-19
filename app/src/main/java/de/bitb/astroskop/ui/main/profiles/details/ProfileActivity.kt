package de.bitb.astroskop.ui.main.profiles.details

import android.content.Context
import android.content.Intent
import android.os.Bundle

import de.bitb.astroskop.ui.base.ActionbarHandler
import de.bitb.astroskop.ui.base.BaseActivity
import de.bitb.astroskop.ui.base.IToolbarView

class ProfileActivity : BaseActivity(), IToolbarView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(ProfileDetailFragment.createInstance(intent.getStringExtra(KEY_PROFILE_UUID)), false)
    }

    companion object {

        val KEY_PROFILE_UUID = "key_profile_uuid"

        fun startActivity(context: Context, uuid: String) {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(KEY_PROFILE_UUID, uuid)
            context.startActivity(intent)
        }
    }

}
