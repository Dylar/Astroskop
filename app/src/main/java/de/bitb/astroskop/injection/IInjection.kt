package de.bitb.astroskop.injection


import de.bitb.astroskop.injection.components.AppComponent

interface IInjection {
    fun inject(appComponent: AppComponent?)
}
