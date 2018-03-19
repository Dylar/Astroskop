package de.bitb.astroskop.ui.main.home

import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.ui.base.IBaseView
import de.bitb.astroskop.ui.base.NavigationBaseFragment
import de.bitb.astroskop.ui.main.MainActivity

class HomeFragment : NavigationBaseFragment(), IBind, IBaseView {
    override val navigationPosition: Int
        get() = MainActivity.TAB_HOME

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onResume() {
        super.onResume()
        setToolbarTitle(getString(R.string.app_name))
    }

    companion object {

        fun createInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}