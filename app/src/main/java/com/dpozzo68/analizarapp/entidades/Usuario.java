package com.dpozzo68.analizarapp.entidades;


public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String celular;
    private boolean habilitado = true;
    private String fechaAlta;

    public Usuario() {
    }

    public Usuario(String email, String nombre, String apellido, String celular, boolean habilitado, String fechaAlta) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.habilitado = habilitado;
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
