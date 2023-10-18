package com.dpozzo68.analizarapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AlertasSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Alertas
    String sqlCreate = "CREATE TABLE Alertas (id_alerta INTEGER PRIMARY KEY,  id_medidor INTEGER, fecha_alta TEXT, valor_alerta INTEGER, estado_alerta INTEGER)";

    public AlertasSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS Medidores");

        db.execSQL(sqlCreate);

    }
}

