package com.dpozzo68.analizarapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    public void login (View view){
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
    }
    public void olvideContrasena(View view){
        Intent intent = new Intent(this, olvidaste_contrasena.class);
        startActivity(intent);
    }
}