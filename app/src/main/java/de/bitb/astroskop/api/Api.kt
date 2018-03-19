package de.bitb.astroskop.api

interface Api//
//    interface Constructions {
//        String KEYWORD = "keyword";
//
//        @GET("wp-json/stoerung/v1/baustellen/0118119848")
//        Call<List<Construction>> getAllConstructions();
//
//        @GET("api/address/search/{street}/{city}/{keyword}")
//        Call<SearchNumberDTO> searchNumbersInWeb(@Path("street") String street, @Path("city") String district, @Path(KEYWORD) String keyword);
//
//        @GET("api/address/getStreetList")
//        Call<List<StreetSearchResult>> getAllStreetSearchResult();
//
//        @POST("wp-content/modules/includes/snhApp_stoerung-stromausfall.php")
//        Call<ResponseBody> sendReport(@Body() Report report);
//
//    }
//
//    interface Disturbance {
//
//        String BASE_URL_DISTURBANCE = "api.php?key=4587684a-f4af-436c-8edd-4097d08234f1";
//
//        @GET(BASE_URL_DISTURBANCE)
//        Call<DisturbanceDTOList> getAllDisturbance();
//
//    }
//
//    interface Consumptions {
//
//        String BASE_URL_GRAPH = "Chart.action?showLiveStreamChart";
//        String BASE_URL_DISTRICT = "Ajax.action?";
//
//        @GET(BASE_URL_GRAPH)
//        Call<PowerGraph> getAllGraph();
//
//        @GET(BASE_URL_DISTRICT)
//        Call<Zodiac> getDistrictPower(@Query("t") long timestamp);
//
//    }