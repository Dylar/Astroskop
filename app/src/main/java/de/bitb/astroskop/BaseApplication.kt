package de.bitb.astroskop

import android.app.Application

import com.facebook.stetho.Stetho

import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.injection.components.DaggerAppComponent
import de.bitb.astroskop.injection.modules.ApplicationModule
import lombok.Getter

class BaseApplication : Application() {

    //    public static final String FONT_PATH = "fonts/NeoTechStd-Regular.otf";

    @Getter
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        initComponent()
        initStetho()
        //        initCalligraphy();
        appComponent!!.inject(this)
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    //    private void initCalligraphy() {
    //        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
    //                .setDefaultFontPath(FONT_PATH)
    //                .setFontAttrId(R.attr.fontPath)
    //                .build()
    //        );
    //    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

}
