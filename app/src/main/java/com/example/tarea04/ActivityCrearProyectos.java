package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityCrearProyectos extends AppCompatActivity {

    EditText etTitulo, etDescripcion;
    Button btnCrear;
    RecyclerView rvMiembros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyectos);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        rvMiembros = findViewById(R.id.rvMiembros);
        btnCrear = findViewById(R.id.btnCrear);

        rvMiembros.setLayoutManager(new LinearLayoutManager(this));

        List<String> listaDeMiembros = new ArrayList<>();
        listaDeMiembros.add("Miembro 1");
        listaDeMiembros.add("Miembro 2");

        AdapterMiembros adapterMiembros = new AdapterMiembros(listaDeMiembros);
        rvMiembros.setAdapter(adapterMiembros);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitulo.getText().toString().trim();
                String description = etDescripcion.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    Toast.makeText(ActivityCrearProyectos.this, "Proyecto creado: " + title, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCrearProyectos.this, "Todos los campos son necesarios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
