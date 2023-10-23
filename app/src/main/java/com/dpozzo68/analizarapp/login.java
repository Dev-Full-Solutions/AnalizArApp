package com.dpozzo68.analizarapp;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.dpozzo68.analizarapp.helpers.UsuarioServicio;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;
import com.dpozzo68.analizarapp.entidades.GlobalUsuario;


public class login extends AppCompatActivity {
    public String mailUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    private void showHome(String email) {
        Intent homeIntent = new Intent(this, Video.class);
        homeIntent.putExtra("email",email);
        startActivity(homeIntent);
    }
    public void login (View view){
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);

    }
    public void olvideContrasena(View view){
        Intent intent = new Intent(this, olvidaste_contrasena.class);
        startActivity(intent);
    }
}