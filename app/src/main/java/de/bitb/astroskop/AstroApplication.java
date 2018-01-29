package de.bitb.astroskop;

import android.app.Application;

import com.facebook.stetho.Stetho;

import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.injection.components.DaggerAppComponent;
import de.bitb.astroskop.injection.modules.ApplicationModule;
import lombok.Getter;

public class AstroApplication extends Application {

//    public static final String FONT_PATH = "fonts/NeoTechStd-Regular.otf";

    @Getter
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        initStetho();
//        initCalligraphy();
        appComponent.inject(this);
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

//    private void initCalligraphy() {
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath(FONT_PATH)
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
//    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

}
