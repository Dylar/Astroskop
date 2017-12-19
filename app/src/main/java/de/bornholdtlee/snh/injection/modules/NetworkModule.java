package de.bornholdtlee.snh.injection.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.snh.BuildConfig;
import de.bornholdtlee.snh.api.Api;
import de.bornholdtlee.snh.api.converter.ConstructionsConverter;
import de.bornholdtlee.snh.api.converter.DistrictPowerConverter;
import de.bornholdtlee.snh.api.converter.GraphConverter;
import de.bornholdtlee.snh.api.converter.DisturbanceConverter;
import de.bornholdtlee.snh.api.converter.ReportConverter;
import de.bornholdtlee.snh.api.converter.SearchNumberConverter;
import de.bornholdtlee.snh.api.converter.SearchStreetConverter;
import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.model.PowerGraph;
import de.bornholdtlee.snh.api.dto.SearchNumberDTO;
import de.bornholdtlee.snh.api.dto.SearchStreetDTO;
import de.bornholdtlee.snh.injection.ApplicationScope;
import de.bornholdtlee.snh.model.Construction;
import de.bornholdtlee.snh.model.Disturbance;
import de.bornholdtlee.snh.model.Report;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Provides
    @ApplicationScope
    public Api.Constructions provideRetrofitConstructions(OkHttpClient okHttpClient) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Construction.class, new ConstructionsConverter());
        gsonBuilder.registerTypeAdapter(SearchNumberDTO.class, new SearchNumberConverter());
        gsonBuilder.registerTypeAdapter(SearchStreetDTO.class, new SearchStreetConverter());
        gsonBuilder.registerTypeAdapter(Report.class, new ReportConverter());

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        if (BuildConfig.SHOW_VERSION) {
            retrofit.client(okHttpClient);
        }

        return retrofit.build().create(Api.Constructions.class);
    }

    @Provides
    @ApplicationScope
    public Api.Disturbance provideRetrofitDisturbance(OkHttpClient okHttpClient) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Disturbance.class, new DisturbanceConverter());

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_DISTURBANCE + (BuildConfig.SHOW_VERSION ? "api-test/" : "api/"))
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        if (BuildConfig.SHOW_VERSION) {
            retrofit.client(okHttpClient);
        }

        return retrofit.build().create(Api.Disturbance.class);
    }

    @Provides
    @ApplicationScope
    public Api.Consumptions provideRetrofit(OkHttpClient okHttpClient) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PowerGraph.class, new GraphConverter());
        gsonBuilder.registerTypeAdapter(DistrictPower.class, new DistrictPowerConverter());

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_CONSUMPTION)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        if (BuildConfig.SHOW_VERSION) {
            retrofit.client(okHttpClient);
        }

        return retrofit.build().create(Api.Consumptions.class);
    }

}
