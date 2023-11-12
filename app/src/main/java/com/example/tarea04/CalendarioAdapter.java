package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.CalendarioViewHolder> {
    SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    List<Calendario> listaReuniones;

    public class CalendarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvFechaReunion, tvHoraInicio, tvHoraFin, tvUbicacionReunion;
        public CalendarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFechaReunion = itemView.findViewById(R.id.tvFechaReunion);
            tvHoraInicio = itemView.findViewById(R.id.tvHoraInicio);
            tvHoraFin = itemView.findViewById(R.id.tvHoraFin);
            tvUbicacionReunion = itemView.findViewById(R.id.tvUbicacionReunion);
        }
    }

    @NonNull
    @Override
    public CalendarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendario, parent, false);
        return new CalendarioViewHolder(view);
    }
    public CalendarioAdapter(List<Calendario> listaReuniones) {
        this.listaReuniones = listaReuniones;
    }
    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter.CalendarioViewHolder holder, int position) {
        Calendario calendario = listaReuniones.get(position);
        holder.tvFechaReunion.setText(sdfFecha.format(calendario.getFecha()));
        holder.tvHoraInicio.setText(calendario.getHoraInicio());
        holder.tvHoraFin.setText(calendario.getHoraFin());
        holder.tvUbicacionReunion.setText(calendario.getUbicacion());
    }
    @Override
    public int getItemCount() {
        return listaReuniones.size();
    }
}

