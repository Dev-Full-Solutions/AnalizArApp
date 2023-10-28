package com.dpozzo68.analizarapp.entidades;
public class GlobalAlarma {
    private Alarma alarma;

    private static GlobalAlarma instanciaAlarma;

    public static GlobalAlarma getinstanciaAlarma() {
        if (instanciaAlarma == null) {
            instanciaAlarma = new GlobalAlarma();
        }
        return instanciaAlarma;
    }

    private GlobalAlarma() {
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public Alarma getAlarma() {
        return alarma;
    }
}