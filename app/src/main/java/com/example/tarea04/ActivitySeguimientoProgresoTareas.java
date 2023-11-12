package com.example.tarea04;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ActivitySeguimientoProgresoTareas extends AppCompatActivity {
    RecyclerView recycler;
    ProgresoTareaAdapter progresoAdapter;
    RecyclerView.LayoutManager view;
    List<ProgresoTarea> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_progreso_tareas);

        Toolbar myToolbar = findViewById(R.id.toolbarSeguimiento);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listaTareas = new ArrayList<>();
        inicializarDatos();

        recycler = findViewById(R.id.rvSeguimientoTareas);
        view = new LinearLayoutManager(this);
        recycler.setLayoutManager(view);
        progresoAdapter = new ProgresoTareaAdapter(listaTareas);
        recycler.setAdapter(progresoAdapter);
    }

    private void inicializarDatos() {
        listaTareas.add(new ProgresoTarea("Investigación de Mercado", "En progreso", 40, "31-12-2023"));
        listaTareas.add(new ProgresoTarea("Desarrollo de Prototipo", "Pendiente", 0, "30-06-2024"));
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