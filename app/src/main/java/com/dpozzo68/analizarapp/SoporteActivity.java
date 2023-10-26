package com.dpozzo68.analizarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.entidades.Usuario;

public class SoporteActivity extends AppCompatActivity {
    // Declaro las variables del formulario
    EditText editTextNombreApellido, editTextCorreoElectronico, editTextMensaje;
    Button btnEnviar, btnVolver;
    public Usuario usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        // Asigno Nombre, Apellido y Correo Electronico a los editText del formulario
        editTextNombreApellido = findViewById(R.id.editTextNombreApellido);
        editTextCorreoElectronico = findViewById(R.id.editTextCorreoElectronico);
        editTextNombreApellido.setText(this.usuario.getNombre() + " " + this.usuario.getApellido());
        editTextCorreoElectronico.setText(this.usuario.getEmail());

        // Asigno el contenido de los controles del formulario
        editTextMensaje = findViewById(R.id.editTextMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnVolver = findViewById(R.id.btnVolver);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNombreApellido.getText().toString().equals("")){
                    Toast.makeText(SoporteActivity.this, "Debe ingresar su nombre y apellido.", Toast.LENGTH_LONG).show();
                }else if (editTextCorreoElectronico.getText().toString().equals("")){
                        Toast.makeText(SoporteActivity.this, "Debe ingresar su correo electrónico.", Toast.LENGTH_LONG).show();
                    } else if (editTextMensaje.getText().toString().equals("")){
                        Toast.makeText(SoporteActivity.this, "Debe redactar un mensaje con su consulta.", Toast.LENGTH_LONG).show();
                        }else {
                            enviarCorreo();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoporteActivity.this, ContactoActivity.class);
                startActivity(intent);
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