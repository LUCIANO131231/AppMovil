package com.example.tarea04;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

public class ActivityCrearProyectos extends AppCompatActivity {

    EditText etTitulo, etDescripcion, etNotasProyecto;
    Button btnCrearProyecto;
    Spinner spinnerEstado;
    RecyclerView recycler;
    RecyclerView.LayoutManager view;
    ProyectoAdapter proyectoAdapter;
    List<Proyecto> listaProyectos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyectos);

        Toolbar myToolbar = findViewById(R.id.toolbarProyectos);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listaProyectos = new ArrayList<>();

        /*recycler = findViewById(R.id.rvProyectos);
        view = new LinearLayoutManager(this);
        recycler.setLayoutManager(view);
        proyectoAdapter = new ProyectoAdapter(listaProyectos);
        recycler.setAdapter(proyectoAdapter);*/

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        etNotasProyecto = findViewById(R.id.etNotasProyecto);
        btnCrearProyecto = findViewById(R.id.btnCrearProyecto);

        btnCrearProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = etTitulo.getText().toString().trim();
                String descripcion = etDescripcion.getText().toString().trim();
                String estado = spinnerEstado.getSelectedItem().toString();
                String notasProyecto = etNotasProyecto.getText().toString().trim();

                if (titulo.isEmpty() || descripcion.isEmpty() || estado.isEmpty() || notasProyecto.isEmpty()) {
                    Toast.makeText(ActivityCrearProyectos.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Proyecto nuevoProyecto = new Proyecto(titulo, descripcion, estado, notasProyecto);
                    listaProyectos.add(nuevoProyecto);
                    /*proyectoAdapter.notifyItemInserted(listaProyectos.size()-1);*/
                    enviarDatosProyecto(nuevoProyecto);
                    limpiarCampos();
                }
            }
        });
    }
    private void enviarDatosProyecto(Proyecto proyecto) {
        Intent intent = new Intent(ActivityCrearProyectos.this, MostrarDatosProyecto.class);
        intent.putExtra("titulo", proyecto.getTitulo());
        intent.putExtra("descripcion", proyecto.getDescripcion());
        intent.putExtra("estado", proyecto.getEstado());
        intent.putExtra("notas", proyecto.getNotas());
        startActivity(intent);
    }
    private void limpiarCampos() {
        etTitulo.getText().clear();
        etDescripcion.getText().clear();
        etNotasProyecto.getText().clear();
        spinnerEstado.setSelection(0);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}