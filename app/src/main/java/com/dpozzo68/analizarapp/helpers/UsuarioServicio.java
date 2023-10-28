package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dpozzo68.analizarapp.entidades.Usuario;

public class UsuarioServicio {

    public void llenarTablaUsuarios(SQLiteDatabase db){
        ContentValues usuario1 = new ContentValues();
        usuario1.put("email", "diego@gmail.com");
        usuario1.put("nombre", "Diego");
        usuario1.put("apellido", "ISPC");
        usuario1.put("celular", "3511231234");
        usuario1.put("habilitado", 1);
        usuario1.put("fechaAlta", "01-01-2022");
        db.insert("Usuarios", null, usuario1);

        ContentValues usuario2 = new ContentValues();
        usuario2.put("email", "carlos@gmail.com");
        usuario2.put("nombre", "Carlos");
        usuario2.put("apellido", "ISPC");
        usuario2.put("celular", "3511231235");
        usuario2.put("habilitado", 1);
        usuario2.put("fechaAlta", "01-02-2022");
        db.insert("Usuarios", null, usuario2);

        ContentValues usuario3 = new ContentValues();
        usuario3.put("email", "christopher@gmail.com");
        usuario3.put("nombre", "Christopher");
        usuario3.put("apellido", "ISPC");
        usuario3.put("celular", "3511231235");
        usuario3.put("habilitado", 1);
        usuario3.put("fechaAlta", "01-03-2022");
        db.insert("Usuarios", null, usuario3);
    }

    @SuppressLint("Range")
    public Usuario getUsuariofromDB(SQLiteDatabase db, String email) {
        Usuario usuario = null;

        // Use a parameterized query with a placeholder for the email
        String query = "SELECT nombre, apellido, celular, habilitado, fechaAlta FROM Usuarios WHERE email = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                usuario = new Usuario();

                // Retrieve data from the cursor
                usuario.setEmail(email);
                usuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                usuario.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
                usuario.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
                usuario.setHabilitado(cursor.getInt(cursor.getColumnIndex("habilitado")) == 1);
                usuario.setFechaAlta(cursor.getString(cursor.getColumnIndex("fechaAlta")));
            }

            cursor.close();
        }

        return usuario;
    }

}
