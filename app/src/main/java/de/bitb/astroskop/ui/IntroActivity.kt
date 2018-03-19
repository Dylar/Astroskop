package de.bitb.astroskop.ui


import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.view.WindowManager

import javax.inject.Inject

import butterknife.BindView
import butterknife.OnClick
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.ui.base.BaseActivity
import de.bitb.astroskop.ui.main.MainActivity
import de.bitb.astroskop.utils.SharedPreferencesUtils
import de.bitb.astroskop.viewbuilder.DialogBuilder

class IntroActivity : BaseActivity(), IInjection, IBind {

    override val layoutId: Int
        get() = R.layout.activity_intro

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(context, R.color.background_gray)

        val updateStarted = System.currentTimeMillis()
        startRemainingTimer(updateStarted)
    }

    private fun startRemainingTimer(updateStarted: Long) {
        val usedTime = System.currentTimeMillis() - updateStarted
        val remainingDelay = (SPLASHSCREEN_MIN_DELAY - usedTime / 1000).toInt()
        Handler().postDelayed({ navigateToMainScreen() }, (if (remainingDelay < 0) 1 else remainingDelay).toLong())
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    private fun navigateToMainScreen() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {

        private val SPLASHSCREEN_MIN_DELAY = 1000
    }

}
