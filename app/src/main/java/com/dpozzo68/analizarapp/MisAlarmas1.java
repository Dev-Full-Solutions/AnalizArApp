package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Button;
import com.dpozzo68.analizarapp.entidades.Alarma;
import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;
import com.dpozzo68.analizarapp.helpers.AlarmaServicio;
import com.dpozzo68.analizarapp.helpers.AlarmasAdapter;
import java.util.ArrayList;


public class MisAlarmas1 extends AppCompatActivity{

    Switch switch01;
    Button button;
    private RecyclerView recyclerView;
    private SQLiteDatabase alarmasDB;
    private AlarmaServicio alarmaServicio;
    private ArrayList<Alarma> alarmasArray;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misalarmas1);

        alarmasDB = ((AplicacionSQLGlobal) getApplication()).getAlarmasDB();


        alarmaServicio = new AlarmaServicio(alarmasDB);
        //Log.d("alarmaServicio", "llenar db ");
        //alarmaServicio.llenarAlarmasDB();
        alarmasArray = alarmaServicio.getAlarmasFromDB();
        Log.d("alarmaServicio", "recuperar alarmas ");

        recyclerView = findViewById(R.id.recyclerView);
        AlarmasAdapter adapter = new AlarmasAdapter(alarmasArray, alarmasDB, alarmaServicio);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(v -> irAlarmasConfiguracion());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetch the latest data from the database
        alarmasArray = alarmaServicio.getAlarmasFromDB();

        // Update the RecyclerView adapter with the new data
        AlarmasAdapter adapter = new AlarmasAdapter(alarmasArray, alarmasDB, alarmaServicio);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void irAlarmasConfiguracion(){
        Intent intent = new Intent(this, AlarmasConfiguracionActivity.class);
        startActivity(intent);
        finish();
    }

//    public void irConsumos(View view) {
//        ImageView imagen = findViewById(R.id.imagen_home);
//        imagen.setClickable(true);
//        Intent intent = new Intent(this, MisConsumos.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void irServicios(View view) {
//        ImageView imagen = findViewById(R.id.imagen_servicios);
//        imagen.setClickable(true);
//        Intent intent = new Intent(this, Servicios.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void irPerfil(View view) {
//        ImageView imagen = findViewById(R.id.imagen_perfil);
//        imagen.setClickable(true);
//        Intent intent = new Intent(this, Mi_Cuenta.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void irContacto(View view) {
//        ImageView imagen = findViewById(R.id.imagen_contacto);
//        imagen.setClickable(true);
//        Intent intent = new Intent(this, ContactoActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Servicios.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alarmasDB != null) {
            alarmasDB.close();
        }
    }
}

