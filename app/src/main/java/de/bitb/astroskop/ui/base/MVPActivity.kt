package de.bitb.astroskop.ui.base

import android.os.Bundle

import de.bitb.astroskop.BaseApplication
import lombok.Getter

abstract class MVPActivity<T : IBaseView, P : BasePresenter<T>> : BaseActivity() {

    @Getter
    var presenter: P? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter(application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
