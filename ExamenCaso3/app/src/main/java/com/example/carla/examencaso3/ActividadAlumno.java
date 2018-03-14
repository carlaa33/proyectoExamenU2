package com.example.carla.examencaso3;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActividadAlumno extends AppCompatActivity {
    Button guardar,cancelar;
    ArrayList<String> listaAlumnos,listaActividades;
    ArrayAdapter adapterAlumnos;
    ArrayAdapter<String> adapterActividades;
    Spinner alumnos,actividades;
    ConexionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_alumno);

        guardar=findViewById(R.id.btnGuardarActAl);
        cancelar=findViewById(R.id.btnCancelarActAl);
        alumnos=findViewById(R.id.spinnerAlumno);
        actividades=findViewById(R.id.spinnerActividad);
        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);

        listaAlumnos=new ArrayList<>();
        listaActividades=new ArrayList<>();

        consultaNombresAlumnos();
        consultaNombresActividades();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });



    }

    private void guardar() {
        String idAlumno=getAlumno(alumnos.getSelectedItem().toString());
        String idActividad=getActividad(actividades.getSelectedItem().toString());

        try{
            SQLiteDatabase base=conexion.getWritableDatabase();
            String SQL="INSERT INTO ALUMNO_ACTIVIDAD VALUES(<IDAL>,<IDAC>)";
            SQL=SQL.replace("<IDAL>",idAlumno);
            SQL=SQL.replace("<IDAC>",idActividad);

            base.execSQL(SQL);

            base.close();

            Toast.makeText(this,"Se guardó correctamente!",Toast.LENGTH_LONG).show();

            volver();

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    private String getActividad(String nombre) {
        try{
            String dato="";
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT ACTIVIDADID FROM ACTIVIDAD WHERE NOMBRE='"+nombre+"'";

            Cursor result=db.rawQuery(sql,null);
            while(result.moveToNext()){
                dato=result.getString(0);
            }
            db.close();
            return dato;
        }catch (SQLException e){

        }
        return "";


    }

    private String getAlumno(String nombre) {
        try{
            String dato="";
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT ALUMNOID FROM ALUMNO WHERE NOMBRE='"+nombre+"'";
            Cursor result=db.rawQuery(sql,null);
            while(result.moveToNext()){
                dato=result.getString(0);
            }
            db.close();
            return dato;
        }catch (SQLException e){

        }

        return "";
    }

    private void consultaNombresAlumnos() {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT NOMBRE FROM ALUMNO";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                return;
            }
            while(result.moveToNext()){
                listaAlumnos.add(result.getString(0));
            }
            db.close();
            adapterAlumnos = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaAlumnos);
            alumnos.setAdapter(adapterAlumnos);
        }catch (SQLException e){

        }

    }

    private void consultaNombresActividades() {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT NOMBRE FROM ACTIVIDAD";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                return;
            }
            while(result.moveToNext()){
                listaActividades.add(result.getString(0));
            }
            db.close();
            adapterActividades= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaActividades);
            actividades.setAdapter(adapterActividades);
        }catch (SQLException e){

        }
    }

    private void volver() {
        Intent ventana=new Intent(ActividadAlumno.this,MainActivity.class);
        finish();
        startActivity(ventana);
    }
}
