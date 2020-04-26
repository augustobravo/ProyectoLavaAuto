package com.example.lavaauto.ui.entidad;

import java.util.Date;

public class EOrdenServicio {

    private int ReservaID;
    private int ServicioID;
    private int UsuarioID;
    private String FecReserva;
    private int Estado;

    public String getFecReserva() {
        return FecReserva;
    }

    public void setFecReserva(String fecReserva) {
        FecReserva = fecReserva;
    }


    public int getServicioID() {
        return ServicioID;
    }

    public void setServicioID(int servicioID) {
        ServicioID = servicioID;
    }

    public int getReservaID() {
        return ReservaID;
    }

    public void setReservaID(int reservaID) {
        ReservaID = reservaID;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }



    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
