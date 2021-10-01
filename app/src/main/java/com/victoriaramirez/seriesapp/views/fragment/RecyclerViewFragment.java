package com.victoriaramirez.seriesapp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.victoriaramirez.seriesapp.R;
import com.victoriaramirez.seriesapp.adapter.SerieAdapter;
import com.victoriaramirez.seriesapp.models.Serie;
import com.victoriaramirez.seriesapp.presenter.IRecylerViewFragmentPresenter;
import com.victoriaramirez.seriesapp.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment  extends Fragment implements IRecyclerViewFragmentView {

    private ArrayList<Serie> series;
    private RecyclerView rvSeries;
    private IRecylerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        rvSeries = (RecyclerView) v.findViewById(R.id.rvSeries);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return v;
    }
    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSeries.setLayoutManager(llm);


    }
    @Override
    public void generateGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvSeries.setLayoutManager(gridLayoutManager);
    }

    @Override
    public SerieAdapter CreateAdapter(ArrayList<Serie> series) {
        SerieAdapter adapter = new SerieAdapter(series, getActivity()  );
        return adapter;
    }

    @Override
    public void AdapterInicializer(SerieAdapter adaptador) {
        rvSeries.setAdapter(adaptador);
    }

}