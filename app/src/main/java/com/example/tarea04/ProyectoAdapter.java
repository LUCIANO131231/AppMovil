package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ProyectoViewHolder> {

    List<Proyecto> listaDeProyectos;

    public class ProyectoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion, tvEstado, tvNotas;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvNotas = itemView.findViewById(R.id.tvNotas);
        }
    }

    @NonNull
    @Override
    public ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto, parent, false);
        return new ProyectoViewHolder(view);
    }

    public ProyectoAdapter(List<Proyecto> listaDeProyectos) {
        this.listaDeProyectos = listaDeProyectos;
    }
    @Override
    public void onBindViewHolder(@NonNull ProyectoViewHolder holder, int position) {
        Proyecto proyecto = listaDeProyectos.get(position);
        holder.tvTitulo.setText(proyecto.getTitulo());
        holder.tvDescripcion.setText(proyecto.getDescripcion());
        holder.tvEstado.setText(proyecto.getEstado());
        holder.tvNotas.setText(proyecto.getNotas());
    }

    @Override
    public int getItemCount() {
        return listaDeProyectos.size();
    }
}

