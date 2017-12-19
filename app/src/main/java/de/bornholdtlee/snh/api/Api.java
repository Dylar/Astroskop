package de.bornholdtlee.snh.api;

import java.util.List;

import de.bornholdtlee.snh.api.dto.DisturbanceDTOList;
import de.bornholdtlee.snh.api.dto.SearchNumberDTO;
import de.bornholdtlee.snh.model.Construction;
import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.model.PowerGraph;
import de.bornholdtlee.snh.model.Report;
import de.bornholdtlee.snh.model.StreetSearchResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    interface Constructions {
        String KEYWORD = "keyword";

        @GET("wp-json/stoerung/v1/baustellen/0118119848")
        Call<List<Construction>> getAllConstructions();

        @GET("api/address/search/{street}/{city}/{keyword}")
        Call<SearchNumberDTO> searchNumbersInWeb(@Path("street") String street, @Path("city") String district, @Path(KEYWORD) String keyword);

        @GET("api/address/getStreetList")
        Call<List<StreetSearchResult>> getAllStreetSearchResult();

        @POST("wp-content/modules/includes/snhApp_stoerung-stromausfall.php")
        Call<ResponseBody> sendReport(@Body() Report report);

    }

    interface Disturbance {

        String BASE_URL_DISTURBANCE = "api.php?key=4587684a-f4af-436c-8edd-4097d08234f1";

        @GET(BASE_URL_DISTURBANCE)
        Call<DisturbanceDTOList> getAllDisturbance();

    }

    interface Consumptions {

        String BASE_URL_GRAPH = "Chart.action?showLiveStreamChart";
        String BASE_URL_DISTRICT = "Ajax.action?";

        @GET(BASE_URL_GRAPH)
        Call<PowerGraph> getAllGraph();

        @GET(BASE_URL_DISTRICT)
        Call<DistrictPower> getDistrictPower(@Query("t") long timestamp);

    }
}