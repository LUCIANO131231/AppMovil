package com.example.tarea04;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityAsignarTarea extends AppCompatActivity {
    EditText etTareaTitulo, etTareaDescripcion, etTareaFechaVencimiento;
    Button btnAsignarTarea;
    Button btnArchivo;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter miAdapter;
    List<Tarea> listaTareas;
    List<String> miembrosEquipo;
    MultiAutoCompleteTextView CompletarMiembrosEquipo;
    FirebaseFirestore db;
    int PICK_FILE_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tarea);

        Toolbar myToolbar = findViewById(R.id.toolbarAsignarTarea);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();

        listaTareas = new ArrayList<>();
        miembrosEquipo = new ArrayList<>();

        recycler = findViewById(R.id.rvTareasAsignadas);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        miAdapter = new TareasAdapter(listaTareas);
        recycler.setAdapter(miAdapter);
        /*recycler.setVisibility(View.GONE);*/

        CompletarMiembrosEquipo = findViewById(R.id.AutocompletarMiembros);
        CompletarMiembrosEquipo.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //ArrayAdapter para el Spinner y el MultiAutoCompleteTextView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, miembrosEquipo);
        CompletarMiembrosEquipo.setAdapter(arrayAdapter);

        etTareaTitulo = findViewById(R.id.etTareaTitulo);
        etTareaDescripcion = findViewById(R.id.etTareaDescripcion);
        etTareaFechaVencimiento = findViewById(R.id.etTareaFechaVencimiento);
        btnAsignarTarea = findViewById(R.id.btnAsignarTarea);
        btnArchivo = findViewById(R.id.btnCargarArchivo);

        etTareaFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAsignarTarea.this, (view, year, month, dayOfMonth) -> {
                            String fechaSeleccionada = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
                            etTareaFechaVencimiento.setText(fechaSeleccionada);
                        }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnAsignarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTareaTitulo.getText().toString().trim();
                String descripcion = etTareaDescripcion.getText().toString().trim();
                String fechaVencimientoStr = etTareaFechaVencimiento.getText().toString().trim();

                String[] miembrosSeleccionados = CompletarMiembrosEquipo.getText().toString().split(", ");
                List<String> listaMiembrosSeleccionados = Arrays.asList(miembrosSeleccionados);

                if (titulo.isEmpty() || descripcion.isEmpty() || fechaVencimientoStr.isEmpty() || listaMiembrosSeleccionados.isEmpty()) {
                    Toast.makeText(ActivityAsignarTarea.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {

                    Map<String, Object> nuevaTarea = new HashMap<>();
                    nuevaTarea.put("titulo", etTareaTitulo.getText().toString().trim());
                    nuevaTarea.put("descripcion", etTareaDescripcion.getText().toString().trim());
                    nuevaTarea.put("fechaVencimiento", etTareaFechaVencimiento.getText().toString().trim());
                    nuevaTarea.put("miembrosEquipo", Arrays.asList(CompletarMiembrosEquipo.getText().toString().split(", ")));

                    db.collection("Tareas")
                      .add(nuevaTarea)
                      .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                          miAdapter.notifyItemInserted(listaTareas.size() - 1);

                          etTareaTitulo.getText().clear();
                          etTareaDescripcion.getText().clear();
                          etTareaFechaVencimiento.getText().clear();
                          CompletarMiembrosEquipo.getText().clear();
                          Toast.makeText(ActivityAsignarTarea.this, "Tarea asignada", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(ActivityAsignarTarea.this, ActivitySeguimientoProgresoTareas.class);
                          startActivity(intent);
                          }
                        })
                      .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          Toast.makeText(ActivityAsignarTarea.this, "Error al agregar la tarea", Toast.LENGTH_SHORT).show();
                          }
                    });
                }
            }
        });

        db.collection("UsuariosRegistrados")
          .get()
          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
              if (task.isSuccessful()) {
                  for (DocumentSnapshot documentSnapshot : task.getResult()) {
                      String nombre = documentSnapshot.getString("Nombre");
                      String apellido = documentSnapshot.getString("Apellido");
                        if (nombre != null && apellido != null) {
                           miembrosEquipo.add(nombre + " " + apellido);
                        }
                  }
              }
            }
          });

        btnArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
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