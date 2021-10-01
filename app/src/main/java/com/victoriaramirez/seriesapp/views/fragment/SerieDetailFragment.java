package com.victoriaramirez.seriesapp.views.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.victoriaramirez.seriesapp.R;
import com.victoriaramirez.seriesapp.models.Serie;
import com.victoriaramirez.seriesapp.restApi.EndPointsApi;
import com.victoriaramirez.seriesapp.restApi.adapter.RestApiAdapter;
import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieDetailFragment extends Fragment  {

    private ArrayList<Serie> series;
    TableLayout tl;

    public SerieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view  = inflater.inflate(R.layout.fragment_serie_detail, container, false);

        tl = (TableLayout) view.findViewById(R.id.serie_table);

        GetSeries();

        return view;
    }

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

                TableRow trHeder = new TableRow(getActivity());
                trHeder.setBackgroundResource(R.drawable.separator);
                trHeder.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView headerName = new TextView(getContext());
                TextView headerStatus = new TextView(getContext());
                headerName.setText("Name");
                headerStatus.setText("Status");
                headerName.setPadding(100, 10, 500, 50);
                headerStatus.setPadding(100, 10, 10, 50);
                headerName.setTypeface(headerName.getTypeface(), Typeface.BOLD);
                headerStatus.setTypeface(headerStatus.getTypeface(), Typeface.BOLD);
                trHeder.addView(headerName);
                trHeder.addView(headerStatus);
                tl.addView(trHeder, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                for (int i = 0; i < 9; i++) {
                    TableRow tr = new TableRow(getActivity());

                    tr.setBackgroundResource(R.drawable.separator);
                    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                    for(int j = 0; j < 1; j++) {
                        TextView tv = new TextView(getContext());
                        TextView tv2 = new TextView(getContext());


                        tv.setText(series.get(i).getName());
                        tv2.setText(series.get(i).getStatus());

                        tv.setPadding(100, 10, 500, 50);
                        tv2.setPadding(100, 10, 50, 50);

                        tr.addView(tv);
                        tr.addView(tv2);

                    }
                    tl.addView(tr, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                }


            }

            @Override
            public void onFailure(Call<SerieResponse> call, Throwable t) {
                Toast.makeText(getContext(), "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }
}
