package de.bitb.astroskop.ui.base


import android.view.Menu
import android.view.MenuItem

import de.bitb.astroskop.R
import de.bitb.astroskop.enums.AnimationType

class SimpleBaseActivity : BaseActivity(), IToolbarView {

    override var animationType: AnimationType = AnimationType.SLIDE_BOTTOM_POP_BOTTOM

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.getItem(0).isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }
}
