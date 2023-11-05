package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMiembros extends RecyclerView.Adapter<AdapterMiembros.MiembroViewHolder> {

    private List<String> listaDeMiembros;

    public AdapterMiembros(List<String> listaDeMiembros) {
        this.listaDeMiembros = listaDeMiembros;
    }

    @NonNull
    @Override
    public MiembroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_miembro, parent, false);
        return new MiembroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MiembroViewHolder holder, int position) {
        String miembro = listaDeMiembros.get(position);
        holder.tvNombreMiembro.setText(miembro);
    }

    @Override
    public int getItemCount() {
        return listaDeMiembros.size();
    }

    static class MiembroViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreMiembro;

        MiembroViewHolder(View view) {
            super(view);
            tvNombreMiembro = view.findViewById(R.id.tvMiembro);
        }
    }
}

