package com.example.tarea04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityIniciarSesion extends AppCompatActivity {
    TextView tvRestablecerContraseña;
    EditText etEmail, etPassword;
    Button btnLogin, btnRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistrar);
        tvRestablecerContraseña = findViewById(R.id.tvRestablecer);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(ActivityIniciarSesion.this, "Ambos campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Para verificar si el usuario esta registrados
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(ActivityIniciarSesion.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityIniciarSesion.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityIniciarSesion.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ActivityIniciarSesion.this, "Datos incorrectos. Por favor, inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                            etPassword.setText("");
                        }
                    }
                });
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityIniciarSesion.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });
        tvRestablecerContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityIniciarSesion.this, ActivityRestablecerContrasena.class));
            }
        });
    }
}