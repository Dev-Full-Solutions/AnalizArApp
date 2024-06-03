package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dpozzo68.analizarapp.iluminacion;
import com.dpozzo68.analizarapp.IluminacionConfiguracion_Activity;
import com.dpozzo68.analizarapp.R;
import com.dpozzo68.analizarapp.entidades.Iluminacion;
import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;

import java.util.ArrayList;

public class IluminacionAdapter extends RecyclerView.Adapter<IluminacionAdapter.IluminacionViewHolder> {

    private ArrayList<Iluminacion> iluminacion;
    private IluminacionServicio iluminacionServicio;
    private SQLiteDatabase iluminacionDB;

    public IluminacionAdapter(ArrayList<Iluminacion> iluminacion, SQLiteDatabase iluminacionDB, IluminacionServicio iluminacionServicio) {
        this.iluminacion = iluminacion;
        this.iluminacionServicio = iluminacionServicio;
        this.iluminacionDB = iluminacionDB;
    }



    @NonNull
    @Override
    public IluminacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ilumin_card_individual, parent, false);
        return new IluminacionViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull IluminacionViewHolder holder, int position) {
        Iluminacion iluminacion = this.iluminacion.get(position);
        holder.textViewNombreAlarmaIluminacion.setText(iluminacion.getNombre());
        holder.textViewDescripcionAlarma.setText(iluminacion.getDescripcion() + " " + iluminacion.getDescripcion());
        holder.checkbox_on_off.setChecked(iluminacion.isEncendido());
        holder.seekBar_intensity.setProgress(iluminacion.getIntensidad());

        String text = "Intensidad: " + String.valueOf(iluminacion.getIntensidad());
        holder.textViewTracker.setText(text);

        holder.textViewNombreAlarmaIluminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch a new activity
                Intent intent = new Intent(view.getContext(), IluminacionConfiguracion_Activity.class);

                // Pass the Iluminacion object as an extra
                Log.d("getidiluminacion", "int " + iluminacion.getIdIluminacion());
                intent.putExtra("IluminacionID", iluminacion.getIdIluminacion());

                // Start the new activity
                view.getContext().startActivity(intent);
            }
        });

        holder.checkbox_on_off.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Actualizar el estado en la base de datos
            iluminacion.setEncendido(isChecked ? true : false);
            iluminacionServicio.editarEncendido(iluminacion);

        });

        holder.seekBar_intensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // Actualizar el valor en la base de datos
                    String text = "Intensidad: " + String.valueOf(progress);
                    holder.textViewTracker.setText(text);
                    iluminacion.setIntensidad(progress);
                    iluminacionServicio.editarIntensidad(iluminacion);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Puedes hacer algo aquí si necesitas
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Puedes hacer algo aquí si necesitas
            }
        });
    }

    @Override
    public int getItemCount() {
        return iluminacion.size();
    }

    public static class IluminacionViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombreAlarmaIluminacion;
        public TextView textViewDescripcionAlarma;
        public TextView textViewTracker;
        public SeekBar seekBar_intensity;
        public CheckBox checkbox_on_off;

        public IluminacionViewHolder(View itemView) {
            super(itemView);
            textViewNombreAlarmaIluminacion = itemView.findViewById(R.id.textViewNombreAlarmaIluminacion);
            textViewDescripcionAlarma = itemView.findViewById(R.id.textViewDescripcionAlarma);
            textViewTracker = itemView.findViewById(R.id.tv_intensity_value);
            seekBar_intensity = itemView.findViewById(R.id.seekBar_intensity);
            checkbox_on_off = itemView.findViewById(R.id.checkbox_on_off);

        }
    }
}
