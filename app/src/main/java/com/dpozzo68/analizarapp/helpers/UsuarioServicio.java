package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dpozzo68.analizarapp.entidades.Usuario;

public class UsuarioServicio {
    private SQLiteDatabase usuariosDB;

    public UsuarioServicio(SQLiteDatabase usuariosDB) {
        this.usuariosDB = usuariosDB;
    }

    public void llenarTablaUsuarios() {
        ContentValues usuario1 = new ContentValues();
        usuario1.put("email", "diego@gmail.com");
        usuario1.put("nombre", "Diego");
        usuario1.put("apellido", "ISPC");
        usuario1.put("celular", "3511231234");
        usuario1.put("habilitado", 1);
        usuario1.put("fechaAlta", "01-01-2022");
        usuariosDB.insert("Usuarios", null, usuario1);

        ContentValues usuario2 = new ContentValues();
        usuario2.put("email", "danielpozzo@ispc.com");
        usuario2.put("nombre", "Daniel");
        usuario2.put("apellido", "Pozzo");
        usuario2.put("celular", "3546404883");
        usuario2.put("habilitado", 1);
        usuario2.put("fechaAlta", "01-06-2023");
        usuariosDB.insert("Usuarios", null, usuario2);

        ContentValues usuario3 = new ContentValues();
        usuario3.put("email", "christopherm@gmail.com");
        usuario3.put("nombre", "Christopher");
        usuario3.put("apellido", "ISPC");
        usuario3.put("celular", "3511231235");
        usuario3.put("habilitado", 1);
        usuario3.put("fechaAlta", "01-03-2022");
        usuariosDB.insert("Usuarios", null, usuario3);

        ContentValues usuario4 = new ContentValues();
        usuario4.put("email", "juliandm@ispc.com");
        usuario4.put("nombre", "Julian");
        usuario4.put("apellido", "Donamaría");
        usuario4.put("celular", "3511231235");
        usuario4.put("habilitado", 1);
        usuario4.put("fechaAlta", "15-02-2020");
        usuariosDB.insert("Usuarios", null, usuario4);

        ContentValues usuario5 = new ContentValues();
        usuario5.put("email", "katy23@gmail.com");
        usuario5.put("nombre", "Katerinne");
        usuario5.put("apellido", "Peralta");
        usuario5.put("celular", "3511231235");
        usuario5.put("habilitado", 1);
        usuario5.put("fechaAlta", "05-11-2023");
        usuariosDB.insert("Usuarios", null, usuario5);

        ContentValues usuario6 = new ContentValues();
        usuario6.put("email", "noemiz@ispc.com");
        usuario6.put("nombre", "Noemí");
        usuario6.put("apellido", "Zalazar");
        usuario6.put("celular", "3511231235");
        usuario6.put("habilitado", 1);
        usuario6.put("fechaAlta", "01-08-2021");
        usuariosDB.insert("Usuarios", null, usuario6);

        ContentValues usuario7 = new ContentValues();
        usuario7.put("email", "noemiv@yahoo.com");
        usuario7.put("nombre", "Noemí");
        usuario7.put("apellido", "Velazquez");
        usuario7.put("celular", "3511231235");
        usuario7.put("habilitado", 1);
        usuario7.put("fechaAlta", "17-12-2020");
        usuariosDB.insert("Usuarios", null, usuario7);

        ContentValues usuario8 = new ContentValues();
        usuario8.put("email", "laurar@outlook.com");
        usuario8.put("nombre", "Laura");
        usuario8.put("apellido", "Rodriguez");
        usuario8.put("celular", "3511231235");
        usuario8.put("habilitado", 1);
        usuario8.put("fechaAlta", "05-05-2022");
        usuariosDB.insert("Usuarios", null, usuario8);

        ContentValues usuario9 = new ContentValues();
        usuario9.put("email", "anibalm@gmail.com");
        usuario9.put("nombre", "Anibal");
        usuario9.put("apellido", "Morales");
        usuario9.put("celular", "3511231235");
        usuario9.put("habilitado", 1);
        usuario9.put("fechaAlta", "18-11-2022");
        usuariosDB.insert("Usuarios", null, usuario9);

        ContentValues usuario10 = new ContentValues();
        usuario10.put("email", "ispc.proyectointegradorweb@gmail.com");
        usuario10.put("nombre", "Profesores");
        usuario10.put("apellido", "ISPC");
        usuario10.put("celular", "3511231235");
        usuario10.put("habilitado", 1);
        usuario10.put("fechaAlta", "01-09-2023");
        usuariosDB.insert("Usuarios", null, usuario10);
    }

    @SuppressLint("Range")
    public Usuario getUsuariofromDB(String email) {
        Usuario usuario = null;

        // Use a parameterized query with a placeholder for the email
        String query = "SELECT nombre, apellido, celular, habilitado, fechaAlta FROM Usuarios WHERE email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = usuariosDB.rawQuery(query, selectionArgs);

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

    public boolean hayRegistros() {
        String query = "SELECT COUNT(*) FROM Usuarios";
        Cursor cursor = usuariosDB.rawQuery(query, null);
        int cuentaRegistros = 0;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cuentaRegistros = cursor.getInt(0);
            }
            cursor.close();
        }

        return cuentaRegistros > 0;
    }

    public void updateUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("celular", usuario.getCelular());
        values.put("habilitado", 1);
        values.put("habilitado", 1);
        values.put("fechaAlta", usuario.getFechaAlta());

        // Define the WHERE clause to identify the user by email
        String whereClause = "email=?";
        String[] whereArgs = {usuario.getEmail()};

        // Perform the update operation
        usuariosDB.update("Usuarios", values, whereClause, whereArgs);
    }

}



