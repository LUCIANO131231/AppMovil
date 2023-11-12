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
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import androidx.appcompat.app.ActionBar;

public class ActivityAsignarTarea extends AppCompatActivity {
    EditText etTareaTitulo, etTareaDescripcion, etTareaFechaVencimiento;
    Button btnAsignarTarea;
    Spinner spinnerPrioridad;
    RecyclerView recycler;
    RecyclerView.LayoutManager view;
    TareasAdapter tareaAdapter;
    List<Tarea> listaTareas;
    static List<String> miembrosEquipo = Arrays.asList("Renzo Paolo", "Bad Bunny", "Manuel Garcia", "Ricardo Morán");
    MultiAutoCompleteTextView multiAutoCompleteMiembrosEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tarea);

        Toolbar myToolbar = findViewById(R.id.toolbarAsignarTarea);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listaTareas = new ArrayList<>();

        /*recycler = findViewById(R.id.rvTareasAsignadas);
        view = new LinearLayoutManager(this);
        recycler.setLayoutManager(view);
        tareaAdapter = new TareasAdapter(listaTareas);
        recycler.setAdapter(tareaAdapter);*/


        etTareaTitulo = findViewById(R.id.etTareaTitulo);
        etTareaDescripcion = findViewById(R.id.etTareaDescripcion);
        etTareaFechaVencimiento = findViewById(R.id.etTareaFechaVencimiento);
        btnAsignarTarea = findViewById(R.id.btnAsignarTarea);
        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        multiAutoCompleteMiembrosEquipo = findViewById(R.id.multiAutoCompleteMiembrosEquipo);
        //Spinner que permite al usuario seleccionar un elemento de una lista.
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, miembrosEquipo);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //MultiAutoCompleteTextView que permite el usuario seleccione múltiples elementos de una lista mientras escribe
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, miembrosEquipo);
        multiAutoCompleteMiembrosEquipo.setAdapter(adapter);
        multiAutoCompleteMiembrosEquipo.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        etTareaFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAsignarTarea.this,
                        (view, year, month, dayOfMonth) -> {
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
                String fechaVencimiento = etTareaFechaVencimiento.getText().toString().trim();
                String prioridad = spinnerPrioridad.getSelectedItem().toString();

                String[] miembrosSeleccionados = multiAutoCompleteMiembrosEquipo.getText().toString().split(", ");
                List<String> listaMiembrosSeleccionados = Arrays.asList(miembrosSeleccionados);

                if (titulo.isEmpty() || descripcion.isEmpty() || fechaVencimiento.isEmpty() || listaMiembrosSeleccionados.isEmpty()) {
                    Toast.makeText(ActivityAsignarTarea.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, prioridad, listaMiembrosSeleccionados);
                    listaTareas.add(nuevaTarea);
                    /*tareaAdapter.notifyItemInserted(listaTareas.size() - 1);*/
                    enviarDatosTarea(nuevaTarea);
                    limpiarCampos();
                }
            }
        });
    }
    private void enviarDatosTarea(Tarea tarea){
        Intent intent = new Intent(ActivityAsignarTarea.this, MostrarDatosTarea.class);
        intent.putExtra("titulo", tarea.getTitulo());
        intent.putExtra("descripcion", tarea.getDescripcion());
        intent.putExtra("fechaVencimiento", tarea.getFechaVencimiento());
        intent.putExtra("prioridad", tarea.getPrioridad());
        intent.putStringArrayListExtra("miembrosEquipo", new ArrayList<>(tarea.getMiembrosEquipo()));
        startActivity(intent);
    }

    private void limpiarCampos() {
        etTareaTitulo.getText().clear();
        etTareaDescripcion.getText().clear();
        etTareaFechaVencimiento.getText().clear();
        spinnerPrioridad.setSelection(0);
        multiAutoCompleteMiembrosEquipo.getText().clear();
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