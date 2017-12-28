package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.CircumstanceController;
import de.bitb.astroskop.controller.ProfileController;
import de.bitb.astroskop.injection.ApplicationScope;

@Module
public class ControllerModule {

    @Provides
    @ApplicationScope
    public CircumstanceController provideCircumstanceController(AstroApplication astroApplication) {
        return new CircumstanceController(astroApplication);
    }
    @Provides
    @ApplicationScope
    public ProfileController provideProfileController(AstroApplication astroApplication) {
        return new ProfileController(astroApplication);
    }

}
