package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.List;

public class CompartirArchivoAdapter extends RecyclerView.Adapter<CompartirArchivoAdapter.CompartirArchivoViewHolder> {
    List<CompartirArchivo> listaDeArchivos;
    public CompartirArchivoAdapter(List<CompartirArchivo> listaDeArchivos) {
        this.listaDeArchivos = listaDeArchivos;
    }
    public class CompartirArchivoViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsuario, tvAchivo;

        public CompartirArchivoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvAchivo = itemView.findViewById(R.id.tvNombreArchivo);
        }
    }
    @NonNull
    @Override
    public CompartirArchivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compartir_archivo, parent, false);
        CompartirArchivoViewHolder vh = new CompartirArchivoViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull CompartirArchivoViewHolder holder, int position) {
        CompartirArchivo compartirArchivo = listaDeArchivos.get(position);
        holder.tvAchivo.setText(compartirArchivo.getNombre());
        holder.tvUsuario.setText("Subido por: ");
    }
    @Override
    public int getItemCount() {
        return listaDeArchivos.size();
    }
}