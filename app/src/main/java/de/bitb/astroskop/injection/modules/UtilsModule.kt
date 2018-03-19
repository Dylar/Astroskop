package de.bitb.astroskop.injection.modules

import dagger.Module
import dagger.Provides
import de.bitb.astroskop.utils.CommonUtils
import de.bitb.astroskop.utils.DateUtils
import de.bitb.astroskop.utils.NetworkUtils
import de.bitb.astroskop.utils.SharedPreferencesUtils
import de.bitb.astroskop.utils.UiUtils
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.viewbuilder.DialogBuilder
import de.bitb.astroskop.viewbuilder.ToastBuilder
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    internal fun provideNetworkUtils(context: BaseApplication): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferencesUtils(context: BaseApplication): SharedPreferencesUtils {
        return SharedPreferencesUtils(context)
    }

    @Provides
    @Singleton
    internal fun provideToastUtils(): ToastBuilder {
        return ToastBuilder()
    }

    @Provides
    @Singleton
    internal fun provideUiUtils(): UiUtils {
        return UiUtils()
    }

    @Provides
    @Singleton
    internal fun provideDialogBuilder(): DialogBuilder {
        return DialogBuilder()
    }

    @Provides
    @Singleton
    internal fun provideCommonUtils(): CommonUtils {
        return CommonUtils()
    }

    @Provides
    @Singleton
    internal fun provideDateUtils(): DateUtils {
        return DateUtils()
    }

}
