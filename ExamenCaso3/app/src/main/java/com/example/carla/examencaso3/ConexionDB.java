package com.example.carla.examencaso3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shepe on 14/03/18.
 */

public class ConexionDB extends SQLiteOpenHelper {
    public ConexionDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ALUMNO(ALUMNOID INTEGER PRIMARY KEY AUTOINCREMENT , NOMBRE VARCHAR(100), CONTROL VARCHAR (20), CEL VARCHAR(15),MAIL VARCHAR(50),CARRERA VARCHAR(50))");
        db.execSQL("CREATE TABLE ACTIVIDAD(ACTIVIDADID INTEGER PRIMARY KEY AUTOINCREMENT , NOMBRE VARCHAR(100), FECHAI DATE,FECHAF DATE, CREDITOS INTEGER)");
        db.execSQL("CREATE TABLE ALUMNO_ACTIVIDAD(IDAL INTEGER,IDAC INTEGER,FOREIGN KEY (IDAL) REFERENCES ALUMNO(ALUMNOID),FOREIGN KEY (IDAC) REFERENCES ACTIVIDAD(ACTIVIDADID))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
