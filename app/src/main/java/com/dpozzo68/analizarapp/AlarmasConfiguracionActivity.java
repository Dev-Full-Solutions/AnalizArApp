package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AlarmasConfiguracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas_configuracion);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MisAlarmas1.class);
        startActivity(intent);
        finish();
    }
}