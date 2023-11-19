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
    public TareasAdapter(List<Tarea> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }
    public class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTareaTitulo, tvTareaDescripcion, tvTareaFechaVencimiento, tvTareaMiembrosEquipo;
        public TareaViewHolder(@NonNull View v) {
            super(v);
            tvTareaTitulo = itemView.findViewById(R.id.tvTareaTitulo);
            tvTareaDescripcion = itemView.findViewById(R.id.tvTareaDescripcion);
            tvTareaFechaVencimiento = itemView.findViewById(R.id.tvTareaFechaVencimiento);
            tvTareaMiembrosEquipo = itemView.findViewById(R.id.tvTareaMiembrosEquipo);
        }
    }
    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        TareaViewHolder vh = new TareaViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.TareaViewHolder holder, int position) {
        Tarea tarea = listaDeTareas.get(position);
        holder.tvTareaTitulo.setText("Tarea: "+tarea.getTitulo());
        holder.tvTareaDescripcion.setText("Descripción: "+tarea.getDescripcion());
        holder.tvTareaFechaVencimiento.setText("Fecha vencimiento: "+tarea.getFechaVencimiento());
        holder.tvTareaMiembrosEquipo.setText("Miembros: "+String.join(", ", tarea.getMiembrosEquipo()));
    }
    @Override
    public int getItemCount() {
        return listaDeTareas.size();
    }
}