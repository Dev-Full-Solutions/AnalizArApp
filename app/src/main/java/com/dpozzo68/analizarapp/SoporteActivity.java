package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SoporteActivity extends AppCompatActivity {
    // Declaro las variables del formulario
    EditText editTextNombreApellido, editTextCorreoElectronico, editTextMensaje;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        // Asigno el contenido de los controles del formulario
        editTextNombreApellido = findViewById(R.id.editTextNombreApellido);
        editTextCorreoElectronico = findViewById(R.id.editTextCorreoElectronico);
        editTextMensaje = findViewById(R.id.editTextMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNombreApellido.getText().toString().equals("") || editTextCorreoElectronico.getText().toString().equals("")){
                    Toast.makeText(SoporteActivity.this, "Disculpe, debe ingresar Nombre y Apellido.", Toast.LENGTH_LONG).show();
                }else {
                    enviarCorreo();
                }
            }
        });
    }
    private void enviarCorreo() {
        String nombre = editTextNombreApellido.getText().toString();
        String email = editTextCorreoElectronico.getText().toString();
        String mensaje = editTextMensaje.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");

        // Configuro el correo electrónico al que se enviará el mensaje del usuario
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dpozzo68@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mensaje desde la aplicación");

        // Construcción del mensaje
        String cuerpoMensaje = "Hola, soy " + nombre + "\nmi correo electrónico es: " + email + "\ny mi mensaje es: " + mensaje;
        intent.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);

        startActivity(Intent.createChooser(intent, "Enviar mensaje"));
    }
}