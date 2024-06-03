package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MedioAmbiente extends AppCompatActivity {

    TextView backTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio_ambiente);

        //Codigo para volver a la activity OnBoarding3.

        backTour=(TextView)findViewById(R.id.backTour);

        backTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MedioAmbiente.this, Onboarding3.class);
                startActivity(i);
            }
        });
    }
}