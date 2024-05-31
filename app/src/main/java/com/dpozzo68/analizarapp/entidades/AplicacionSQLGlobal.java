package com.dpozzo68.analizarapp.entidades;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpozzo68.analizarapp.helpers.AlarmasSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.ConsumosSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.IluminacionSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.MedidoresSQLiteHelper;
import com.dpozzo68.analizarapp.helpers.UsuariosSQLiteHelper;

public class AplicacionSQLGlobal extends Application {
    private SQLiteDatabase alarmasDB;
    private SQLiteDatabase iluminacionDB;
    private AlarmasSQLiteHelper alarmasSQLiteHelper;
    private IluminacionSQLiteHelper iluminacionSQLiteHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App global", "onCreate: ");
        alarmasSQLiteHelper = new AlarmasSQLiteHelper(this, "Alarmas", null, 1);

        alarmasDB = alarmasSQLiteHelper.getWritableDatabase();

        iluminacionSQLiteHelper = new IluminacionSQLiteHelper(this, "Iluminacion", null, 1);

        iluminacionDB = iluminacionSQLiteHelper.getWritableDatabase();


    }

    public SQLiteDatabase getAlarmasDB() {
        return alarmasDB;
    }
    public SQLiteDatabase getIluminacionDB() { return iluminacionDB; }


}
