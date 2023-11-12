package com.example.tarea04;
public class Proyecto {
    public String titulo;
    public String descripcion;
    public String estado;
    public String notas;

    public Proyecto(String titulo, String descripcion, String estado, String notas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.notas = notas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public String getNotas() {
        return notas;
    }
}

