package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarDatosProyecto extends AppCompatActivity {
    TextView Datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_proyecto);

        Datos = findViewById(R.id.tvDatosProyecto);
        mostrarDatosProyecto(getIntent());
    }
    private void mostrarDatosProyecto(Intent intent){
        String titulo = intent.getStringExtra("titulo");
        String descripcion = intent.getStringExtra("descripcion");
        String estado = intent.getStringExtra("estado");
        String notas = intent.getStringExtra("notas");
        Datos.setText("Título: " + titulo + "\nDescripción: " + descripcion + "\nEstado del proyecto: " + estado
                + "\nNotas adicionales: " + notas);
    }
}