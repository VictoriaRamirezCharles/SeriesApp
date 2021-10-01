package com.victoriaramirez.seriesapp.restApi;

import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointsApi {

    @GET(ConstantsRestApi.URL_SERIES)
    Call<SerieResponse> getSeries();
}
