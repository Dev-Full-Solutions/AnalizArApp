package com.dpozzo68.analizarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ingresar(View view) {
        Intent intent = new Intent(this, Onboarding1.class);
        startActivity(intent);
    }

//    public void testeo(View view){
//        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
//        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                String id = String.valueOf(item.getItemId());
//                if(R.id.home == item.getItemId()){
//                    Intent intent = new Intent(String.valueOf(MisConsumos.class));
//                    startActivity(intent);
//                    return true;
//                } else if (R.id.perfil == item.getItemId()) {
//                    Intent intent = new Intent(String.valueOf(Mi_Cuenta.class));
//                    startActivity(intent);
//                    return true;
//                } else if (R.id.alerta == item.getItemId()) {
//                    Intent intent = new Intent(String.valueOf(MisAlarmas1.class));
//                    startActivity(intent);
//                    return true;
//                } else if (R.id.contacto == item.getItemId()) {
//                    Intent intent = new Intent(String.valueOf(ContactoActivity.class));
//                    startActivity(intent);
//                    return true;
//                }else {
//                    return false;
//                }
//            }
//        });
//    }
}