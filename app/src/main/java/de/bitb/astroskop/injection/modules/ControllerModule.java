package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.ZodiacController;
import de.bitb.astroskop.injection.ApplicationScope;

@Module
public class ControllerModule {

    @Provides
    @ApplicationScope
    public ZodiacController provideAccountController(AstroApplication astroApplication) {
        return new ZodiacController(astroApplication);
    }

}
