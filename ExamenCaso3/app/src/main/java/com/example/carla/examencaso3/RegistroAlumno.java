package com.example.carla.examencaso3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroAlumno extends AppCompatActivity {
    Button btnGuardarAlumno,btnCancelarAlumno;
    EditText nombre, control, cel, mail, carrera;
    ConexionDB conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_alumno);
        btnGuardarAlumno=findViewById(R.id.btnGuardar);
        btnCancelarAlumno=findViewById(R.id.btnCancelarAlumno);
        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);

        nombre=findViewById(R.id.editNombreAlumno);
        control=findViewById(R.id.editControl);
        cel=findViewById(R.id.editCel);
        mail=findViewById(R.id.editEmail);
        carrera=findViewById(R.id.editCarreraAlumno);

        btnCancelarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
        btnGuardarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarAlumno();
            }
        });
    }

    private void guardarAlumno() {
        try{
            SQLiteDatabase base=conexion.getWritableDatabase();
            String SQL="INSERT INTO ALUMNO VALUES(NULL,'<NOMBRE>','<CONTROL>','<CEL>','<MAIL>','<CARRERA>')";
            SQL=SQL.replace("<NOMBRE>",nombre.getText().toString());
            SQL=SQL.replace("<CONTROL>",control.getText().toString());
            SQL=SQL.replace("<CEL>",cel.getText().toString());
            SQL=SQL.replace("<MAIL>",mail.getText().toString());
            SQL=SQL.replace("<CARRERA>",carrera.getText().toString());

            base.execSQL(SQL);

            base.close();

            Toast.makeText(this,"Se guardó correctamente!",Toast.LENGTH_LONG).show();
            nombre.setText("");
            control.setText("");
            cel.setText("");
            mail.setText("");
            carrera.setText("");
            agregarActividadAlumno();

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void agregarActividadAlumno() {
        Intent ventana=new Intent(RegistroAlumno.this,ActividadAlumno.class);
        finish();
        startActivity(ventana);
    }

    private void cancelar() {
        Intent ventana=new Intent(RegistroAlumno.this,VistaAlumnos.class);
        finish();
        startActivity(ventana);
    }
}
