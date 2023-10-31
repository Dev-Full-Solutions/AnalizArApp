package com.dpozzo68.analizarapp.entidades;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpozzo68.analizarapp.helpers.AlarmasSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.ConsumosSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.MedidoresSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;

public class AplicacionSQLGlobal extends Application {
    private SQLiteDatabase alarmasDB, usuariosDB;
    private AlarmasSQLiteHelper alarmasSQLiteHelper;
    private UsuariosSQLiteHelper usuariosSQLiteHelper;



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App global", "onCreate: ");
        alarmasSQLiteHelper = new AlarmasSQLiteHelper(this, "Alarmas", null, 1);
        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this, "Usuarios", null, 1);

        alarmasDB = alarmasSQLiteHelper.getWritableDatabase();
        usuariosDB = usuariosSQLiteHelper.getWritableDatabase();


    }

    public SQLiteDatabase getAlarmasDB() {
        return alarmasDB;
    }

    public SQLiteDatabase getUsuariosDB() {
        return usuariosDB;
    }

    public void cerrarDB() {

        int currentVersion = alarmasDB.getVersion();
        int newVersion = currentVersion + 1;

        alarmasSQLiteHelper.onUpgrade(alarmasDB, currentVersion, newVersion);
        usuariosSQLiteHelper.onUpgrade(usuariosDB, currentVersion, newVersion);


        if (alarmasDB != null && alarmasDB.isOpen()) {
            alarmasDB.close();
        }

        if (usuariosDB != null && usuariosDB.isOpen()) {
            usuariosDB.close();
        }

        alarmasDB = alarmasSQLiteHelper.getWritableDatabase();
        usuariosDB = usuariosSQLiteHelper.getWritableDatabase();

    }



}
