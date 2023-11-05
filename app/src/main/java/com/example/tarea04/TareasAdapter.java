package com.example.tarea04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<String> listaDeTareas;

    public TareasAdapter(List<String> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        String tarea = listaDeTareas.get(position);
        holder.tvTituloTarea.setText(tarea);
    }

    @Override
    public int getItemCount() {
        return listaDeTareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTituloTarea;

        public TareaViewHolder(View view) {
            super(view);
            tvTituloTarea = view.findViewById(R.id.tvTituloTarea);
        }
    }
}


