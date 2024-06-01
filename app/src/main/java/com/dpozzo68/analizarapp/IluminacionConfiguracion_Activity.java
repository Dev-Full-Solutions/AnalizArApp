package com.dpozzo68.analizarapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;
import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.entidades.Iluminacion;
import com.dpozzo68.analizarapp.entidades.Usuario;
import com.dpozzo68.analizarapp.helpers.IluminacionServicio;

public class IluminacionConfiguracion_Activity extends AppCompatActivity {

    public Usuario usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();
    private boolean nuevaIluminacion;
    private EditText etNombre;
    private CheckBox cbEncendido;
    private TextView tvTracker;
    private SeekBar skIntensidad;
    private EditText etDescripcion;
    private Button salir;
    private Button guardar;
    private Button eliminar;
    private int valorIntensidad = 100;
    private Iluminacion iluminacion;
    private SQLiteDatabase iluminacionDB;
    private IluminacionServicio iluminacionServicio;
    private String txtIntensidad = "Intensidad: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iluminacion_configuracion);

        iluminacionDB = ((AplicacionSQLGlobal) getApplication()).getIluminacionDB();
        iluminacionServicio = new IluminacionServicio(iluminacionDB);

        //asociar variables con views
        etNombre = findViewById(R.id.edit_nombre_iluminacion);
        cbEncendido = findViewById(R.id.checkbox_on_off_iluminacion);
        skIntensidad = findViewById(R.id.seekBar_intensity);
        tvTracker = findViewById(R.id.iluminacion_intensidad_tracker);
        etDescripcion = findViewById(R.id.edit_descripcion_iluminacion);



        salir = findViewById(R.id.salir);
        guardar = findViewById(R.id.guardar);
        eliminar = findViewById(R.id.eliminar);

        //verifica si es una nueva iluminacion o edita una nueva
        if (!getIntent().hasExtra("IluminacionID")){
            iluminacion = new Iluminacion();
            nuevaIluminacion = true;
            eliminar.setVisibility(View.GONE);
            tvTracker.setText(txtIntensidad + String.valueOf(valorIntensidad));
            skIntensidad.setProgress(100);
            cbEncendido.setChecked(true);
        }else{
            nuevaIluminacion = false;
            int iluminacionID = getIntent().getIntExtra("IluminacionID", 0);
            iluminacion = iluminacionServicio.getIluminacionConID(iluminacionID);
            etNombre.setText(iluminacion.getNombre());
            if (iluminacion.isEncendido()){
                cbEncendido.setChecked(true);
            }else {
                cbEncendido.setChecked(false);
            }

            //setear intensidad inicial
            skIntensidad.setProgress(iluminacion.getIntensidad());

            //
            skIntensidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    valorIntensidad = i;
                    tvTracker.setText(txtIntensidad + String.valueOf(valorIntensidad));
                    iluminacion.setIntensidad(valorIntensidad);
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            etDescripcion.setText(iluminacion.getDescripcion());

            salir.setOnClickListener(v -> irIluminacion());
            guardar.setOnClickListener(v -> guardarIluminacion());
            eliminar.setOnClickListener(v -> eliminarIluminacion());

        }


    }

    public void contruirIluminacion(){
        iluminacion.setIntensidad(valorIntensidad);
        iluminacion.setEncendido(cbEncendido.isActivated());
        iluminacion.setNombre(String.valueOf(etNombre.getText()));
        iluminacion.setDescripcion(String.valueOf(etDescripcion.getText()));
        iluminacion.setUsuarioEmail(usuario.getEmail());
    }

    public void guardarIluminacion(){
        contruirIluminacion();
        if(iluminacion.getNombre() != null && !iluminacion.getNombre().isEmpty()){
            if(nuevaIluminacion){
                iluminacionServicio.guardarIluminacion(iluminacion);
                irIluminacion();
            }else{
                iluminacionServicio.editarIluminacion(iluminacion);
                irIluminacion();
            }
        }else{
            Toast.makeText(this, "completar campo valor", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarIluminacion(){
        contruirIluminacion();
        iluminacionServicio.editarIluminacion(iluminacion);
        irIluminacion();
    }

    public void irIluminacion() {
        Intent intent = new Intent(this, iluminacion.class);
        startActivity(intent);
    }


}



