package com.example.carla.examencaso3;

/**
 * Created by carla on 25/02/18.
 */

public class CardAlumnos{
    private String nombre,control,cel,mail, carrera,creditos;

    public CardAlumnos(String nombre,String control,String cel,String mail, String carrera,String creditos) {
        this.nombre = nombre;
        this.control= control;
        this.cel = cel;
        this.mail = mail;
        this.carrera=carrera;
        this.creditos=creditos;
    }
    public String getNombre() {
        return nombre;
    }
    public String getControl() {
        return control;
    }
    public String getCel() {
        return cel;
    }
    public String getMail() {
        return mail;
    }
    public String getCarrera() {
        return carrera;
    }
    public String getCreditos() {
        return creditos;
    }
}
