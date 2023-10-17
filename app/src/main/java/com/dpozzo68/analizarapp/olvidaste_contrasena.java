package com.dpozzo68.analizarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class olvidaste_contrasena extends AppCompatActivity {

    EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidaste_contrasena);

        emailText = findViewById(R.id.inputEmail);
        Button botonResetPassword = findViewById(R.id.olvidasteContrasenaButton);

        botonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    public void resetPassword(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = emailText.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(olvidaste_contrasena.this, "Email enviado exitosamente!",
                                    Toast.LENGTH_SHORT).show();
                            showLogin();
                        }else{
                            Toast.makeText(olvidaste_contrasena.this, "Email no registrado!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showLogin(){
        finish();
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}