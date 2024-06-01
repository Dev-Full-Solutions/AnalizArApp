package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.dpozzo68.analizarapp.helpers.NotificationService;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NotificationService(this);
    }
    public void ingresar(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void iniciarTour(View view) {
        Intent intent = new Intent(this, Onboarding1.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de AnalizArApp?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
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