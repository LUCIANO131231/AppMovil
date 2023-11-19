package com.example.tarea04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    TextView tvNavNombre, tvNavEmail;
    FirebaseFirestore db;
    FirebaseUser usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        View headerView = navigationView.getHeaderView(0);
        tvNavNombre = headerView.findViewById(R.id.tvNombre);
        tvNavEmail = headerView.findViewById(R.id.tvCorreo);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        ImageView btnOpenDrawer = findViewById(R.id.btnNav);
        btnOpenDrawer.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Asignar Tareas":
                        Intent asignar = new Intent(MainActivity.this, ActivityAsignarTarea.class);
                        startActivity(asignar);
                        return true;
                    case "Seguimiento del Proyecto":
                        Intent seguimiento = new Intent(MainActivity.this, ActivitySeguimientoProgresoTareas.class);
                        startActivity(seguimiento);
                        return true;
                    case "Calendario de Reuniones":
                        Intent calendario = new Intent(MainActivity.this, ActivityCalendarioReunion.class);
                        startActivity(calendario);
                        return true;
                    case "Compartir Archivos":
                        Intent archivo = new Intent(MainActivity.this, ActivityCompartirArchivos.class);
                        startActivity(archivo);
                        return true;
                    case "Cerrar Sesión":
                        FirebaseAuth.getInstance().signOut();
                        Intent cerrar = new Intent(MainActivity.this, ActivityIniciarSesion.class);
                        startActivity(cerrar);
                        finish();
                        return true;
                }
                return false;
            }
        });

        usuario = mAuth.getCurrentUser();
        if (usuario != null) {
            String usuarioId = usuario.getUid();

            db.collection("UsuariosRegistrados")//Extraer datos almacenados
              .document(usuarioId)
              .get()
            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
               @Override
               public void onSuccess(DocumentSnapshot document) {
                  if (document.exists()) {
                       String nombre = document.getString("Nombre");
                       String apellido = document.getString("Apellido");
                       tvNavNombre.setText(nombre + " " + apellido);
                       tvNavEmail.setText(usuario.getEmail());
                      }
                  }
              })
            .addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(MainActivity.this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
               }
            });
        }
    }
}