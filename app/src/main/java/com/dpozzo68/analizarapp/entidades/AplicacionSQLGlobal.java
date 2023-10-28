package com.dpozzo68.analizarapp.entidades;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpozzo68.analizarapp.helpers.AlarmasSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.ConsumosSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.MedidoresSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;

public class AplicacionSQLGlobal extends Application {
    private SQLiteDatabase alarmasDB;
    private AlarmasSQLiteHelper alarmasSQLiteHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App global", "onCreate: ");
        alarmasSQLiteHelper = new AlarmasSQLiteHelper(this, "Alarmas", null, 1);

        alarmasDB = alarmasSQLiteHelper.getWritableDatabase();


    }

    public SQLiteDatabase getAlarmasDB() {
        return alarmasDB;
    }


}
