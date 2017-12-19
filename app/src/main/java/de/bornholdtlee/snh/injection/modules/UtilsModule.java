package de.bornholdtlee.snh.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.injection.ApplicationScope;
import de.bornholdtlee.snh.utils.CommonUtils;
import de.bornholdtlee.snh.utils.DateUtils;
import de.bornholdtlee.snh.utils.NetworkUtils;
import de.bornholdtlee.snh.utils.SharedPreferencesUtils;
import de.bornholdtlee.snh.utils.UiUtils;
import de.bornholdtlee.snh.viewbuilder.DialogBuilder;
import de.bornholdtlee.snh.viewbuilder.ToastBuilder;

@Module
public class UtilsModule {

    @Provides
    @ApplicationScope
    NetworkUtils provideNetworkUtils(SNHApplication context) {
        return new NetworkUtils(context);
    }

    @Provides
    @ApplicationScope
    SharedPreferencesUtils provideSharedPreferencesUtils(SNHApplication context) {
        return new SharedPreferencesUtils(context);
    }

    @Provides
    @ApplicationScope
    ToastBuilder provideToastUtils() {
        return new ToastBuilder();
    }

    @Provides
    @ApplicationScope
    UiUtils provideUiUtils() {
        return new UiUtils();
    }

    @Provides
    @ApplicationScope
    DialogBuilder provideDialogBuilder() {
        return new DialogBuilder();
    }

    @Provides
    @ApplicationScope
    CommonUtils provideCommonUtils() {
        return new CommonUtils();
    }

    @Provides
    @ApplicationScope
    DateUtils provideDateUtils() {
        return new DateUtils();
    }

}
