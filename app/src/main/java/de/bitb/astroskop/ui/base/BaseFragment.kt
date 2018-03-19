package de.bitb.astroskop.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import butterknife.ButterKnife
import de.bitb.astroskop.enums.AnimationType
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.viewbuilder.DialogBuilder
import de.bitb.astroskop.viewbuilder.ToastBuilder


abstract class BaseFragment : Fragment(), IBaseView {

    @Inject
    protected var toastUtils: ToastBuilder? = null

    @Inject
    protected var dialogBuilder: DialogBuilder? = null

    private var toolbarView: IToolbarView? = null
    private var actionbarHandler: ActionbarHandler? = null

    abstract val layoutId: Int

    protected val allowBackpress: Boolean
        get() = false

    protected val actionbarCallback: ActionbarHandler.ActionbarCallback
        get() = ActionbarHandler.ActionbarCallback()

    protected val animationType: AnimationType
        get() = AnimationType.NONE

    override fun getContext(): Context {
        return context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.actionbarHandler = ActionbarHandler(actionbarCallback)
        if (this is IInjection) {
            (this as IInjection).inject((activity as BaseActivity).appComponent)
        } else {
            (activity as BaseActivity).appComponent!!.inject(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(layoutId, null, false)
        if (this is IBind) {
            ButterKnife.bind(this, root)
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        actionbarHandler!!.onPrepareOptionsMenu(menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return actionbarHandler!!.onOptionsItemSelected(item!!.itemId) || super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IToolbarView) {
            toolbarView = context
        }
    }

    protected fun runOnUiThread(runnable: Runnable) {
        activity?.runOnUiThread(runnable)
    }

    fun setToolbarTitle(title: String) {
        toolbarView?.setToolbarTitle(title)
    }

}
