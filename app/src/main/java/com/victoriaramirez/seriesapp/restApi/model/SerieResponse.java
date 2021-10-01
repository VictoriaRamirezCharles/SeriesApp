package com.victoriaramirez.seriesapp.restApi.model;


import com.victoriaramirez.seriesapp.models.Serie;

import java.util.ArrayList;

public class SerieResponse {

    ArrayList<Serie> series;

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
}
