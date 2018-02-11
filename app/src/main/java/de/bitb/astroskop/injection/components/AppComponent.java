package de.bitb.astroskop.injection.components;

import dagger.Component;
import de.bitb.astroskop.controller.BaseController;
import de.bitb.astroskop.controller.CircumstanceController;
import de.bitb.astroskop.controller.ProfileController;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.injection.modules.DatabaseModule;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.modules.ApplicationModule;
import de.bitb.astroskop.injection.modules.ControllerModule;
import de.bitb.astroskop.injection.modules.NetworkModule;
import de.bitb.astroskop.injection.modules.UtilsModule;
import de.bitb.astroskop.ui.IntroActivity;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.circumstances.CircumstancesPresenter;
import de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsPresenter;
import de.bitb.astroskop.ui.main.profiles.ProfilesPresenter;
import de.bitb.astroskop.ui.main.profiles.details.ProfileDetailPresenter;
import de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationPresenter;

@ApplicationScope
@Component(modules = {ApplicationModule.class, ControllerModule.class, UtilsModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(AstroApplication application);

    void inject(BaseController baseController);
    void inject(CircumstanceController circumstanceController);
    void inject(ProfileController profileController);

    void inject(BaseActivity baseActivity);
    void inject(IntroActivity introActivity);
    void inject(MainActivity mainActivity);

    void inject(BaseFragment baseFragment);

    void inject(CircumstancesPresenter circumstancesPresenter);
    void inject(CircumstanceDetailsPresenter circumstanceDetailsPresenter);
    void inject(ProfilesPresenter profilesOverviewPresenter);
    void inject(ProfileDetailPresenter profileDetailPresenter);

    void inject(CreateConstellationPresenter createConstellationPresenter);

}
