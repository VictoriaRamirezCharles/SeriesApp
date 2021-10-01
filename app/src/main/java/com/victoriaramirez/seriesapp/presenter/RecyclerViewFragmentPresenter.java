package com.victoriaramirez.seriesapp.presenter;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.victoriaramirez.seriesapp.models.Serie;
import com.victoriaramirez.seriesapp.restApi.EndPointsApi;
import com.victoriaramirez.seriesapp.restApi.adapter.RestApiAdapter;
import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;
import com.victoriaramirez.seriesapp.views.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ArrayList<Serie> series;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        GetSeries();
    }

    @Override
    public void GetSeries() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSeries = restApiAdapter.GsonDeserializerSerieBuilder();
        EndPointsApi endpointsApi = restApiAdapter.establishConnection(gsonSeries);
        Call<SerieResponse> serieResponseCall = endpointsApi.getSeries();

        serieResponseCall.enqueue(new Callback<SerieResponse>() {
            @Override
            public void onResponse(Call<SerieResponse> call, Response<SerieResponse> response) {
                SerieResponse serieResponse = response.body();
                series = serieResponse.getSeries();
                ShowSeriesRV();
            }

            @Override
            public void onFailure(Call<SerieResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void ShowSeriesRV() {
        iRecyclerViewFragmentView.AdapterInicializer(iRecyclerViewFragmentView.CreateAdapter(series));
        iRecyclerViewFragmentView.generateLinearLayoutVertical();
    }
}
