package com.example.tarea04;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;
import java.util.Locale;

public class ActivityCalendarioReunion extends AppCompatActivity {
    CalendarView calendarView;
    Calendar fechaReunion = Calendar.getInstance();
    FloatingActionButton btnProgramarReunion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_reunion);

        Toolbar myToolbar = findViewById(R.id.toolbarCalendario);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnProgramarReunion = findViewById(R.id.btnAgregarEvento);
        calendarView = findViewById(R.id.calendarView);

        btnProgramarReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                    fechaReunion.set(year, month, dayOfMonth);
                });
                int hora = fechaReunion.get(Calendar.HOUR_OF_DAY);
                int minutos = fechaReunion.get(Calendar.MINUTE);
                new TimePickerDialog(ActivityCalendarioReunion.this, (timePicker, selectedHour, selectedMinute) -> {
                    // Establecer la hora seleccionada en un formato específico
                    String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                    // Abre la aplicación de Google Calendar con los datos necesarios
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Reunión importante")
                            .putExtra(CalendarContract.Events.DESCRIPTION, "Discusión sobre el próximo proyecto")
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, fechaReunion.getTimeInMillis())
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fechaReunion.getTimeInMillis() + 3600000)
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, " - " + horaSeleccionada);
                    startActivity(intent);
                }, hora, minutos, true).show();
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
