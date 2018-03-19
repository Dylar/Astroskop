package de.bitb.astroskop.ui.base

import android.content.Context

import java.lang.ref.WeakReference

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.IInjection

open class BasePresenter<T : IBaseView>(application: BaseApplication, view: T) {

    private val view: WeakReference<T>

    val context: Context
        get() = getView().context

    init {
        this.view = WeakReference(view)
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }

    fun getView(): T {
        return view.get()
    }

}
