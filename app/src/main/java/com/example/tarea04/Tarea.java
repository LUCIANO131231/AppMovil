package com.example.tarea04;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarea {
    public String titulo;
    public String descripcion;
    public String fechaVencimiento;
    List<String> miembrosEquipo;
    public Tarea(){}

    public Tarea(String titulo, String descripcion, String fechaVencimiento, List<String> miembrosEquipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.miembrosEquipo = new ArrayList<>(miembrosEquipo);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public List<String> getMiembrosEquipo() {
        return miembrosEquipo;
    }
}