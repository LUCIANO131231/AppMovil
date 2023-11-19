package com.example.tarea04;

import android.net.Uri;

public class CompartirArchivo {
    public String nombre;
    public Uri uri;

    public CompartirArchivo(String nombre, Uri uri) {
        this.nombre = nombre;
        this.uri = uri;
    }

    public String getNombre() {
        return nombre;
    }

    public Uri getUri() {
        return uri;
    }
}
