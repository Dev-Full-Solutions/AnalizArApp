package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.entidades.Usuario;

public class Mi_Cuenta extends AppCompatActivity {

    public Usuario usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        TextView textoEmailUsuario = findViewById(R.id.mailUsuario3);
        TextView textoNombreYApellido = findViewById(R.id.nombreUsuario);
        textoEmailUsuario.setText(usuario.getEmail());
        textoNombreYApellido.setText(usuario.getNombre() + " " + usuario.getApellido());
    }

    public void cerrarSesion(View view){
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


}