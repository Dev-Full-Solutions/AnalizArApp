package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Button;


public class MisAlarmas1 extends AppCompatActivity{

    Switch switch01;
    Switch switch02;
    Switch switch03;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misalarmas1);

        switch01= (Switch) findViewById(R.id.switch1);
        switch02= (Switch) findViewById(R.id.switch2);
        switch03= (Switch) findViewById(R.id.switch3);
        button= (Button) findViewById(R.id.button);

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

    public void onclick (View view) {
        if (view.getId()==R.id.switch1); {
            if (switch01.isChecked()){

            }

            else {
            }
        }
    }
    public void onclick2 (View view) {
        if (view.getId()==R.id.switch2); {
            if (switch02.isChecked()){

            }

            else {
            }
        }
    }
    public void onclick3 (View view) {
        if (view.getId()==R.id.switch3); {
            if (switch03.isChecked()) {

            }

            else {
            }
        }
    }
}
