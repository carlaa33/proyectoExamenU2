package com.example.carla.examencaso3;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActividadesAlumno extends AppCompatActivity {
    Button consulta,volver;
    Spinner alumnos;
    ArrayList<String> listaAlumnos;
    ArrayAdapter<String> adapterAlumnos;
    ArrayList<String> listaActividades;
    ArrayAdapter<String> adaptador;
    ListView acts;
    ConexionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_alumno);
        consulta=findViewById(R.id.btnConsultaActAl);
        volver=findViewById(R.id.btnVolver);
        alumnos=findViewById(R.id.spinner2);
        acts=findViewById(R.id.Actividades);

        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);
        listaActividades=new ArrayList<>();
        listaAlumnos=new ArrayList<>();

        consultaNombresAlumnos();

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(alumnos.getSelectedItem().toString());
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });
    }

    private void volver() {
        Intent ventana=new Intent(ActividadesAlumno.this,MainActivity.class);
        finish();
        startActivity(ventana);
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

    private void consultar(String s) {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT A.NOMBRE,A.FECHAI,A.FECHAF FROM ACTIVIDAD A \n" +
                    "INNER JOIN ALUMNO_ACTIVIDAD B ON B.IDAC=A.ACTIVIDADID \n" +
                    "INNER JOIN ALUMNO C ON C.ALUMNOID=B.IDAL WHERE C.NOMBRE='"+s+"' group by A.nombre  ORDER BY A.FECHAI DESC;";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                listaActividades=new ArrayList<>();
                adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaActividades);
                acts.setAdapter(adaptador);
                return;
            }
            while(result.moveToNext()){
                listaActividades.add(result.getString(0)+": Del "+result.getString(1)+" al"+result.getString(2));
            }
            db.close();

            adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaActividades);
            acts.setAdapter(adaptador);

        }catch (SQLException e){

        }
    }
}
