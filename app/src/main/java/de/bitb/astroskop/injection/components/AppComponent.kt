package de.bitb.astroskop.injection.components

import dagger.Component
import de.bitb.astroskop.controller.BaseController
import de.bitb.astroskop.controller.CircumstanceController
import de.bitb.astroskop.controller.ProfileController
import de.bitb.astroskop.injection.modules.DatabaseModule
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.injection.modules.ApplicationModule
import de.bitb.astroskop.injection.modules.ControllerModule
import de.bitb.astroskop.injection.modules.NetworkModule
import de.bitb.astroskop.injection.modules.UtilsModule
import de.bitb.astroskop.ui.IntroActivity
import de.bitb.astroskop.ui.base.BaseActivity
import de.bitb.astroskop.ui.base.BaseFragment
import de.bitb.astroskop.ui.main.MainActivity
import de.bitb.astroskop.ui.main.circumstances.CircumstancesPresenter
import de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsPresenter
import de.bitb.astroskop.ui.main.profiles.ProfilesPresenter
import de.bitb.astroskop.ui.main.profiles.details.ProfileDetailPresenter
import de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationPresenter

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, ControllerModule::class, UtilsModule::class, NetworkModule::class, DatabaseModule::class))
interface AppComponent {

    fun inject(application: BaseApplication)

    fun inject(baseController: BaseController)
    fun inject(circumstanceController: CircumstanceController)
    fun inject(profileController: ProfileController)

    fun inject(baseActivity: BaseActivity)
    fun inject(introActivity: IntroActivity)
    fun inject(mainActivity: MainActivity)

    fun inject(baseFragment: BaseFragment)

    fun inject(circumstancesPresenter: CircumstancesPresenter)
    fun inject(circumstanceDetailsPresenter: CircumstanceDetailsPresenter)
    fun inject(profilesOverviewPresenter: ProfilesPresenter)
    fun inject(profileDetailPresenter: ProfileDetailPresenter)

    fun inject(createConstellationPresenter: CreateConstellationPresenter)

}
