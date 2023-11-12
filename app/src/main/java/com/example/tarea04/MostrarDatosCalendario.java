package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarDatosCalendario extends AppCompatActivity {
    TextView Datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_calendario);

        Datos = findViewById(R.id.tvDatosCalendario);
        mostrarDatosCalendario(getIntent());
    }
    private void mostrarDatosCalendario(Intent intent){
            String horaInicio = intent.getStringExtra("horaInicio");
            String horaFin = intent.getStringExtra("horaFin");
            String ubicacion = intent.getStringExtra("ubicacion");
            Datos.setText("Hora de Inicio: " + horaInicio + "\nHora de Fin: " + horaFin + "\nUbicacion: " + ubicacion);
    }
}