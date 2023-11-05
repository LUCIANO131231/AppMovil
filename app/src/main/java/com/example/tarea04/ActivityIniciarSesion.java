package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityIniciarSesion extends AppCompatActivity {

    TextView tvRestablecerPassword;
    EditText etEmail, etPassword;
    Button btLogin, btRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        tvRestablecerPassword = findViewById(R.id.tvRestablecerPassword);

        tvRestablecerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityIniciarSesion.this, ActivityRestablecerContrasena.class));
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Ingrese su correo por favor!");
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("No olvide su contraseña por favor!");
                    return;
                }
                Toast.makeText(ActivityIniciarSesion.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityIniciarSesion.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityIniciarSesion.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });

    }
}