package com.dpozzo68.analizarapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.entidades.Usuario;
import com.google.firebase.auth.FirebaseAuth;

public class Mi_Cuenta extends AppCompatActivity {
    //creo variable usuario
    public Usuario usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        //modificar y asignar los datos de usuario Global a los textviews
        TextView email = findViewById(R.id.mailUsuario3);
        TextView nombreCompleto = findViewById(R.id.nombreUsuario);
        email.setText(this.usuario.getEmail());
        nombreCompleto.setText(this.usuario.getNombre() + " " + this.usuario.getApellido());
    }

    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void irConsumos(View view) {
        ImageView imagen = findViewById(R.id.imagen_home);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
    }

    public void irAlarmas(View view) {
        ImageView imagen = findViewById(R.id.imagen_alerta);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisAlarmas1.class);
        startActivity(intent);
    }

    public void irPerfil(View view) {
        ImageView imagen = findViewById(R.id.imagen_perfil);
        imagen.setClickable(true);
        Intent intent = new Intent(this, Mi_Cuenta.class);
        startActivity(intent);
    }

    public void irContacto(View view) {
        ImageView imagen = findViewById(R.id.imagen_contacto);
        imagen.setClickable(true);
        Intent intent = new Intent(this, ContactoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
        finish();
    }
}