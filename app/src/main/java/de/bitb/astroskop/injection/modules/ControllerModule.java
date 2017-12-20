package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.controller.AccountController;
import de.bitb.astroskop.controller.ConstructionsController;
import de.bitb.astroskop.controller.DisturbanceController;
import de.bitb.astroskop.controller.PowerController;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.utils.SharedPreferencesUtils;
import de.bitb.astroskop.SNHApplication;

@Module
public class ControllerModule {

    @Provides
    @ApplicationScope
    public AccountController provideAccountController(SharedPreferencesUtils sharedPreferencesUtils) {
        return new AccountController(sharedPreferencesUtils);
    }

    @Provides
    @ApplicationScope
    public ConstructionsController provideConstructionsController(SNHApplication application) {
        return new ConstructionsController(application);
    }

    @Provides
    @ApplicationScope
    public DisturbanceController provideDisturbanceController(SNHApplication application) {
        return new DisturbanceController(application);
    }

    @Provides
    @ApplicationScope
    public PowerController provideConsumptionController(SNHApplication application) {
        return new PowerController(application);
    }

}
