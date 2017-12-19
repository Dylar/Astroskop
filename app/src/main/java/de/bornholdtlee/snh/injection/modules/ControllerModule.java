package de.bornholdtlee.snh.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.controller.AccountController;
import de.bornholdtlee.snh.controller.ConstructionsController;
import de.bornholdtlee.snh.controller.PowerController;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.injection.ApplicationScope;
import de.bornholdtlee.snh.utils.SharedPreferencesUtils;

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
