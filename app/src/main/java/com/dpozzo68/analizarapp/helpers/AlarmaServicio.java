package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpozzo68.analizarapp.entidades.Alarma;



import java.util.ArrayList;

public class AlarmaServicio {

    private SQLiteDatabase alarmasDB;
    public AlarmaServicio(SQLiteDatabase alarmasDB) {
        this.alarmasDB = alarmasDB;
    }

    public void llenarAlarmasDB(){
        ContentValues alarma1 = new ContentValues();
        alarma1.put("id_medidor", 1);
        alarma1.put("nombre_alarma", "Alarma Test 1");
        alarma1.put("tipo", "Mensual");
        alarma1.put("fecha_alta", "01/10/2023");
        alarma1.put("valor_alerta", 900);
        alarma1.put("activo", 1);
        alarmasDB.insert("Alarmas", null, alarma1);

        ContentValues alarma2 = new ContentValues();
        alarma2.put("id_medidor", 1);
        alarma2.put("nombre_alarma", "Alarma Test 2");
        alarma2.put("tipo", "Semanal");
        alarma2.put("fecha_alta", "01/10/2023");
        alarma2.put("valor_alerta", 400);
        alarma2.put("activo", 1);
        alarmasDB.insert("Alarmas", null, alarma2);

    }

    @SuppressLint("Range")
    public ArrayList<Alarma> getAlarmasFromDB() {
        ArrayList<Alarma> alarmas = new ArrayList<>();

        String query = "SELECT id_alarma, id_medidor, nombre_alarma, tipo, fecha_alta, valor_alerta, activo FROM Alarmas";
        Cursor cursor = alarmasDB.rawQuery(query, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Alarma alarma = new Alarma();

                alarma.setIdalarma(cursor.getInt(cursor.getColumnIndex("id_alarma")));
                alarma.setIdMedidor(cursor.getInt(cursor.getColumnIndex("id_medidor")));
                alarma.setNombreAlarma(cursor.getString(cursor.getColumnIndex("nombre_alarma")));
                alarma.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                alarma.setFechaAlta(cursor.getString(cursor.getColumnIndex("fecha_alta")));
                alarma.setValorAlerta(cursor.getInt(cursor.getColumnIndex("valor_alerta")));
                alarma.setEstadoAlerta(cursor.getInt(cursor.getColumnIndex("activo")));

                alarmas.add(alarma);
            }

            cursor.close();
        }
        return alarmas;
    }
    public void guardarAlarma(Alarma alarma) {
        if (alarmasDB != null) {
            ContentValues alarmaAGuardar = new ContentValues();
            alarmaAGuardar.put("id_medidor", alarma.getIdMedidor());
            alarmaAGuardar.put("nombre_alarma", alarma.getNombreAlarma());
            alarmaAGuardar.put("tipo", alarma.getTipo());
            alarmaAGuardar.put("fecha_alta", alarma.getFechaAlta());
            alarmaAGuardar.put("valor_alerta", alarma.getValorAlerta());
            alarmaAGuardar.put("activo", alarma.getEstadoAlerta());
            alarmasDB.insert("Alarmas", null, alarmaAGuardar);
        } else {
            Log.d("guardarAlarma", "guardarAlarma: db null");
        }
    }


    public void eliminarAlarma(Alarma alarma){

        alarmasDB.delete("Alarmas", "id_alarma= " + alarma.getIdalarma(), null);
    }

    public void editarAlarma(Alarma alarma){

        ContentValues alarmaAActualizar = new ContentValues();
        alarmaAActualizar.put("id_medidor", alarma.getIdMedidor());
        alarmaAActualizar.put("nombre_alarma", alarma.getNombreAlarma());
        alarmaAActualizar.put("tipo", alarma.getTipo());
        alarmaAActualizar.put("fecha_alta", alarma.getFechaAlta());
        alarmaAActualizar.put("valor_alerta", alarma.getValorAlerta());
        alarmaAActualizar.put("activo", alarma.getEstadoAlerta());
        alarmasDB.update("Alarmas", alarmaAActualizar, "id_alarma= " + alarma.getIdalarma(), null);
    }

    public void cambiarEstadoAlarma(Alarma alarma){
        ContentValues alarmaAActualizar = new ContentValues();
        alarmaAActualizar.put("activo", alarma.getEstadoAlerta());
        alarmasDB.update("Alarmas", alarmaAActualizar, "id_alarma =" + alarma.getIdalarma(), null);
    }

    @SuppressLint("Range")
    public Alarma getAlarmaConID(int id) {
        Alarma alarma = null;

        String query = "SELECT id_alarma, id_medidor, nombre_alarma, tipo, fecha_alta, valor_alerta, activo FROM Alarmas WHERE id_alarma = ?";
        String[] args = {Integer.toString(id)};
        Cursor cursor = null;

        try {
            cursor = alarmasDB.rawQuery(query, args);

            if (cursor != null && cursor.moveToFirst()) {
                alarma = new Alarma();
                alarma.setIdalarma(cursor.getInt(cursor.getColumnIndex("id_alarma")));
                alarma.setIdMedidor(cursor.getInt(cursor.getColumnIndex("id_medidor")));
                alarma.setNombreAlarma(cursor.getString(cursor.getColumnIndex("nombre_alarma")));
                alarma.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                alarma.setFechaAlta(cursor.getString(cursor.getColumnIndex("fecha_alta")));
                alarma.setValorAlerta(cursor.getInt(cursor.getColumnIndex("valor_alerta")));
                alarma.setEstadoAlerta(cursor.getInt(cursor.getColumnIndex("activo")));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return alarma;
    }


}
