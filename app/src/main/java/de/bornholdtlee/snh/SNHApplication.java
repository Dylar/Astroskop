package de.bornholdtlee.snh;

import android.app.Application;

import com.etracker.tracking.Tracker;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.injection.components.DaggerAppComponent;
import de.bornholdtlee.snh.injection.modules.ApplicationModule;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SNHApplication extends Application {

    public static final String FONT_PATH = "fonts/NeoTechStd-Regular.otf";

    @Getter
    @Inject
    protected Tracker tracker;

    @Getter
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        initStetho();
        initCalligraphy();
        appComponent.inject(this);
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    @Override
    public void onTerminate() {
        tracker.flush();
        tracker.stopTracker();
        super.onTerminate();
    }

}
