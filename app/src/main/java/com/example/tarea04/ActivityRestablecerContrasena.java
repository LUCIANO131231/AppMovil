package com.example.tarea04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRestablecerContrasena extends AppCompatActivity {
    EditText etEmail;
    Button btnRestablecer;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_contrasena);

        etEmail = findViewById(R.id.etEmail);
        btnRestablecer = findViewById(R.id.btnRestablecerPass);
        mAuth = FirebaseAuth.getInstance();

        btnRestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                if (email.isEmpty()) {

                mAuth.sendPasswordResetEmail(email)
                     .addOnCompleteListener(ActivityRestablecerContrasena.this, task -> {
                       if (task.isSuccessful()) {
                           Toast.makeText(ActivityRestablecerContrasena.this, "Se ha enviado a " + email + " para restablecer contraseña", Toast.LENGTH_SHORT).show();
                       } else {
                            Toast.makeText(ActivityRestablecerContrasena.this, "Error al enviar correo electrónico", Toast.LENGTH_SHORT).show();
                         }
                     });
                }
            }
        });
    }
}
