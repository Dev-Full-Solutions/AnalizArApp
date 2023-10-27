package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Button;
import android.widget.TextView;
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
    Button button;
    RecyclerView recyclerView;

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

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(v -> irAlarmasConfiguracion());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AlarmaAdapter adapter = new AlarmaAdapter(alarmas);
        recyclerView.setAdapter(adapter);

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

        public class Alarma {
            private String textView2;
            private String textView3;
            private boolean switchState;

            public Alarma(String textView2, String textView3,boolean switchState) {
                this.textView2 = textView2;
                this.textView3 = textView3;
                this.switchState = switchState;
            }

            public String getTextView2() {
                return textView2;
            }
            public String getTextView3() {
                return textView3;
            }
            public boolean isSwitchState() {
                return switchState;
            }
       }


    public class AlarmaAdapter extends RecyclerView.Adapter<AlarmaAdapter.AlarmaViewHolder> {

        private ArrayList<Alarma> alarmas;

        public AlarmaAdapter(ArrayList<Alarma> alarmas) {
            this.alarmas = alarmas;
        }
        // Implementa los métodos necesarios, como onCreateViewHolder, onBindViewHolder y getItemCount
        public AlarmaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Aquí debes inflar la vista de la tarjeta y crear una instancia de AlarmaViewHolder
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_misalarmas1, parent, false);
            return new AlarmaViewHolder(view);
        }

        public void onBindViewHolder(AlarmaViewHolder holder, int position) {
            // Aquí debes realizar la asignación de datos a las vistas dentro de la tarjeta
            Alarma alarma = alarmas.get(position);
            holder.textView2.setText(alarma.getTextView2());
            holder.textView3.setText(alarma.getTextView3());
            holder.switch1.setChecked(alarma.isSwitchState());
            // Aquí puedes manejar la lógica para mostrar u ocultar vistas según la alarma
        }

        @Override
        public int getItemCount() {
            return alarmas.size()   ;
        }

        public class AlarmaViewHolder extends RecyclerView.ViewHolder{
            public TextView textView2;
            public TextView textView3;
            public Switch switch1;
            public AlarmaViewHolder(View itemView) {
                super(itemView);
                textView2 = itemView.findViewById(R.id.textView2);
                textView3 = itemView.findViewById(R.id.textView3);
                switch1 = itemView.findViewById(R.id.switch1);
            }
        }
    }





}


