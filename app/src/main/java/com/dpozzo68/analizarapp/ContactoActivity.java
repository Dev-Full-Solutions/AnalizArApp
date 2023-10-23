package com.dpozzo68.analizarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactoActivity extends AppCompatActivity {

    Button btnSoporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        btnSoporte = findViewById(R.id.btn_soporte);
        btnSoporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactoActivity.this, SoporteActivity.class);
                startActivity(intent);
            }
        });

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

        int startIndex = linkText.indexOf("DevFullSolutions");
        int endIndex = startIndex + 16;

        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
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
}