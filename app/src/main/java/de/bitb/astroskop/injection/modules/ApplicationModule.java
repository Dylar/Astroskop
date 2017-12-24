package de.bitb.astroskop.injection.modules;


import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.ApplicationScope;

@Module
public class ApplicationModule {

    private final AstroApplication application;

    public ApplicationModule(AstroApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    AstroApplication provideApplication() {
        return application;
    }

}
