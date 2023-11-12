package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MostrarDatosTarea extends AppCompatActivity {
    TextView Datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_tarea);

        Datos = findViewById(R.id.tvDatosTarea);
        mostrarDatosTarea(getIntent());
    }

    private void mostrarDatosTarea(Intent intent) {
            String titulo = intent.getStringExtra("titulo");
            String descripcion = intent.getStringExtra("descripcion");
            String fechaVencimiento = intent.getStringExtra("fechaVencimiento");
            String prioridad = intent.getStringExtra("prioridad");
            ArrayList<String> miembrosEquipo = intent.getStringArrayListExtra("miembrosEquipo");
            Datos.setText("Título: " + titulo + "\nDescripción: " + descripcion + "\nFecha Vencimiento: " + fechaVencimiento
                    + "\nPrioridad: " + prioridad + "\nMiembros del Equipo: " + miembrosEquipo);
    }
}