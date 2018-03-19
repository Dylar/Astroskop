package de.bitb.astroskop.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter

import butterknife.BindView
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind


abstract class NavigationBaseActivity : BaseActivity(), IBind, AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.activity_navigation)
    var bottomNavigation: AHBottomNavigation? = null

    abstract val bottomMenuLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigationBar()
        checkKeyBoardUp()
    }

    private fun setupNavigationBar() {
        setBottomNavigationVisible(true)

        bottomNavigation!!.accentColor = ContextCompat.getColor(this, R.color.icon_blue)
        bottomNavigation!!.inactiveColor = ContextCompat.getColor(this, R.color.icon_inactive_gray)
        //        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.background_gray));
        bottomNavigation!!.isForceTint = true
        bottomNavigation!!.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        setCurrentTab(0)
        bottomNavigation!!.setOnTabSelectedListener(this)
        val adapter = AHBottomNavigationAdapter(this, bottomMenuLayout)
        adapter.setupWithBottomNavigation(bottomNavigation)
    }

    override fun allowBackPressed(): Boolean {
        val fragment = currentContent
        if (fragment is NavigationBaseFragment && fragment.navigationPosition != 0) {
            bottomNavigation!!.currentItem = 0
            return false
        }
        return true
    }

    fun setBottomNavigationVisible(visible: Boolean) {
        bottomNavigation!!.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setCurrentTab(currentTab: Int) {
        bottomNavigation!!.currentItem = currentTab
    }

    fun checkKeyBoardUp() {
        baseView!!.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            baseView!!.getWindowVisibleDisplayFrame(r)
            val heightDiff = baseView!!.rootView.height - (r.bottom - r.top)
            bottomNavigation!!.visibility = if (heightDiff > VIEW_HEIGHT_DIFFERENT) View.GONE else View.VISIBLE
        }
    }

    companion object {

        val VIEW_HEIGHT_DIFFERENT = 600
    }
}