package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Servicios extends AppCompatActivity {

    Button btnAlarmas;
    Button btnIluminacion;
    Button btnAAcondicionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        btnAlarmas = findViewById(R.id.btn_alarmas);
        btnAlarmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Servicios.this, MisAlarmas1.class);
                startActivity(intent);
                finish();
            }
        });

        btnIluminacion = findViewById(R.id.btn_iluminacion);
        btnIluminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Servicios.this, iluminacion.class);
                startActivity(intent);
                finish();
            }
        });

        btnAAcondicionado = findViewById(R.id.btn_aacondicionado);
        btnAAcondicionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Servicios.this, MisAlarmas1.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    public void irAlarmas(View view) {
//        Intent intent = new Intent(this, MisAlarmas1.class);
//        startActivity(intent);
//        finish();
//    }

//    public void irIluminacion(View view) {
//        Intent intent = new Intent(this, MisAlarmas1.class);
//        startActivity(intent);
//        finish();
//    }

//    public void irAAcondicionado(View view) {
//        Intent intent = new Intent(this, MisAlarmas1.class);
//        startActivity(intent);
//        finish();
//    }
    public void irConsumos(View view) {
        ImageView imagen = findViewById(R.id.imagen_home);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
        finish();
    }

    public void irServicios(View view) {
        ImageView imagen = findViewById(R.id.imagen_servicios);
        imagen.setClickable(true);
        Intent intent = new Intent(this, Servicios.class);
        startActivity(intent);
        finish();
    }

    public void irPerfil(View view) {
        ImageView imagen = findViewById(R.id.imagen_perfil);
        imagen.setClickable(true);
        Intent intent = new Intent(this, Mi_Cuenta.class);
        startActivity(intent);
        finish();
    }

    public void irContacto(View view) {
        ImageView imagen = findViewById(R.id.imagen_contacto);
        imagen.setClickable(true);
        Intent intent = new Intent(this, ContactoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
        finish();
    }
}