package com.victoriaramirez.seriesapp.restApi.adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoriaramirez.seriesapp.restApi.ConstantsRestApi;
import com.victoriaramirez.seriesapp.restApi.EndPointsApi;
import com.victoriaramirez.seriesapp.restApi.deserializer.SerieDeserializer;
import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndPointsApi establishConnection(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointsApi.class);
    }

    public Gson GsonDeserializerSerieBuilder(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SerieResponse.class, new SerieDeserializer());
        return gsonBuilder.create();
    }
}
