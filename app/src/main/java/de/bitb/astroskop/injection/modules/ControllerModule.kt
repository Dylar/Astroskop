package de.bitb.astroskop.injection.modules

import dagger.Module
import dagger.Provides
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.controller.CircumstanceController
import de.bitb.astroskop.controller.ProfileController
import javax.inject.Singleton

@Module
class ControllerModule {

    @Provides
    @Singleton
    fun provideCircumstanceController(baseApplication: BaseApplication): CircumstanceController {
        return CircumstanceController(baseApplication)
    }

    @Provides
    @Singleton
    fun provideProfileController(baseApplication: BaseApplication): ProfileController {
        return ProfileController(baseApplication)
    }

}
