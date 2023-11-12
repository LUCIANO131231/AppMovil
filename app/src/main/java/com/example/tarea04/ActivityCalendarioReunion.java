    package com.example.tarea04;

    import android.app.TimePickerDialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CalendarView;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.ActionBar;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import org.w3c.dom.Text;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;
    import java.util.Locale;
    import java.util.SimpleTimeZone;

    public class ActivityCalendarioReunion extends AppCompatActivity {
        CalendarView calendarView;
        EditText etHoraInicio, etHoraFin, etUbicacion;
        Button btnProgramarReunion;
        /*RecyclerView recycler;
        RecyclerView.LayoutManager view;
        CalendarioAdapter calendarioAdapter;*/
        List<Calendario> listaReuniones;
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar fechaReunion = Calendar.getInstance();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendario_reunion);

            Toolbar myToolbar = findViewById(R.id.toolbarCalendario);
            myToolbar.setTitle("");
            setSupportActionBar(myToolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            listaReuniones = new ArrayList<>();

            /*recycler = findViewById(R.id.rvReunionesProgramadas);
            view = new LinearLayoutManager(this);
            recycler.setLayoutManager(view);
            calendarioAdapter = new CalendarioAdapter(listaReuniones);
            recycler.setAdapter(calendarioAdapter);*/

            calendarView = findViewById(R.id.calendarView);
            etHoraInicio = findViewById(R.id.etHoraInicio);
            etHoraFin = findViewById(R.id.etHoraFin);
            etUbicacion = findViewById(R.id.etUbicacion);
            btnProgramarReunion = findViewById(R.id.btnProgramarReunion);

            calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                fechaReunion.set(year, month, dayOfMonth);
            });

            etHoraInicio.setOnClickListener(v -> mostrarTimePicker(etHoraInicio));
            etHoraFin.setOnClickListener(v -> mostrarTimePicker(etHoraFin));

            btnProgramarReunion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String horaInicio = etHoraInicio.getText().toString();
                        String horaFin = etHoraFin.getText().toString();
                        String ubicacion = etUbicacion.getText().toString();

                        if (!horaInicio.isEmpty() && !horaFin.isEmpty() && !ubicacion.isEmpty()) {
                            Date horaInicioDate = sdfHora.parse(horaInicio);
                            Date horaFinDate = sdfHora.parse(horaFin);
                            Calendar horaInicioCal = Calendar.getInstance();
                            Calendar horaFinCal = Calendar.getInstance();
                            if (horaInicioDate != null) horaInicioCal.setTime(horaInicioDate);
                            if (horaFinDate != null) horaFinCal.setTime(horaFinDate);

                            Calendario nuevaReunion = new Calendario(fechaReunion.getTime(),
                                    sdfHora.format(horaInicioCal.getTime()),
                                    sdfHora.format(horaFinCal.getTime()),
                                    ubicacion);
                            listaReuniones.add(nuevaReunion);

                            /*calendarioAdapter.notifyItemInserted(listaReuniones.size() - 1);*/
                            enviarDatosReunion(nuevaReunion);
                            limpiarCampos();
                        } else {
                            Toast.makeText(ActivityCalendarioReunion.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        private void mostrarTimePicker(final EditText etHora) {
            int hora = fechaReunion.get(Calendar.HOUR_OF_DAY);
            int minutos = fechaReunion.get(Calendar.MINUTE);
            new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> {
                etHora.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
            }, hora, minutos, true).show();
        }
        private void enviarDatosReunion(Calendario calendario){
            Intent intent = new Intent(ActivityCalendarioReunion.this, MostrarDatosCalendario.class);
            intent.putExtra("horaInicio", calendario.getHoraInicio());
            intent.putExtra("horaFin", calendario.getHoraFin());
            intent.putExtra("ubicacion", calendario.getUbicacion());
            startActivity(intent);
        }
        private void limpiarCampos(){
            etHoraInicio.getText().clear();
            etHoraFin.getText().clear();
            etUbicacion.getText().clear();
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