package de.bitb.astroskop.injection.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import dagger.Module;
import dagger.Provides;
import de.bitb.astroskop.injection.ApplicationScope;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://www.stromnetz.hamburg/";
    private static final String BASE_URL_DISTURBANCE = "https://stoerungskarte.stromnetz-hamburg.de/";
    private static final String BASE_URL_CONSUMPTION = "http://www.energieportal-hamburg.de/distribution/energieportal/";

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingInterceptor);
        return client.build();
    }

//    @Provides
//    @ApplicationScope
//    public Api.Constructions provideRetrofitConstructions(OkHttpClient okHttpClient) {
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Construction.class, new ConstructionsConverter());
//        gsonBuilder.registerTypeAdapter(SearchNumberDTO.class, new SearchNumberConverter());
//        gsonBuilder.registerTypeAdapter(SearchStreetDTO.class, new SearchStreetConverter());
//        gsonBuilder.registerTypeAdapter(Report.class, new ReportConverter());
//
//        Retrofit.Builder retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
//
//        if (BuildConfig.SHOW_VERSION) {
//            retrofit.client(okHttpClient);
//        }
//
//        return retrofit.build().create(Api.Constructions.class);
//    }
//
//    @Provides
//    @ApplicationScope
//    public Api.Disturbance provideRetrofitDisturbance(OkHttpClient okHttpClient) {
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Disturbance.class, new DisturbanceConverter());
//
//        Retrofit.Builder retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL_DISTURBANCE + (BuildConfig.SHOW_VERSION ? "api-test/" : "api/"))
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
//
//        if (BuildConfig.SHOW_VERSION) {
//            retrofit.client(okHttpClient);
//        }
//
//        return retrofit.build().create(Api.Disturbance.class);
//    }
//
//    @Provides
//    @ApplicationScope
//    public Api.Consumptions provideRetrofit(OkHttpClient okHttpClient) {
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(PowerGraph.class, new GraphConverter());
//        gsonBuilder.registerTypeAdapter(Zodiac.class, new DistrictPowerConverter());
//
//        Retrofit.Builder retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL_CONSUMPTION)
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
//
//        if (BuildConfig.SHOW_VERSION) {
//            retrofit.client(okHttpClient);
//        }
//
//        return retrofit.build().create(Api.Consumptions.class);
//    }

}
