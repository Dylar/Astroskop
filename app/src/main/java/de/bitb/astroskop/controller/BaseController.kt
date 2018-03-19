package de.bitb.astroskop.controller

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.utils.SharedPreferencesUtils
import lombok.Getter

@Getter
open class BaseController(baseApplication: BaseApplication) {

    @Inject
    var sharedPreferencesUtils: SharedPreferencesUtils? = null

    init {
        if (this is IInjection) {
            (this as IInjection).inject(baseApplication.appComponent)
        } else {
            baseApplication.appComponent!!.inject(this)
        }
    }
}
