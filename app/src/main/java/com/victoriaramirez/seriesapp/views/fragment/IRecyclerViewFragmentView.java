package com.victoriaramirez.seriesapp.views.fragment;

import com.victoriaramirez.seriesapp.adapter.SerieAdapter;
import com.victoriaramirez.seriesapp.models.Serie;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generateLinearLayoutVertical();
    void generateGridLayout();
    SerieAdapter CreateAdapter(ArrayList<Serie> series);
    void AdapterInicializer(SerieAdapter adapter);
}
