package de.bitb.astroskop.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import javax.inject.Inject

import butterknife.ButterKnife
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.enums.AnimationType
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.ui.main.home.HomeFragment
import de.bitb.astroskop.utils.CommonUtils
import de.bitb.astroskop.utils.UiUtils
import de.bitb.astroskop.viewbuilder.ToastBuilder
import lombok.Getter

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    @Getter
    var uiUtils: UiUtils? = null

    @Inject
    @Getter
    var commonUtils: CommonUtils? = null
    @Inject
    @Getter
    var toastUtils: ToastBuilder? = null

    @Getter
    var baseView: View? = null
        private set

    var actionbarHandler: ActionbarHandler? = null

    //    @Override
    //    protected void attachBaseContext(Context newBase) {
    //        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    //    }
    open fun getContext(): Context {
        return this
    }

    open var layoutId: Int = R.layout.activity_container

    open var contentContainerId: Int = R.id.activity_container

    val appComponent: AppComponent?
        get() = (application as BaseApplication).appComponent

    val arguments: Bundle?
        get() = intent.extras

    open val actionbarCallback: ActionbarHandler.ActionbarCallback = ActionbarHandler.ActionbarCallback()

    val currentContent: BaseFragment?
        get() = supportFragmentManager.findFragmentById(contentContainerId) as BaseFragment

    open var animationType: AnimationType = AnimationType.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.actionbarHandler = ActionbarHandler(actionbarCallback)

        if (this is IInjection) {
            (this as IInjection).inject(appComponent)
        } else {
            appComponent!!.inject(this)
        }

        val inflater = LayoutInflater.from(this)
        val activityView = inflater.inflate(layoutId, null)

        baseView = inflater.inflate(R.layout.activity_base, null)

        val container = baseView!!.findViewById<ViewGroup>(R.id.activity_base_content)
        container.removeAllViews()
        container.addView(activityView)

        initToolbar(baseView as View)

        setContentView(baseView)

        if (this !is NavigationBaseActivity) {
            baseView!!.findViewById<View>(R.id.activity_navigation).visibility = View.GONE
        }

        if (this is IBind) {
            ButterKnife.bind(this)
        }

    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.activity_base_toolbar)
        if (this is IToolbarView) {
            setSupportActionBar(toolbar)
            val actionBar = supportActionBar
            actionBar!!.setDisplayShowTitleEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        } else {
            toolbar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return actionbarHandler!!.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionbarHandler!!.onOptionsItemSelected(item.itemId) || super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        isAppInForeground = true
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onPause() {
        super.onPause()
        isAppInForeground = false
    }

    override fun onBackPressed() {
        if (currentContent?.allowBackPress == true && allowBackPressed()) {
            if (currentContent is HomeFragment) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
    }

    protected open fun allowBackPressed(): Boolean {
        return true
    }

    fun setToolbarTitle(title: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    fun showFragmentClearTop(fragment: BaseFragment, shouldAddToBackStack: Boolean) {
        showFragmentClearTop(fragment, contentContainerId, shouldAddToBackStack)
    }

    @JvmOverloads
    fun showFragmentClearTop(fragment: BaseFragment, containerViewResId: Int = contentContainerId, shouldAddToBackStack: Boolean = false) {
        val fragmentManager = supportFragmentManager
        val fragmentName = fragment.javaClass.name

        val fragmentPopped = fragmentManager.popBackStackImmediate(fragmentName, 0)

        if (!fragmentPopped) { //fragment not in back stack, create it.
            showFragment(fragment, containerViewResId, shouldAddToBackStack)
        }
    }

    fun showFragment(fragment: BaseFragment, shouldAddToBackStack: Boolean) {
        showFragment(fragment, contentContainerId, shouldAddToBackStack)
    }

    @JvmOverloads
    fun showFragment(fragment: BaseFragment, containerViewResId: Int = contentContainerId, shouldAddToBackStack: Boolean = false) {
        val fragmentManager = supportFragmentManager
        val fragmentName = fragment.javaClass.name
        var fragmentByTag: BaseFragment? = fragmentManager.findFragmentByTag(fragmentName) as BaseFragment

        fragmentByTag = if (null == fragmentByTag) fragment else fragmentByTag

        if (fragmentByTag.isAdded) {
            return
        }

        val fragmentTransaction = fragmentManager.beginTransaction()

        uiUtils!!.setAnimation(fragmentByTag, fragmentTransaction)

        fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName)

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName)
        }

        fragmentTransaction.commit()
    }

    override fun finish() {
        super.finish()
        commonUtils!!.closeKeyboard(this, baseView!!.windowToken)
        if (AnimationType.NONE != animationType) {
            uiUtils!!.setAnimation(this, false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        isAppInForeground = true
        val frag = currentContent
        frag!!.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        @Getter
        var isAppInForeground: Boolean = false
            private set
    }

}//    protected void showScreenClearBackStack(BaseFragment fragment, boolean shouldAddToBackStack) {
//        showScreenClearBackStack(fragment, getContentContainerId(), shouldAddToBackStack);
//    }
//
//    private void showScreenClearBackStack(BaseFragment fragment, int containerViewResId, boolean shouldAddToBackStack) {
//        clearBackstack();
//        showFragment(fragment, containerViewResId, shouldAddToBackStack);
//    }
//
//    private void clearBackstack() {
//        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//    }
