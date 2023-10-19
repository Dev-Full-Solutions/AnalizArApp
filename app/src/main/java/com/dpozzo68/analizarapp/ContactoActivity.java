package com.dpozzo68.analizarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactoActivity extends AppCompatActivity {
    /*
    Bloque comentado el 18-10 Inicio
    Código a reutilizar en la funcionalidad del botón SOPORTE
    Button btnInicio;
    Bloque comentado el 18-10 Fin
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        // Código añadido el 18-10 Inicio
        TextView textView = findViewById(R.id.textView10);
        String linkText = getResources().getString(R.string.link);
        SpannableString spannableString = new SpannableString(linkText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                String url = "https://github.com/Dev-Full-Solutions";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        };

        //Código añadido el 18-10 Fin

        /*
        Bloque comentado el 18-10 Inicio
         Código a reutilizar para la navegación entre el botón SOPORTE y la activity con el formulario con la consulta del usuario (Funcionalidad)
         TextView textView = findViewById(R.id.textView);
         textView.setMovementMethod(LinkMovementMethod.getInstance());
         btnInicio = findViewById(R.id.button4);
         btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactoActivity.this, MisConsumos.class);
                startActivity(intent);
            }
         });
        Bloque comentado el 18-10 Fin
        */
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
}