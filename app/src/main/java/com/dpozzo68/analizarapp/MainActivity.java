package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSiguiente;
    // Declaro la variable btnSiguiente indicando que es de tipo Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = findViewById(R.id.button3);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MisConsumos.this, ContactoActivity.class);
                startActivity(intent);
            }
        });
    }
    // Código para ir a la activity Contacto.
}