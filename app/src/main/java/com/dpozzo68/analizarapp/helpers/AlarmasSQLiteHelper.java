package com.dpozzo68.analizarapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmasSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Alertas
    private static final String sqlCreate = "CREATE TABLE IF NOT EXISTS Alarmas (\n" +
            "    id_alarma INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    id_medidor INTEGER,\n" +
            "    nombre_alarma TEXT,\n" +
            "    tipo TEXT,\n" +
            "    fecha_alta TEXT,\n" +
            "    valor_alerta INTEGER,\n" +
            "    activo INTEGER\n" +
            ");\n";

    public AlarmasSQLiteHelper(Context contexto, String nombre,
                               CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS Alertas");

        db.execSQL(sqlCreate);

    }
}

