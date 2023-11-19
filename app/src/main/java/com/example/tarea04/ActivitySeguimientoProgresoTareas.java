package com.example.tarea04;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeguimientoProgresoTareas extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerView.Adapter miAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Tarea> listaSeguimientoTareas;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_progreso_tareas);

        Toolbar myToolbar = findViewById(R.id.toolbarSeguimiento);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();
        listaSeguimientoTareas = new ArrayList<>();

        recycler = findViewById(R.id.rvSeguimientoTareas);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        miAdapter = new TareasAdapter(listaSeguimientoTareas);
        recycler.setAdapter(miAdapter);
        /*recycler.setVisibility(View.GONE);*/


        db.collection("Tareas")
          .get()
          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){
              listaSeguimientoTareas.clear();
                for (DocumentSnapshot document: task.getResult()){
                    String titulo = document.getString("titulo");
                    String descripcion = document.getString("descripcion");
                    String fechaVencimiento = document.getString("fechaVencimiento");
                    List<String> miembrosEquipo = (List<String>) document.get("miembrosEquipo");
                    Tarea tarea = new Tarea(titulo, descripcion, fechaVencimiento, miembrosEquipo);
                    if (tarea != null) {
                        listaSeguimientoTareas.add(tarea);
                    }
                }
                miAdapter.notifyDataSetChanged();
            }
        }
      });
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