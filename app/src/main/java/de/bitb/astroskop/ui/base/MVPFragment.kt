package de.bitb.astroskop.ui.base

import android.os.Bundle
import android.view.View

import de.bitb.astroskop.BaseApplication
import lombok.Getter

abstract class MVPFragment<T : IBaseView, P : BasePresenter<T>> : BaseFragment() {

    @Getter
    var presenter: P? = null
        private set

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter(activity!!.application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
