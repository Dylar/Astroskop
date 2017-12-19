package de.bornholdtlee.snh.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.injection.ApplicationScope;
import de.bornholdtlee.snh.model.Account;
import de.bornholdtlee.snh.model.Construction;
import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.model.Disturbance;
import de.bornholdtlee.snh.model.PowerGraph;
import de.bornholdtlee.snh.model.MyObjectBox;
import de.bornholdtlee.snh.model.StreetSearchResult;
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
