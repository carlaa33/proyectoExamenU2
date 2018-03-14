package com.example.carla.examencaso3;

/**
 * Created by carla on 25/02/18.
 */

public class CardActividades {
    private String nombre,fechaInicio,fechaFin,creditos;

    public CardActividades(String nombre, String fechaInicio, String fechaFin, String creditos) {
        this.nombre = nombre;
        this.fechaInicio= fechaInicio;
        this.fechaFin = fechaFin;
        this.creditos = creditos;
    }
    public String getNombre() {
        return nombre;
    }
    public String getHoraInicio() {
        return fechaInicio;
    }

    public String getHoraFin() {
        return fechaFin;
    }

    public String getCreditos() {
        return creditos;
    }
}
