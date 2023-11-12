package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    List<Tarea> listaDeTareas;

    public class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTareaTitulo, tvTareaDescripcion, tvTareaFechaVencimiento, tvTareaPrioridad, tvTareaMiembrosEquipo;
        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTareaTitulo = itemView.findViewById(R.id.tvTareaTitulo);
            tvTareaDescripcion = itemView.findViewById(R.id.tvTareaDescripcion);
            tvTareaFechaVencimiento = itemView.findViewById(R.id.tvTareaFechaVencimiento);
            tvTareaPrioridad = itemView.findViewById(R.id.tvTareaPrioridad);
            tvTareaMiembrosEquipo = itemView.findViewById(R.id.tvTareaMiembrosEquipo);
        }
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    public TareasAdapter(List<Tarea> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }
    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.TareaViewHolder holder, int position) {
        Tarea tarea = listaDeTareas.get(position);
        holder.tvTareaTitulo.setText(tarea.getTitulo());
        holder.tvTareaDescripcion.setText(tarea.getDescripcion());
        holder.tvTareaFechaVencimiento.setText(tarea.getFechaVencimiento());
        holder.tvTareaPrioridad.setText(tarea.getPrioridad());
        holder.tvTareaMiembrosEquipo.setText(String.join(", ", tarea.getMiembrosEquipo()));
    }

    @Override
    public int getItemCount() {
        return listaDeTareas.size();
    }
}