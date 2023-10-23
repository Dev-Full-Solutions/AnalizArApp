package com.dpozzo68.analizarapp.entidades;
public class GlobalUsuario {
    private Usuario usuario;

    private static GlobalUsuario instanciaUsuario;

    public static GlobalUsuario getInstanciaUsuario() {
        if (instanciaUsuario == null) {
            instanciaUsuario = new GlobalUsuario();
        }
        return instanciaUsuario;
    }

    private GlobalUsuario() {
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
