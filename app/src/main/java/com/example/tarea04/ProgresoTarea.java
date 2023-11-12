package com.example.tarea04;

import java.util.ArrayList;
import java.util.List;

public class ProgresoTarea {
    public String titulo;
    public String estado;
    public String fechaVencimiento;
    int porcentajeAvance;
    List<String> comentarios;

    public ProgresoTarea(String titulo, String estado, int porcentajeAvance, String fechaVencimiento) {
        this.titulo = titulo;
        this.estado = estado;
        this.porcentajeAvance = porcentajeAvance;
        this.fechaVencimiento = fechaVencimiento;
        this.comentarios = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstado() {
        return estado;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
}

