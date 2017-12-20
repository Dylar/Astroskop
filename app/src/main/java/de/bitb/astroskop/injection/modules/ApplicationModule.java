package de.bitb.astroskop.injection.modules;


import com.etracker.tracking.Tracker;
import com.etracker.tracking.UserConsent;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bornholdtlee.snh.BuildConfig;
import de.bitb.astroskop.SNHApplication;

@Module
public class ApplicationModule {

    private final SNHApplication application;

    public ApplicationModule(SNHApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    SNHApplication provideApplication() {
        return application;
    }

    @Provides
    @ApplicationScope
    Tracker provideTracker(SNHApplication application) {
        Tracker tracker = Tracker.getInstance(application);
        String apiKey = "07040c9275";
        String accountKey = BuildConfig.SHOW_VERSION ? "ya3Vsb" : "ya3Ar3";
        tracker.startTracker(accountKey, apiKey);
        tracker.setUserConsent(UserConsent.Granted);
        return tracker;
    }

}
