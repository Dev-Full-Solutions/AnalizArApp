package com.dpozzo68.analizarapp.entidades;

import java.io.Serializable;



public class Iluminacion implements Serializable {
    private int idIluminacion;
    private String usuarioEmail;
    private String nombre;
    private String descripcion;
    private int intensidad;
    private boolean encendido;



    public Iluminacion() {
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    @Override
    public String toString() {
        return "Iluminacion{" +
                "usuario=" + usuarioEmail +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", intensidad=" + intensidad +
                ", encendido=" + encendido +
                '}';
    }

    public void setUsuario(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public int getIdIluminacion() {
        return idIluminacion;
    }

    public void setIdIluminacion(int idIluminacion) {
        this.idIluminacion = idIluminacion;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
}

