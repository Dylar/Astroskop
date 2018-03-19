package de.bitb.astroskop.injection.modules

import dagger.Module
import dagger.Provides
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.model.MyObjectBox
import de.bitb.astroskop.model.Profile
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: BaseApplication): BoxStore {
        //        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
        //        if (BuildConfig.DEBUG) {
        //            new AndroidObjectBrowser(boxStore).constructionsStart(application);
        //        }
        return MyObjectBox.builder().androidContext(application).build()
    }

    @Provides
    @Singleton
    fun providePowerBox(store: BoxStore): Box<Circumstance> {
        return store.boxFor(Circumstance::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileBox(store: BoxStore): Box<Profile> {
        return store.boxFor(Profile::class.java)
    }

}
