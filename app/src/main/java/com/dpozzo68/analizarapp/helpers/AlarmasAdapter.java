package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpozzo68.analizarapp.AlarmasConfiguracionActivity;
import com.dpozzo68.analizarapp.R;
import com.dpozzo68.analizarapp.entidades.Alarma;
import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;

import java.util.ArrayList;

    public class AlarmasAdapter extends RecyclerView.Adapter<AlarmasAdapter.AlarmaViewHolder> {

        private ArrayList<Alarma> alarmas;
        private AlarmaServicio alarmaServicio;
        private SQLiteDatabase alarmasDB;



        public AlarmasAdapter(ArrayList<Alarma> alarmas, SQLiteDatabase alarmasDB, AlarmaServicio alarmaServicio) {
            this.alarmas = alarmas;
            this.alarmaServicio = alarmaServicio;
            this.alarmasDB = alarmasDB;
        }

        @NonNull
        @Override
        public AlarmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_card_individual, parent, false);
            return new AlarmaViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull AlarmaViewHolder holder, int position) {
            Alarma alarma = alarmas.get(position);
            holder.textViewNombreAlarma.setText(alarma.getNombreAlarma());
            holder.textViewDescripcionAlarma.setText(alarma.getTipo() + " " + alarma.getValorAlerta());
            holder.switchAlarma.setChecked(alarma.getEstadoAlerta() == 1);

            holder.textViewNombreAlarma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an Intent to launch a new activity
                    Intent intent = new Intent(view.getContext(), AlarmasConfiguracionActivity.class);

                    // Pass the Alarma object as an extra
                    Log.d("getidalarma", "int " + alarma.getIdalarma());
                    intent.putExtra("AlarmaID", alarma.getIdalarma());

                    // Start the new activity
                    view.getContext().startActivity(intent);
                }
            });

            holder.switchAlarma.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // el SW esta en OFF, se cambia en la BD
                    alarma.setEstadoAlerta(1);
                    alarmaServicio.cambiarEstadoAlarma(alarma);
                } else {
                    // el SW esta en OFF, se cambia en la BD
                    alarma.setEstadoAlerta(0);
                    alarmaServicio.cambiarEstadoAlarma(alarma);
                }
            });
        }

        @Override
        public int getItemCount() {
            return alarmas.size();
        }

        public class AlarmaViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewNombreAlarma;
            public TextView textViewDescripcionAlarma;
            public Switch switchAlarma;

            public AlarmaViewHolder(View itemView) {
                super(itemView);
                textViewNombreAlarma = itemView.findViewById(R.id.textViewNombreAlarma);
                textViewDescripcionAlarma = itemView.findViewById(R.id.textViewDescripcionAlarma);
                switchAlarma = itemView.findViewById(R.id.switchAlarma);
            }
        }
    }


