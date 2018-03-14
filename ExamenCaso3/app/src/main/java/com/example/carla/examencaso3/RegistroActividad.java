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

public class RegistroActividad extends AppCompatActivity {
    Button btnGuardarActividad,btnCancelarActividad;
    EditText actividad, fechaI, fechaF, creditos;
    ConexionDB conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividad);
        btnGuardarActividad=findViewById(R.id.btnGuardarActividad);
        btnCancelarActividad=findViewById(R.id.btnCancelarActividad);

        actividad=findViewById(R.id.editNombreAct);
        fechaI=findViewById(R.id.editFI);
        fechaF=findViewById(R.id.editFF);
        creditos=findViewById(R.id.editCred);

        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);

        btnCancelarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
        btnGuardarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarActividad();
            }
        });

    }

    private void guardarActividad() {
        try{
            SQLiteDatabase base=conexion.getWritableDatabase();
            String SQL="INSERT INTO ACTIVIDAD VALUES(NULL,'<NOMBRE>','<FI>','<FF>',<CREDITOS>)";
            SQL=SQL.replace("<NOMBRE>",actividad.getText().toString());
            SQL=SQL.replace("<FI>",fechaI.getText().toString());
            SQL=SQL.replace("<FF>",fechaF.getText().toString());
            SQL=SQL.replace("<CREDITOS>",creditos.getText().toString());

            base.execSQL(SQL);

            base.close();

            Toast.makeText(this,"Se guardó correctamente!",Toast.LENGTH_LONG).show();
            actividad.setText("");
            fechaI.setText("");
            fechaF.setText("");
            creditos.setText("");
            cancelar();

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void cancelar() {
        Intent ventana=new Intent(RegistroActividad.this,VistaActividades.class);
        finish();
        startActivity(ventana);
    }
}
