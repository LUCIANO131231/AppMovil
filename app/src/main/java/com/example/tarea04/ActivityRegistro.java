package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegistro extends AppCompatActivity {
    EditText etEmail, etPassword, etName, etLastName;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        buttonRegister = findViewById(R.id.btRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || lastName.isEmpty()) {
                    Toast.makeText(ActivityRegistro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityRegistro.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityRegistro.this, ActivityIniciarSesion.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}