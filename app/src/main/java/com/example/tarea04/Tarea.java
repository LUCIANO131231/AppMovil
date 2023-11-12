package com.example.tarea04;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
    public String titulo;
    public String descripcion;
    public String fechaVencimiento;
    public String prioridad;
    List<String> miembrosEquipo;

    public Tarea(String titulo, String descripcion, String fechaVencimiento, String prioridad, List<String> miembrosEquipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = prioridad;
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

    public String getPrioridad() {
        return prioridad;
    }

    public List<String> getMiembrosEquipo() {
        return miembrosEquipo;
    }
}