package de.bitb.astroskop.ui.main.circumstances.details

import android.content.Context
import android.content.Intent
import android.os.Bundle

import de.bitb.astroskop.ui.base.BaseActivity

class CircumstanceDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(CircumstanceDetailsFragment.createInstance(intent.getStringExtra(KEY_CIRCUMSTANCE_UUID)), false)
    }

    companion object {

        val KEY_CIRCUMSTANCE_UUID = "key_circumstance_uuid"

        fun startActivity(context: Context, uuid: String) {
            val intent = Intent(context, CircumstanceDetailsActivity::class.java)
            intent.putExtra(KEY_CIRCUMSTANCE_UUID, uuid)
            context.startActivity(intent)
        }
    }
}
