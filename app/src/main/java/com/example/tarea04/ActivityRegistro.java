package com.example.tarea04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ActivityRegistro extends AppCompatActivity {
    EditText etEmail, etPassword, etNombres, etApellidos;
    Button btnRegistrar;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        btnRegistrar = findViewById(R.id.btnRegistro);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String nombre = etNombres.getText().toString().trim();
                String apellido = etApellidos.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                    Toast.makeText(ActivityRegistro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(ActivityRegistro.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser usuario = mAuth.getCurrentUser();
                                if (usuario != null) {

                                    String usuarioId = mAuth.getCurrentUser().getUid();
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Nombre", nombre);
                                    user.put("Apellido", apellido);

                                    db.collection("UsuariosRegistrados")
                                      .document(usuarioId)
                                      .set(user)
                                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                                      @Override
                                      public void onSuccess(Void unused) {
                                         Toast.makeText(ActivityRegistro.this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
                                         Intent intent = new Intent(ActivityRegistro.this, MainActivity.class);
                                         startActivity(intent);
                                         finish();
                                         }
                                      })
                                      .addOnFailureListener(new OnFailureListener() {
                                      @Override
                                      public void onFailure(@NonNull Exception e) {
                                          Toast.makeText(ActivityRegistro.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                                          }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}