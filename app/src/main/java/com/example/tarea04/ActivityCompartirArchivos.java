package com.example.tarea04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ActivityCompartirArchivos extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerView.Adapter miAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<CompartirArchivo> listaArchivos;
    FloatingActionButton btnArchivo;
    FirebaseStorage storage;
    StorageReference storageReference;
    int PICK_FILE_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir_archivos);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Toolbar myToolbar = findViewById(R.id.toolbarCompartirArchivo);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaArchivos = new ArrayList<>();

        recycler = findViewById(R.id.rvArchivos);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        miAdapter = new CompartirArchivoAdapter(listaArchivos);
        recycler.setAdapter(miAdapter);

        btnArchivo = findViewById(R.id.btnCompartir);

        btnArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                //Seleccionar archivos
                Uri seleccionarArchivo = data.getData();
                //Obtener nombre del archiv
                String nombreArchivo;

                if (seleccionarArchivo.getScheme().equals("content")) {
                    try (Cursor cursor = getContentResolver().query(seleccionarArchivo, null, null, null, null)) {
                        if (cursor != null && cursor.moveToFirst()) {
                            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                            nombreArchivo = cursor.getString(nameIndex);
                        } else {
                            nombreArchivo = "Archivo sin nombre";
                        }
                    }
                } else {
                    nombreArchivo = seleccionarArchivo.getLastPathSegment();
                }

                storageReference = FirebaseStorage.getInstance().getReference();
                storageReference.child("CarpetasArchivos/" + nombreArchivo)
                        .putFile(seleccionarArchivo)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               Toast.makeText(ActivityCompartirArchivos.this, "Archivo subido", Toast.LENGTH_SHORT).show();
                           }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ActivityCompartirArchivos.this, "Error al subir", Toast.LENGTH_SHORT).show();
                            }
                        });
                //Agregar el nuevo archivo a la lista
                listaArchivos.add(new CompartirArchivo(nombreArchivo, seleccionarArchivo));
                //notificar al adaptador de un nuevo elemento
                miAdapter.notifyItemInserted(listaArchivos.size() - 1);
                Toast.makeText(ActivityCompartirArchivos.this, "Archivo seleccionado: " + nombreArchivo, Toast.LENGTH_SHORT).show();
            }
        }
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