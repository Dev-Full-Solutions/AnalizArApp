package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Button;
import android.widget.Toast;

import com.dpozzo68.analizarapp.entidades.Alarma;
import com.dpozzo68.analizarapp.entidades.GlobalAlarma;
import com.dpozzo68.analizarapp.helpers.AlarmaServicio;
import com.dpozzo68.analizarapp.helpers.AlarmasSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.UsuarioServicio;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;

import java.util.ArrayList;


public class MisAlarmas1 extends AppCompatActivity {

    Switch switch01;
    Switch switch02;
    Switch switch03;
    Button button;
    public ArrayList<Alarma> alarmas = new ArrayList<Alarma>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misalarmas1);



        //creo alarmas helper y db
        AlarmasSQLiteHelper as = new AlarmasSQLiteHelper(MisAlarmas1.this, "Alarmas", null, 1);
        SQLiteDatabase db = as.getWritableDatabase();
        //Creo alarmaServicio, sumo datos a la db y los guardo en ArrayList de alarmas
        AlarmaServicio alServ = new AlarmaServicio();
        alServ.llenarAlarmasDB(db);
        alarmas = alServ.getAlarmasFromDB(db);





        Intent configIntent = getIntent();
        if (configIntent.hasExtra("accion")){
            Alarma alarma = GlobalAlarma.getinstanciaAlarma().getAlarma();
            if(configIntent.getStringExtra("accion") == "guardar"){
                alServ.guardarAlarma(db, alarma);
                Toast.makeText(this, "guardado", Toast.LENGTH_SHORT).show();
            }
            if(configIntent.getStringExtra("accion") == "editar"){
                alServ.editarAlarma(db, alarma);
                Toast.makeText(this, "editado", Toast.LENGTH_SHORT).show();
            }
            if(configIntent.getStringExtra("accion") == "eliminar"){
                alServ.eliminarAlarma(db, alarma);
                Toast.makeText(this, "eliminado", Toast.LENGTH_SHORT).show();
            }
        }





        switch01 = (Switch) findViewById(R.id.switch1);
        switch02 = (Switch) findViewById(R.id.switch2);
        switch03 = (Switch) findViewById(R.id.switch3);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(v -> irAlarmasConfiguracion());

    }
    public void irAlarmasConfiguracion(){
        Intent intent = new Intent(this, AlarmasConfiguracionActivity.class);
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
    public void onBackPressed() {
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
        finish();
    }

    public void onclick(View view) {
        if (view.getId() == R.id.swAlarmaActivo) ;
        {
            if (switch01.isChecked()) {

            } else {
            }
        }
    }

    public void onclick2(View view) {
        if (view.getId() == R.id.switch2) ;
        {
            if (switch02.isChecked()) {

            } else {
            }
        }
    }

    public void onclick3(View view) {
        if (view.getId() == R.id.switch3) ;
        {
            if (switch03.isChecked()) {

            } else {
            }
        }
    }
}

