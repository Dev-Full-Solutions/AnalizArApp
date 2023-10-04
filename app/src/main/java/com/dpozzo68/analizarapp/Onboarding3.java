package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Onboarding3 extends AppCompatActivity {

    Button btnAtras;
    Button btnSeguir;
    // Declaro las variables btnAtras y btnSeguir indicando que es de tipo Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding3);

//        btnAtras = findViewById(R.id.button6);
//        btnAtras.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Onboarding3.this, Onboarding2.class);
//                startActivity(intent);
//            }
//        });
//
//        btnSeguir = findViewById(R.id.button5);
//        btnSeguir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Onboarding3.this, login.class);
//                startActivity(intent);
//            }
//        });
    }
    // Código para volver a la activity Onboarding2 o seguir a la activity Login.

    public void atras (View view){
        Intent intent = new Intent(this, Onboarding2.class);
        startActivity(intent);
    }

    public void seguir (View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}