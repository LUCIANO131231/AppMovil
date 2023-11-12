package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgresoTareaAdapter extends RecyclerView.Adapter<ProgresoTareaAdapter.ProgresoTareaViewHolder> {
    List<ProgresoTarea> listaProgresoTareas;

    public class ProgresoTareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTareaTitulo, tvTareaEstado, tvTareaPorcentaje, tvTareaFechaVencimiento, tvTareaComentarios;
        ProgressBar barraProgreso;
        public ProgresoTareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTareaTitulo = itemView.findViewById(R.id.tvTareaTitulo);
            tvTareaEstado = itemView.findViewById(R.id.tvTareaEstado);
            tvTareaPorcentaje = itemView.findViewById(R.id.tvTareaPorcentaje);
            tvTareaFechaVencimiento = itemView.findViewById(R.id.tvTareaFechaVencimiento);
            tvTareaComentarios = itemView.findViewById(R.id.tvTareaComentarios);
            barraProgreso = itemView.findViewById(R.id.pbTareaProgreso);
        }
    }

    @NonNull
    @Override
    public ProgresoTareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea_progreso, parent, false);
        return new ProgresoTareaViewHolder(view);
    }

    public ProgresoTareaAdapter(List<ProgresoTarea> listaProgresoTareas) {
        this.listaProgresoTareas = listaProgresoTareas;
    }
    @Override
    public void onBindViewHolder(@NonNull ProgresoTareaAdapter.ProgresoTareaViewHolder holder, int position) {
        ProgresoTarea progresoTarea = listaProgresoTareas.get(position);
        holder.tvTareaTitulo.setText(progresoTarea.getTitulo());
        holder.tvTareaEstado.setText(progresoTarea.getEstado());
        holder.barraProgreso.setProgress(progresoTarea.getPorcentajeAvance());
        holder.tvTareaPorcentaje.setText(String.format("%d%%", progresoTarea.getPorcentajeAvance()));
        holder.tvTareaFechaVencimiento.setText(progresoTarea.getFechaVencimiento());
    }

    @Override
    public int getItemCount() {
        return listaProgresoTareas.size();
    }
}