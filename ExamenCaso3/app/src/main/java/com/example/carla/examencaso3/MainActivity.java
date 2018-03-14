package com.example.carla.examencaso3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button actividades,alumnos,asigAct,consulta,conAct,ActAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actividades=findViewById(R.id.button7);
        alumnos=findViewById(R.id.button8);
        asigAct=findViewById(R.id.btnAsigAct);
        consulta= findViewById(R.id.btnConsultar);
        conAct=findViewById(R.id.btnActividades);
        ActAl=findViewById(R.id.btnActAlumn);

        actividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actividades();
            }
        });
        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alumnos();
            }
        });
        asigAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignAct();
            }
        });
        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta();
            }
        });
        conAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultaAct();
            }
        });
        ActAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultaActAl();
            }
        });
    }

    private void consultaActAl() {
        Intent ventana=new Intent(MainActivity.this,ActividadesAlumno.class);
        finish();
        startActivity(ventana);
    }

    private void consultaAct() {
        Intent ventana=new Intent(MainActivity.this,ConsultaActividades.class);
        finish();
        startActivity(ventana);
    }

    private void consulta() {
        Intent ventana=new Intent(MainActivity.this,ConsultaAlumno.class);
        finish();
        startActivity(ventana);
    }

    private void asignAct() {
        Intent ventana=new Intent(MainActivity.this,ActividadAlumno.class);
        finish();
        startActivity(ventana);
    }

    private void alumnos() {
        Intent ventana=new Intent(MainActivity.this,VistaAlumnos.class);
        finish();
        startActivity(ventana);
    }

    private void actividades() {
        Intent ventana=new Intent(MainActivity.this,VistaActividades.class);
        finish();
        startActivity(ventana);
    }
}
