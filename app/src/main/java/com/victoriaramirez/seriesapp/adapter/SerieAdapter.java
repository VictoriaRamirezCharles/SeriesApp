package com.victoriaramirez.seriesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.victoriaramirez.seriesapp.R;
import com.victoriaramirez.seriesapp.SerieDetail;
import com.victoriaramirez.seriesapp.models.Serie;


import java.util.ArrayList;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.SerieViewHolder>{

    ArrayList<Serie> series;
    Activity activity;

    public SerieAdapter(ArrayList<Serie> series, Activity activity) {
        this.series = series;
        this.activity = activity;
    }

    @Override
    public SerieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_serie, parent, false);

        return new SerieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SerieViewHolder serieViewHolder, int position) {

        final Serie serie = series.get(position);

        Picasso.with(activity)
                .load(serie.getImage().getOriginal())
                .placeholder(R.drawable.ic_launcher_background)
                .into(serieViewHolder.imgFoto);



        serieViewHolder.tvName.setText(String.valueOf(serie.getName()));
        serieViewHolder.tvStatus.setText(String.valueOf(serie.getStatus()));

        serieViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(activity, SerieDetail.class);
                intent.putExtra("url_image", serie.getImage().getMedium());
                intent.putExtra("status", serie.getStatus());


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transicion_foto)).toBundle());
                }else {
                    activity.startActivity(intent);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public static class SerieViewHolder extends RecyclerView.ViewHolder  {

        private ImageView imgFoto;
        private TextView tvName;
        private TextView tvStatus;


        public SerieViewHolder(View itemView) {
            super(itemView);

            imgFoto     = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvName    = (TextView) itemView.findViewById(R.id.tvNameCV);
            tvStatus  = (TextView) itemView.findViewById(R.id.tvStatusCV);

        }
    }
}
