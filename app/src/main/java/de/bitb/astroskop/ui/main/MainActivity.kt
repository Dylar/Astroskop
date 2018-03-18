package de.bitb.astroskop.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle

import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.ui.base.IToolbarView
import de.bitb.astroskop.ui.base.NavigationBaseActivity
import de.bitb.astroskop.ui.main.circumstances.CircumstanceFragment
import de.bitb.astroskop.ui.main.home.HomeFragment
import de.bitb.astroskop.ui.main.profiles.ProfilesFragment
import de.bitb.astroskop.utils.HockeyAppUtils

class MainActivity : NavigationBaseActivity(), IBind, IToolbarView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToHomeScreen()
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        if (!wasSelected) {
            when (position) {
                TAB_HOME -> {
                    navigateToHomeScreen()
                    return true
                }
                TAB_CIRCUMSTANCES -> {
                    navigateToCircumstanceScreen()
                    return true
                }
                TAB_PROFILES -> {
                    navigateToProfilesScreen()
                    return true
                }
                TAB_SETTINGS -> return true
            }
        }
        return false
    }

    private fun navigateToHomeScreen() {
        val fragment = HomeFragment.createInstance()
        showFragmentClearTop(fragment)
    }

    private fun navigateToCircumstanceScreen() {
        val fragment = CircumstanceFragment.createInstance()
        showFragment(fragment)
    }

    private fun navigateToProfilesScreen() {
        val fragment = ProfilesFragment.createInstance()
        showFragment(fragment)
    }

    public override fun onResume() {
        super.onResume()

        HockeyAppUtils.checkForCrashes(this)
    }

    override fun getBottomMenuLayout(): Int {
        return R.menu.navigation_menu
    }

    companion object {

        val TAB_HOME = 0
        val TAB_CIRCUMSTANCES = 1
        val TAB_PROFILES = 2
        val TAB_SETTINGS = 3

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}