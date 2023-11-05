package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ActivityAsignarTarea extends AppCompatActivity {
    EditText etTitulo, etDescripcion, etFechaInicio, etFechaFinal;
    Spinner spPrioridad;
    Button btnAsignar;
    RecyclerView rvTareas;
    private TareasAdapter tareasAdapter;
    List<String> listaDeTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tarea);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        spPrioridad = findViewById(R.id.spPrioridad);
        btnAsignar = findViewById(R.id.btnAsignar);
        etFechaInicio = findViewById(R.id.etFechaInicio);
        etFechaFinal = findViewById(R.id.etFechaFinal);

        etFechaInicio.setOnClickListener(v -> showDatePickerDialog(etFechaInicio));
        etFechaFinal.setOnClickListener(v -> showDatePickerDialog(etFechaFinal));

        listaDeTareas = new ArrayList<>();
        listaDeTareas.add("Tarea 1: Investigación");
        listaDeTareas.add("Tarea 2: Desarrollo");
        listaDeTareas.add("Tarea 3: Pruebas");

        rvTareas = findViewById(R.id.rvTareas);
        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        tareasAdapter = new TareasAdapter(listaDeTareas);
        rvTareas.setAdapter(tareasAdapter);

        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitulo.getText().toString().trim();
                String description = etDescripcion.getText().toString().trim();
                String fechaInicio = etFechaInicio.getText().toString().trim();
                String fechaFinal = etFechaFinal.getText().toString().trim();
                String priority = spPrioridad.getSelectedItem().toString();

                if (title.isEmpty() || description.isEmpty() || fechaInicio.isEmpty() || fechaFinal.isEmpty()) {
                    Toast.makeText(ActivityAsignarTarea.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityAsignarTarea.this, "Tarea asignada correctamente", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });
    }

    private void clearFields() {
        etTitulo.setText("");
        etDescripcion.setText("");
        etFechaInicio.setText("");
        etFechaFinal.setText("");
        spPrioridad.setSelection(0);
    }

    private void showDatePickerDialog(final EditText dateField) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, yearSelected, monthOfYear, dayOfMonth) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, yearSelected);
            dateField.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }
}

