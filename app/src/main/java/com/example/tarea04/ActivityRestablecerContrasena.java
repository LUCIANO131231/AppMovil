package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRestablecerContrasena extends AppCompatActivity {

    EditText etEmail;
    Button btRestablecerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_contrasena);

        etEmail = findViewById(R.id.etEmail);
        btRestablecerPassword = findViewById(R.id.btRestablecerPassword);

        btRestablecerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    resetPassword(email);
                } else {
                    etEmail.setError("Ingrese su correo electrónico.");
                }
            }
        });
    }

    private void resetPassword(String email) {
        Toast.makeText(this, "Instrucciones enviadas a " + email, Toast.LENGTH_SHORT).show();
    }
}
