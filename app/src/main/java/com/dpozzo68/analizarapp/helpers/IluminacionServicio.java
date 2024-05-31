package com.dpozzo68.analizarapp.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpozzo68.analizarapp.entidades.Iluminacion;

import java.util.ArrayList;

public class IluminacionServicio {

    private SQLiteDatabase iluminacionDB;

    public IluminacionServicio (SQLiteDatabase iluminacionDB) {this.iluminacionDB = iluminacionDB;}

    @SuppressLint("Range")
    public ArrayList<Iluminacion> getIluminacionFromDB() {
        ArrayList<Iluminacion> iluminaciones = new ArrayList<>();

        String query = "SELECT id_iluminacion, usuario_email, nombre_iluminacion, descripcion, intensidad, encendido FROM Iluminacion";
        Cursor cursor = iluminacionDB.rawQuery(query, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Iluminacion iluminacion = new Iluminacion();

                iluminacion.setIdIluminacion(cursor.getInt(cursor.getColumnIndex("id_iluminacion")));
                iluminacion.setUsuarioEmail(cursor.getString(cursor.getColumnIndex("usuario_email")));
                iluminacion.setNombre(cursor.getString(cursor.getColumnIndex("nombre_iluminacion")));
                iluminacion.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                iluminacion.setIntensidad(cursor.getInt(cursor.getColumnIndex("intensidad")));
                if (cursor.getInt(cursor.getColumnIndex("encendido"))== 0){
                    iluminacion.setEncendido(false);
                }else{
                    iluminacion.setEncendido(true);
                }

                iluminaciones.add(iluminacion);
            }

            cursor.close();
        }
        return iluminaciones;
    }

    public void guardarIluminacion(Iluminacion iluminacion) {
        if (iluminacionDB != null) {
            ContentValues iluminacionAGuardar = new ContentValues();
            iluminacionAGuardar.put("id_iluminacion", iluminacion.getIdIluminacion());
            iluminacionAGuardar.put("usuario_email", iluminacion.getUsuarioEmail());
            iluminacionAGuardar.put("nombre_iluminacion", iluminacion.getNombre());
            iluminacionAGuardar.put("descripcion", iluminacion.getDescripcion());
            iluminacionAGuardar.put("intensidad", iluminacion.getIntensidad());
            if (iluminacion.isEncendido()){
                iluminacionAGuardar.put("encendido", 1);
            }else{
                iluminacionAGuardar.put("encendido", 0);
            }
            iluminacionDB.insert("Iluminacion", null, iluminacionAGuardar);
        } else {
            Log.d("guardarIluminacion", "guardarIluminacion: db null");
        }
    }

    public void eliminarIluminacion(Iluminacion iluminacion){

        iluminacionDB.delete("Iluminacion", "id_iluminacion= " + iluminacion.getIdIluminacion(), null);
    }

    public void editarIluminacion(Iluminacion iluminacion){
        ContentValues iluminacionAGuardar = new ContentValues();
        iluminacionAGuardar.put("id_iluminacion", iluminacion.getIdIluminacion());
        iluminacionAGuardar.put("usuario_email", iluminacion.getUsuarioEmail());
        iluminacionAGuardar.put("nombre_iluminacion", iluminacion.getNombre());
        iluminacionAGuardar.put("descripcion", iluminacion.getDescripcion());
        iluminacionAGuardar.put("intensidad", iluminacion.getIntensidad());
        if (iluminacion.isEncendido()){
            iluminacionAGuardar.put("encendido", 1);
        }else{
            iluminacionAGuardar.put("encendido", 0);
        }
        iluminacionDB.update("Iluminacion", iluminacionAGuardar, "id_iluminacion= " + iluminacion.getIdIluminacion(), null);
    }

    public void editarIntensidad(Iluminacion iluminacion){
        ContentValues iluminacionAGuardar = new ContentValues();
        iluminacionAGuardar.put("intensidad", iluminacion.getIntensidad());
        iluminacionDB.update("Iluminacion", iluminacionAGuardar, "id_iluminacion= " + iluminacion.getIdIluminacion(), null);
    }

    public void editarEncendido(Iluminacion iluminacion){
        ContentValues iluminacionAGuardar = new ContentValues();
        if (iluminacion.isEncendido()){
            iluminacionAGuardar.put("encendido", 1);
        }else{
            iluminacionAGuardar.put("encendido", 0);
        }
        iluminacionDB.update("Iluminacion", iluminacionAGuardar, "id_iluminacion= " + iluminacion.getIdIluminacion(), null);
    }

    @SuppressLint("Range")
    public Iluminacion getIluminacionConID(int id) {
        Iluminacion iluminacion = null;

        String query = "SELECT id_iluminacion, usuario_email, nombre_iluminacion, descripcion, intensidad, encendido FROM Iluminacion WHERE id_alarma = ?";
        String[] args = {Integer.toString(id)};
        Cursor cursor = null;

        try {
            cursor = iluminacionDB.rawQuery(query, args);

            if (cursor != null && cursor.moveToFirst()) {
                iluminacion = new Iluminacion();
                iluminacion.setIdIluminacion(cursor.getInt(cursor.getColumnIndex("id_iluminacion")));
                iluminacion.setUsuarioEmail(cursor.getString(cursor.getColumnIndex("usuario_email")));
                iluminacion.setNombre(cursor.getString(cursor.getColumnIndex("nombre_iluminacion")));
                iluminacion.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                iluminacion.setIntensidad(cursor.getInt(cursor.getColumnIndex("intensidad")));
                if (cursor.getInt(cursor.getColumnIndex("encendido"))== 0){
                    iluminacion.setEncendido(false);
                }else{
                    iluminacion.setEncendido(true);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return iluminacion;
    }

}
