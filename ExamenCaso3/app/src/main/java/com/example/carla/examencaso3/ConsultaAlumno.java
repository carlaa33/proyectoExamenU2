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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultaAlumno extends AppCompatActivity {
    Button consulta,guardar;
    Spinner alumno;
    EditText id,nombre,control,cel,email,carrera;
    ArrayList<String> listaAlumnos;
    ArrayAdapter adapterAlumnos;
    ConexionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_alumno);
        consulta=findViewById(R.id.btnConsulta);
        guardar=findViewById(R.id.btnGuardar);


        id=findViewById(R.id.editID);
        nombre=findViewById(R.id.editNombre);
        control=findViewById(R.id.editControl);
        cel=findViewById(R.id.editCel);
        email=findViewById(R.id.editEmail);
        carrera=findViewById(R.id.editCarrera);
        alumno=findViewById(R.id.spinnerAl);

        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);
        listaAlumnos=new ArrayList<>();

        consultaNombresAlumnos();

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(alumno.getSelectedItem().toString());
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
        try{
            SQLiteDatabase base=conexion.getWritableDatabase();
            String SQL="UPDATE ALUMNO SET NOMBRE= '<NOMBRE>', CONTROL='<CONTROL>',CEL='<CEL>',MAIL='<MAIL>',CARRERA='<CARRERA>' WHERE ALUMNOID="+id.getText();
            SQL=SQL.replace("<NOMBRE>",nombre.getText().toString());
            SQL=SQL.replace("<CONTROL>",control.getText().toString());
            SQL=SQL.replace("<CEL>",cel.getText().toString());
            SQL=SQL.replace("<MAIL>",email.getText().toString());
            SQL=SQL.replace("<CARRERA>",carrera.getText().toString());

            base.execSQL(SQL);

            base.close();

            Toast.makeText(this,"Se guardó correctamente!",Toast.LENGTH_LONG).show();
            volver();

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void volver() {
        Intent ventana=new Intent(ConsultaAlumno.this,MainActivity.class);
        finish();
        startActivity(ventana);
    }

    private void consultar(String nombre) {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT * FROM ALUMNO WHERE NOMBRE='"+nombre+"'";
            Cursor result=db.rawQuery(sql,null);
            while(result.moveToNext()){
                id.setText(result.getString(0));
                this.nombre.setText(result.getString(1));
                control.setText(result.getString(2));
                cel.setText(result.getString(3));
                email.setText(result.getString(4));
                carrera.setText(result.getString(5));
            }
            db.close();
        }catch (SQLException e){

        }

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
            alumno.setAdapter(adapterAlumnos);
        }catch (SQLException e){

        }
    }
}
