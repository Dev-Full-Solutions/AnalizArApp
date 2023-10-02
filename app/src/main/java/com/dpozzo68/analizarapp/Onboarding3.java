package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Onboarding3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding3);
    }

    public void atras (View view){
        Intent intent = new Intent(this, Onboarding2.class);
        startActivity(intent);
    }

    public void seguir (View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}