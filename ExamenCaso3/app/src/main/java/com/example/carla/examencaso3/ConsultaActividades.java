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

public class ConsultaActividades extends AppCompatActivity {
    Button consulta,guardar;
    Spinner actividad;
    EditText id,nombre,fechaI,fechaF,creditos;
    ArrayList<String> listaAct;
    ArrayAdapter adapterAct;
    ConexionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_actividades);
        consulta=findViewById(R.id.btnConsultaAct);
        guardar=findViewById(R.id.btnGuardarAct);

        id=findViewById(R.id.IDAct);
        nombre=findViewById(R.id.editNombreAct);
        fechaI=findViewById(R.id.editFI);
        fechaF=findViewById(R.id.editFF);
        creditos=findViewById(R.id.editCred);
        actividad=findViewById(R.id.spinnerAct);

        conexion=new ConexionDB(this,"baseExamenCaso3",null,1);
        listaAct=new ArrayList<>();

        consultaNombresAct();

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(actividad.getSelectedItem().toString());
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
            String SQL="UPDATE ACTIVIDAD SET NOMBRE= '<NOMBRE>', FECHAI='<FI>',FECHAF='<FF>',CREDITOS='<CREDITOS>' WHERE ACTIVIDADID="+id.getText();
            SQL=SQL.replace("<NOMBRE>",nombre.getText().toString());
            SQL=SQL.replace("<FI>",fechaI.getText().toString());
            SQL=SQL.replace("<FF>",fechaF.getText().toString());
            SQL=SQL.replace("<CREDITOS>",creditos.getText().toString());

            base.execSQL(SQL);

            base.close();

            Toast.makeText(this,"Se guardó correctamente!",Toast.LENGTH_LONG).show();
            volver();

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void volver() {
        Intent ventana=new Intent(ConsultaActividades.this,MainActivity.class);
        finish();
        startActivity(ventana);
    }

    private void consultar(String nombre) {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT * FROM ACTIVIDAD WHERE NOMBRE='"+nombre+"'";
            Cursor result=db.rawQuery(sql,null);
            while(result.moveToNext()){
                id.setText(result.getString(0));
                this.nombre.setText(result.getString(1));
                fechaI.setText(result.getString(2));
                fechaF.setText(result.getString(3));
                creditos.setText(result.getString(4));
            }
            db.close();
        }catch (SQLException e){

        }

    }

    private void consultaNombresAct() {
        try{
            SQLiteDatabase db= conexion.getReadableDatabase();
            String sql="SELECT NOMBRE FROM ACTIVIDAD";
            Cursor result=db.rawQuery(sql,null);
            if(result.getCount()<= 0){
                return;
            }
            while(result.moveToNext()){
                listaAct.add(result.getString(0));
            }
            db.close();
            adapterAct= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaAct);
            actividad.setAdapter(adapterAct);
        }catch (SQLException e){

        }

    }
}
