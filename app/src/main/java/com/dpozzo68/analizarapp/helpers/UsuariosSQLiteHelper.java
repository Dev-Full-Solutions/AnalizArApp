package com.dpozzo68.analizarapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Medidores
    private static final String crearTablaUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios (\n" +
            "    email TEXT PRIMARY KEY ,\n" +
            "    nombre TEXT,\n" +
            "    apellido TEXT,\n" +
            "    celular TEXT,\n" +
            "    habilitado INTEGER,\n" +
            "    fechaAlta TEXT\n" +
            ");\n";
    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(crearTablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        db.execSQL(crearTablaUsuarios);

    }

}
