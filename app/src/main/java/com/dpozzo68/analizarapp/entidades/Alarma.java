package com.dpozzo68.analizarapp.entidades;

import java.io.Serializable;

public class Alarma implements Serializable {
    private int idAlarma;
    private int idMedidor;
    private String nombreAlarma;
    private String tipo;
    private String fechaAlta;
    private int valorAlerta;
    private int estadoAlerta;

    public Alarma() {
    }


    public int getIdalarma() {
        return idAlarma;
    }

    public void setIdalarma(int idAlarma) {
        this.idAlarma = idAlarma;
    }

    public int getIdMedidor() {
        return idMedidor;
    }

    public void setIdMedidor(int idMedidor) {
        this.idMedidor = idMedidor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getValorAlerta() {
        return valorAlerta;
    }

    public void setValorAlerta(int valorAlerta) {
        this.valorAlerta = valorAlerta;
    }

    public int getEstadoAlerta() {
        return estadoAlerta;
    }

    public void setEstadoAlerta(int estadoAlerta) {
        this.estadoAlerta = estadoAlerta;
    }

    public String getNombreAlarma() {
        return nombreAlarma;
    }

    public void setNombreAlarma(String nombreAlarma) {
        this.nombreAlarma = nombreAlarma;
    }

    @Override
    public String toString() {
        return "Alarma{" +
                "idAlarma=" + idAlarma +
                ", idMedidor=" + idMedidor +
                ", nombreAlarma='" + nombreAlarma + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", valorAlerta=" + valorAlerta +
                ", estadoAlerta=" + estadoAlerta +
                '}';
    }
}


