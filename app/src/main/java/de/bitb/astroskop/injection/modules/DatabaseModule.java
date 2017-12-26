package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.MyObjectBox;
import io.objectbox.Box;
import io.objectbox.BoxStore;

@Module
public class DatabaseModule {

    @Provides
    @ApplicationScope
    public BoxStore provideDatabase(AstroApplication application) {
//        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
//        if (BuildConfig.DEBUG) {
//            new AndroidObjectBrowser(boxStore).constructionsStart(application);
//        }
        return MyObjectBox.builder().androidContext(application).build();
    }

    @Provides
    @ApplicationScope
    public Box<Circumstance> providePowerBox(BoxStore store) {
        return store.boxFor(Circumstance.class);
    }

}
