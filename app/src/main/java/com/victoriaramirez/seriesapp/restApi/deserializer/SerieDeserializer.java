package com.victoriaramirez.seriesapp.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.victoriaramirez.seriesapp.models.Image;
import com.victoriaramirez.seriesapp.models.Serie;
import com.victoriaramirez.seriesapp.restApi.JsonKeys;
import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SerieDeserializer implements JsonDeserializer<SerieResponse> {
    @Override
    public SerieResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        SerieResponse serieResponse = new SerieResponse();
        JsonArray serieResponseData = json.getAsJsonArray();

        serieResponse.setSeries(serieDeserializerOfJson(serieResponseData));

        return serieResponse;
    }

    private ArrayList<Serie> serieDeserializerOfJson(JsonArray movieResponseData){

        ArrayList<Serie> series = new ArrayList<>();
        for (int i = 40; i < 49 ; i++) {
            JsonObject movieResponseDataObject = movieResponseData.get(i).getAsJsonObject();


            String id               = movieResponseDataObject.get(JsonKeys.SERIE_ID).getAsString();
            String name             = movieResponseDataObject.get(JsonKeys.SERIE_NAME).getAsString();
            String status           = movieResponseDataObject.get(JsonKeys.SERIE_STATUS).getAsString();

            JsonObject imageJson    = movieResponseDataObject.getAsJsonObject(JsonKeys.SERIE_IMAGES);
            Gson gson = new Gson();
            Image obj = gson.fromJson(imageJson.toString(),Image.class);
            Serie currentSerie = new Serie();
            currentSerie.setId(id);
            currentSerie.setName(name);
            currentSerie.setImage(obj);
            currentSerie.setStatus(status);

            series.add(currentSerie);

        }

        return series;
    }
}
