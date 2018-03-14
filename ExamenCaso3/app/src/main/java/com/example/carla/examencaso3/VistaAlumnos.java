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

public class VistaAlumnos extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterAlumnos adapter;
    Button nuevo,volver;
    ConexionDB conexion;

    List<CardAlumnos> alumnosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_alumnos);
        nuevo=findViewById(R.id.button9);
        volver=findViewById(R.id.button10);
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


        alumnosList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerAlumnos);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        consultaAlumnos();

    }

    private void consultaAlumnos() {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT A.NOMBRE,A.CONTROL,A.CEL,A.MAIL,A.CARRERA,SUM(C.CREDITOS) FROM ALUMNO A INNER JOIN ALUMNO_ACTIVIDAD B ON B.IDAL=A.ALUMNOID INNER JOIN ACTIVIDAD C ON C.ACTIVIDADID=B.IDAC GROUP BY(A.NOMBRE) ORDER BY (A.NOMBRE)";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                return;
            }
            while(result.moveToNext()){
                alumnosList.add(
                        new CardAlumnos(result.getString(0),
                                result.getString(1),
                                result.getString(2),
                                result.getString(3),
                                result.getString(4),
                                result.getString(5))
                );
            }
            db.close();
            adapter = new AdapterAlumnos(this,alumnosList);
            recyclerView.setAdapter(adapter);
        }catch (SQLException e){

        }
    }

    private void nuevo() {
        Intent ventana=new Intent(VistaAlumnos.this,RegistroAlumno.class);
        finish();
        startActivity(ventana);
    }

    private void volver() {
        Intent ventana=new Intent(VistaAlumnos.this,MainActivity.class);
        finish();
        startActivity(ventana);
    }
}
