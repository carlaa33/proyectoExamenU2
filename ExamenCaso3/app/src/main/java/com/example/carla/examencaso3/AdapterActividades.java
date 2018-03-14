package com.example.carla.examencaso3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterActividades extends RecyclerView.Adapter<AdapterActividades.ActividadesViewHolder>{
    private Context mCtx;
    private List<CardActividades> actividadesList;

    public AdapterActividades(Context mCtx, List<CardActividades> productList) {
        this.mCtx = mCtx;
        this.actividadesList = productList;
    }

    @Override
    public ActividadesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_card_actividades,null);
        ActividadesViewHolder holder = new ActividadesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActividadesViewHolder holder, int position) {
        CardActividades actividades = actividadesList.get(position);
        holder.nombreEvento.setText(actividades.getNombre());
        holder.fechaInicio.setText(actividades.getHoraInicio());
        holder.fechaFin.setText(String.valueOf(actividades.getHoraFin()));
        holder.numeroCreditos.setText(String.valueOf(actividades.getCreditos()));
    }

    @Override
    public int getItemCount() {
        return actividadesList.size();
    }

    class ActividadesViewHolder extends RecyclerView.ViewHolder{

        TextView nombreEvento,fechaInicio,fechaFin,numeroCreditos;

        public ActividadesViewHolder(View itemView) {
            super(itemView);
            nombreEvento= itemView.findViewById(R.id.nombreE);
            fechaInicio= itemView.findViewById(R.id.hIni);
            fechaFin = itemView.findViewById(R.id.hFin);
            numeroCreditos= itemView.findViewById(R.id.nCreditos);
        }
    }
}
