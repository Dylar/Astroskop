package de.bitb.astroskop.injection.components;

import dagger.Component;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.injection.modules.DatabaseModule;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.modules.ApplicationModule;
import de.bitb.astroskop.injection.modules.ControllerModule;
import de.bitb.astroskop.injection.modules.NetworkModule;
import de.bitb.astroskop.injection.modules.UtilsModule;
import de.bitb.astroskop.ui.IntroActivity;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.home.HomeViewPresenter;

@ApplicationScope
@Component(modules = {ApplicationModule.class, ControllerModule.class, UtilsModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(AstroApplication application);

    void inject(BaseActivity baseActivity);
    void inject(IntroActivity introActivity);
    void inject(MainActivity mainActivity);

    void inject(BaseFragment baseFragment);

    void inject(HomeViewPresenter homeViewPresenter);

}
