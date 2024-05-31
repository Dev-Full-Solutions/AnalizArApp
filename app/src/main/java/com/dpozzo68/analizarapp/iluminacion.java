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
import android.widget.Button;


import com.dpozzo68.analizarapp.entidades.Iluminacion;
import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;

import com.dpozzo68.analizarapp.helpers.AlarmasAdapter;
import com.dpozzo68.analizarapp.helpers.IluminacionAdapter;
import com.dpozzo68.analizarapp.helpers.IluminacionServicio;
import java.util.ArrayList;

public class iluminacion extends AppCompatActivity{

    Button button;
    private RecyclerView recyclerView;
    private SQLiteDatabase iluminacionDB;
    private IluminacionServicio iluminacionServicio;
    private ArrayList<Iluminacion> iluminacionArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iluminacion);

        iluminacionDB = ((AplicacionSQLGlobal) getApplication()).getIluminacionDB();


        iluminacionServicio = new IluminacionServicio(iluminacionDB);
        //Log.d("iluminacionServicio", "llenar db ");
        //iluminacionServicio.llenarIluminacionDB();
        iluminacionArray = iluminacionServicio.getIluminacionFromDB();
        Log.d("iluminacionServicio", "recuperar alarmas ");

        recyclerView = findViewById(R.id.recyclerView);
        IluminacionAdapter adapter = new IluminacionAdapter(iluminacionArray, iluminacionDB, iluminacionServicio);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(v -> irIluminacionConfiguracion());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetch the latest data from the database
        iluminacionArray = iluminacionServicio.getIluminacionFromDB();

        // Update the RecyclerView adapter with the new data
        IluminacionAdapter adapter = new IluminacionAdapter(iluminacionArray, iluminacionDB, iluminacionServicio);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void irIluminacionConfiguracion(){
        Intent intent = new Intent(this, IluminacionConfiguracion_Activity.class);
        startActivity(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iluminacionDB != null) {
            iluminacionDB.close();
        }
    }
}
