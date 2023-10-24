package com.dpozzo68.analizarapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
<<<<<<< HEAD
import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.helpers.UsuarioServicio;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;
=======

>>>>>>> parent of 7e1219d (Merge branch 'FeatureMisConsumosMisAlarmas' into develop)
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    EditText usuarioText;
    EditText passwordText;
    private FirebaseAuth mAuth;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioText = findViewById(R.id.inputUsuario);
        passwordText = findViewById(R.id.inputPassword);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        buttonLogin = findViewById(R.id.loginButton);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singIn();
            }
        });
    }

    public void singIn(){
        String email = usuarioText.getText().toString();
        String password = passwordText.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){
            if(email.contains("@") && email.contains(".") && password.length() > 5){
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    showHome(task.getResult().getUser().getEmail());
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(login.this, "Se ha producido un error al autenticar el usuario. Usuario inexistente y/o contraseña incorrecta",
                                            Toast.LENGTH_SHORT).show();
//                                    showAlert("Se ha producido un error al autenticar el usuario");

                                }
                            }

                        });
            }else {
                Toast.makeText(login.this, "Usuario y/o contraseña inválidos",
                        Toast.LENGTH_SHORT).show();
//                showAlert("Usuario y/o contraseña inválidos");
            }

        }else {
            Toast.makeText(login.this, "Hay campos vacíos en el formulario",
                    Toast.LENGTH_SHORT).show();
//            showAlert("Hay campos vacíos en el formulario");
        }

    }
    private void showAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(msg);
        builder.setPositiveButton("Aceptar",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHome(String email) {
        Intent homeIntent = new Intent(this, Video.class);
        homeIntent.putExtra("email",email);
        startActivity(homeIntent);
    }
    public void olvideContrasena(View view){
        Intent intent = new Intent(this, olvidaste_contrasena.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Onboarding3.class);
        startActivity(intent);
        finish();
    }
}