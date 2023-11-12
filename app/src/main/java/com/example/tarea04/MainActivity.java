package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.myToolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_crear_proyectos) {
            startActivity(new Intent(this, ActivityCrearProyectos.class));
            return true;
        }

        if (item.getItemId() == R.id.nav_asignar_tareas) {
            Intent intent = new Intent(MainActivity.this, ActivityAsignarTarea.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.nav_seguimiento) {
            Intent intent = new Intent(MainActivity.this, ActivitySeguimientoProgresoTareas.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.nav_calendario_reuniones) {
            Intent intent = new Intent(MainActivity.this, ActivityCalendarioReunion.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
