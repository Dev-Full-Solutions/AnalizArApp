package com.dpozzo68.analizarapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.dpozzo68.analizarapp.entidades.Usuario;


public class IluminacionSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Iluminacion
    private static final String sqlCreate = "CREATE TABLE IF NOT EXISTS Iluminacion (\n" +
            "    id_iluminacion INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    usuario_email TEXT,\n" +
            "    nombre_iluminacion TEXT,\n" +
            "    descripcion TEXT,\n" +
            "    intensidad INTEGER,\n" +
            "    encendido INTEGER\n" +
            ");\n";


    private int idIluminacion;
    private Usuario usuario;
    private String nombre;
    private String descripcion;
    private int intensidad;
    private boolean encendido;

    public IluminacionSQLiteHelper(Context contexto, String nombre,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS Alarmas");

        db.execSQL(sqlCreate);

    }
}

