package com.example.carla.examencaso3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.AlumnosViewHolder>{
    private Context mCtx;
    private List<CardAlumnos> alumnosList;

    public AdapterAlumnos(Context mCtx, List<CardAlumnos> alumnosList) {
        this.mCtx = mCtx;
        this.alumnosList = alumnosList;
    }

    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_card_alumnos,null);
        AlumnosViewHolder holder = new AlumnosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int position) {
        CardAlumnos alumnos= alumnosList.get(position);
        holder.nombre.setText(alumnos.getNombre());
        holder.control.setText(alumnos.getControl());
        holder.cel.setText(alumnos.getCel());
        holder.mail.setText(alumnos.getMail());
        holder.carrera.setText(alumnos.getCarrera());
        holder.creditos.setText(alumnos.getCreditos());

    }

    @Override
    public int getItemCount() {
        return alumnosList.size();
    }

    class AlumnosViewHolder extends RecyclerView.ViewHolder{

        TextView nombre,control,cel,mail, carrera,creditos;

        public AlumnosViewHolder(View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.nombreA);
            control= itemView.findViewById(R.id.numControl);
            cel= itemView.findViewById(R.id.cel);
            mail= itemView.findViewById(R.id.mail);
            carrera= itemView.findViewById(R.id.carrera);
            creditos= itemView.findViewById(R.id.creditosA);
        }
    }
}
