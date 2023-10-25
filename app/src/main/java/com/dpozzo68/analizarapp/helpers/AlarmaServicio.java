package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dpozzo68.analizarapp.entidades.Alarma;

import java.util.ArrayList;

public class AlarmaServicio {

    public void llenarAlarmasDB(SQLiteDatabase db){
        ContentValues alarma1 = new ContentValues();
        alarma1.put("id_medidor", 1);
        alarma1.put("tipo", "Mensual");
        alarma1.put("fecha_alta", "01/10/2023");
        alarma1.put("valor_alerta", 900);
        alarma1.put("activo", 1);
        db.insert("Alarmas", null, alarma1);

        ContentValues alarma2 = new ContentValues();
        alarma2.put("id_medidor", 1);
        alarma2.put("tipo", "Semanal");
        alarma2.put("fecha_alta", "01/10/2023");
        alarma2.put("valor_alerta", 400);
        alarma2.put("activo", 1);
        db.insert("Alarmas", null, alarma1);

    }

    @SuppressLint("Range")
    public ArrayList<Alarma> getAlarmasFromDB(SQLiteDatabase db) {
        ArrayList<Alarma> alarmas = new ArrayList<>();

        String query = "SELECT id_alarma, id_medidor, tipo, fecha_alta, valor_alerta FROM Usuarios WHERE activo = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Alarma alarma = new Alarma();

                alarma.setIdalarma(cursor.getInt(cursor.getColumnIndex("id_alarma")));
                alarma.setIdMedidor(cursor.getInt(cursor.getColumnIndex("id_medidor")));
                alarma.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                alarma.setFechaAlta(cursor.getString(cursor.getColumnIndex("fecha_alta")));
                alarma.setValorAlerta(cursor.getInt(cursor.getColumnIndex("valor_alerta")));

                alarmas.add(alarma);
            }

            cursor.close();
        }

        return alarmas;
    }

}
