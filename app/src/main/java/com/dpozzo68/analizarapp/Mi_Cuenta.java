package com.dpozzo68.analizarapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dpozzo68.analizarapp.entidades.AplicacionSQLGlobal;
import com.dpozzo68.analizarapp.entidades.GlobalUsuario;
import com.dpozzo68.analizarapp.entidades.Usuario;
import com.dpozzo68.analizarapp.helpers.UsuarioServicio;
import com.google.firebase.auth.FirebaseAuth;

public class Mi_Cuenta extends AppCompatActivity {
    //creo variable usuario
    public Usuario usuario;
    private SQLiteDatabase usuarioDB;
    private UsuarioServicio usuarioServicio;

    public interface UsuarioCallback {
        void onSuccess(Usuario usuario);
        void onError(String mensaje);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();

        TextView email = findViewById(R.id.mailUsuario3);
        TextView nombreCompleto = findViewById(R.id.nombreUsuario);
        EditText editNombre = findViewById(R.id.editNombre);
        EditText editApellido = findViewById(R.id.editApellido);
        EditText editNumero = findViewById(R.id.editTelefono);
        email.setText(this.usuario.getEmail());
        nombreCompleto.setText(this.usuario.getNombre() + " " + this.usuario.getApellido());

        usuarioDB = ((AplicacionSQLGlobal) getApplication()).getUsuariosDB();
        usuarioServicio = new UsuarioServicio(usuarioDB);

        Button buttonDatosPersonales = findViewById(R.id.buttonEditDatos);
        buttonDatosPersonales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = GlobalUsuario.getInstanciaUsuario().getUsuario();

                EditText editNombre = findViewById(R.id.editNombre);
                EditText editApellido = findViewById(R.id.editApellido);
                EditText editNumero = findViewById(R.id.editTelefono);

                usuario.setNombre(String.valueOf(editNombre.getText()));
                usuario.setApellido(String.valueOf(editApellido.getText()));
                usuario.setCelular(String.valueOf(editNumero.getText()));



                try {
                    usuarioServicio.updateUsuario(usuario);
                    GlobalUsuario.getInstanciaUsuario().setUsuario(usuario);
                    Toast.makeText(getApplicationContext(), "Datos actualizados de manera correcta", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "La actualización falló", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();


        AplicacionSQLGlobal aplicacionSQLGlobal = (AplicacionSQLGlobal) getApplication();
        aplicacionSQLGlobal.cerrarDB();
        //aplicacionSQLGlobal.updateVersion();

        GlobalUsuario globalUsuario = GlobalUsuario.getInstanciaUsuario();
        globalUsuario.resetUsuario();




        finish();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
}