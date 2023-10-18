package com.dpozzo68.analizarapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class MedidoresSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Medidores
    String sqlCreate = "CREATE TABLE Medidores (id_medidor INTEGER PRIMARY KEY,  id_usuario INTEGER, nombre TEXT, detalle TEXT )";

    public MedidoresSQLiteHelper(Context contexto, String nombre,
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
