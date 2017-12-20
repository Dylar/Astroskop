package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.model.Account;
import de.bitb.astroskop.model.Construction;
import de.bitb.astroskop.model.DistrictPower;
import de.bitb.astroskop.model.Disturbance;
import de.bitb.astroskop.model.PowerGraph;
import de.bornholdtlee.snh.model.MyObjectBox;
import de.bitb.astroskop.model.StreetSearchResult;
import io.objectbox.Box;
import io.objectbox.BoxStore;

@Module
public class DatabaseModule {

    @Provides
    @ApplicationScope
    public BoxStore provideDatabase(SNHApplication application) {
//        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
//        if (BuildConfig.DEBUG) {
//            new AndroidObjectBrowser(boxStore).constructionsStart(application);
//        }
        return MyObjectBox.builder().androidContext(application).build();
    }

    @Provides
    @ApplicationScope
    public Box<Account> provideAccountBox(BoxStore store) {
        return store.boxFor(Account.class);
    }

    @Provides
    @ApplicationScope
    public Box<Construction> provideConstructionBox(BoxStore store) {
        return store.boxFor(Construction.class);
    }

    @Provides
    @ApplicationScope
    public Box<Disturbance> provideDisturbanceBox(BoxStore store) {
        return store.boxFor(Disturbance.class);
    }

    @Provides
    @ApplicationScope
    public Box<DistrictPower> providePowerBox(BoxStore store) {
        return store.boxFor(DistrictPower.class);
    }

    @Provides
    @ApplicationScope
    public Box<PowerGraph> provideGraphBox(BoxStore store) {
        return store.boxFor(PowerGraph.class);
    }

    @Provides
    @ApplicationScope
    public Box<StreetSearchResult> provideStreetsSearchResultsBox(BoxStore store) {
        return store.boxFor(StreetSearchResult.class);
    }

}
