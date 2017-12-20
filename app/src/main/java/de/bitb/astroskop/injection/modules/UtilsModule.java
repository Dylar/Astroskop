package de.bitb.astroskop.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.utils.CommonUtils;
import de.bitb.astroskop.utils.DateUtils;
import de.bitb.astroskop.utils.NetworkUtils;
import de.bitb.astroskop.utils.SharedPreferencesUtils;
import de.bitb.astroskop.utils.UiUtils;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.viewbuilder.DialogBuilder;
import de.bitb.astroskop.viewbuilder.ToastBuilder;

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
