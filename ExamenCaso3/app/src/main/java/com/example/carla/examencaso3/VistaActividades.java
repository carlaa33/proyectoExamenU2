package com.example.carla.examencaso3;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class VistaActividades extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterActividades adapter;
    Button nuevo,volver;

    ConexionDB conexion;

    List<CardActividades> actividadesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_actividades);
        nuevo=findViewById(R.id.button6);
        volver=findViewById(R.id.button5);
        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevo();
            }
        });

        actividadesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerActividades);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        consultaActividades();


    }

    private void consultaActividades() {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT NOMBRE,FECHAI,FECHAF, CREDITOS FROM ACTIVIDAD ORDER BY FECHAI DESC";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                return;
            }
            while(result.moveToNext()){
                actividadesList.add(
                        new CardActividades(
                                result.getString(0),
                                result.getString(1),
                                result.getString(2),
                                result.getString(3))
                );
            }
            db.close();
            adapter = new AdapterActividades(this,actividadesList);
            recyclerView.setAdapter(adapter);
        }catch (SQLException e){

        }
    }

    private void nuevo() {
        Intent ventana=new Intent(VistaActividades.this,RegistroActividad.class);
        finish();
        startActivity(ventana);
    }

    private void volver() {
        Intent ventana=new Intent(VistaActividades.this,MainActivity.class);
        finish();
        startActivity(ventana);
    }
}
