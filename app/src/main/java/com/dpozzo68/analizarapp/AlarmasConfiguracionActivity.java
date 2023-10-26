package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import android.widget.Toast;

import com.dpozzo68.analizarapp.entidades.Alarma;
import com.dpozzo68.analizarapp.entidades.GlobalAlarma;
import com.dpozzo68.analizarapp.helpers.AlarmaServicio;
import com.dpozzo68.analizarapp.helpers.AlarmasSQLiteHelper;

public class AlarmasConfiguracionActivity extends AppCompatActivity {
    private boolean nuevaAlarma = false;
    private EditText etFecha;
    private EditText etHora;
    private EditText etValorAlarma;
    private EditText etNombreAlarma;
    private Switch swActivo;
    private RadioGroup rdTipo;
    private String tipoAlarma;
    private Button salir;
    private Button guardar;
    private Button eliminar;
    private int idMedidor = 1;

    private int estadoAlerta = 1;
    private Alarma alarma;
    private int ultimoDiaDelMes, ultimoMes, ultimoAnio, ultimoHora, ultimoMinuto;




    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;
            refrescarFecha();
        }
    };
    public void refrescarFecha() {

        String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d", ultimoDiaDelMes, ultimoMes+1, ultimoAnio);

        // La ponemos en el editText
        etFecha.setText(fecha);
    }

    private TimePickerDialog.OnTimeSetListener listenerDeTimePicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
            ultimoHora = hora;
            ultimoMinuto = minuto;
            refrescarHora();
        }
    };
    public void refrescarHora() {

        String hora = String.format(Locale.getDefault(), "%02d:%02d", ultimoHora, ultimoMinuto);
        etHora.setText(hora);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas_configuracion);


        etFecha = findViewById(R.id.editTextDate);
        etHora = findViewById(R.id.editTextTime);
        etValorAlarma = findViewById(R.id.editLimite);
        etNombreAlarma = findViewById(R.id.edit_nombre_alarma);
        swActivo = findViewById(R.id.swAlarmaActivo);
        rdTipo = findViewById(R.id.rdGroupAlarma);
        salir = findViewById(R.id.salir);
        guardar = findViewById(R.id.guardar);
        eliminar = findViewById(R.id.eliminar);

        if (!getIntent().hasExtra("Alarma")){
            alarma = new Alarma();
            nuevaAlarma = true;
            eliminar.setVisibility(View.GONE);
        } else {
            alarma = (Alarma) getIntent().getSerializableExtra("Alarma");
        }


        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        ultimoHora = 12;
        ultimoMinuto = 0;

        refrescarFecha();
        refrescarHora();

        swActivo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    estadoAlerta = 1;
                } else {
                    estadoAlerta = 0;
                }
            }
        });

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialogoFecha = new DatePickerDialog(AlarmasConfiguracionActivity.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                dialogoFecha.show();
            }
        });
        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialogoHora = new TimePickerDialog(AlarmasConfiguracionActivity.this, listenerDeTimePicker, ultimoHora, ultimoMinuto, true);
                dialogoHora.show();
            }
        });

        rdTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedRadioButton = findViewById(i);
                tipoAlarma = selectedRadioButton.getText().toString();
            }
        });

        salir.setOnClickListener(v -> irAlarmas());
        guardar.setOnClickListener(v -> guardarAlarma());
        eliminar.setOnClickListener(v -> eliminarAlarma());

    }

    public void guardarAlarma(){
        if(!etValorAlarma.getText().toString().isEmpty()){
            construirAlarma();
            if(nuevaAlarma){
                Intent intent = new Intent(this, MisAlarmas1.class);
                intent.putExtra("acccion", "guardar");
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, MisAlarmas1.class);
                intent.putExtra("acccion", "editar");
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "completar campo valor", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarAlarma(){
        construirAlarma();
        Intent intent = new Intent(this, MisAlarmas1.class);
        intent.putExtra("acccion", "eliminar");
        startActivity(intent);
    }

    public void construirAlarma(){
        alarma.setIdMedidor(1);
        alarma.setTipo(tipoAlarma);
        alarma.setNombreAlarma(etNombreAlarma.getText().toString());
        alarma.setFechaAlta(etFecha.getText().toString() + " " + etHora.getText().toString());
        alarma.setValorAlerta(Integer.parseInt(etValorAlarma.getText().toString()));
        alarma.setEstadoAlerta(estadoAlerta);
        GlobalAlarma.getinstanciaAlarma().setAlarma(this.alarma);
    }
    public void irConsumos(View view) {
        ImageView imagen = findViewById(R.id.imagen_home);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
    }

    public void irAlarmas() {
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
        Intent intent = new Intent(this, MisAlarmas1.class);
        startActivity(intent);
        finish();
    }
}