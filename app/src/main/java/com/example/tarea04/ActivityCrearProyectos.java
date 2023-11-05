package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCrearProyectos extends AppCompatActivity {

    EditText etTitulo, etDescripcion, etMiembros;
    Button btnCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyectos);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etMiembros = findViewById(R.id.etMiembros);
        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitulo.getText().toString().trim();
                String description = etDescripcion.getText().toString().trim();
                String teamMembers = etMiembros.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty() && !teamMembers.isEmpty()) {
                    Toast.makeText(ActivityCrearProyectos.this, "Proyecto creado: " + title, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCrearProyectos.this, "Todos los campos son necesarios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}