package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import android.widget.Toast;
public class AlarmasConfiguracionActivity extends AppCompatActivity {
    // Obtener referencia al EditText
    private EditText etFecha;
    private EditText etHora;
    private Button salir;
    private Button guardar;

    private int ultimoDiaDelMes, ultimoMes, ultimoAnio, ultimoHora, ultimoMinuto;

    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;
            refrescarFechaEnEditText();
        }
    };
    public void refrescarFechaEnEditText() {

        String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d", ultimoDiaDelMes, ultimoMes+1, ultimoAnio);

        // La ponemos en el editText
        etFecha.setText(fecha);
    }

    private TimePickerDialog.OnTimeSetListener listenerDeTimePicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
            ultimoHora = hora;
            ultimoMinuto = minuto;
            refrescarHoraEnEditText();
        }
    };
    public void refrescarHoraEnEditText() {

        String hora = String.format(Locale.getDefault(), "%02d:%02d", ultimoHora, ultimoMinuto);
        etHora.setText(hora);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas_configuracion);
        etFecha = findViewById(R.id.editTextDate);
        etHora = findViewById(R.id.editTextTime);

        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        ultimoHora = 12;
        ultimoMinuto = 0;

        refrescarFechaEnEditText();
        refrescarHoraEnEditText();


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

        salir = (Button) findViewById(R.id.salir);
        salir.setOnClickListener(v -> irAlarmas());
        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(v -> guardarAlarma());
    }

    public void guardarAlarma(){
        Toast.makeText(this, etFecha.getText() + " " + etHora.getText(),Toast.LENGTH_SHORT).show();
        irAlarmas();
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