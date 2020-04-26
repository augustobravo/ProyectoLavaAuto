package com.example.lavaauto.ui.entidad;

import java.sql.Date;
import java.sql.Time;

public class EOrdenServicio {
    private int OrdenID;
    private EServicio Servicio;
    private EUsuario Usuario;
    private EAuto UsuarioAuto;
    private EDireccion UsuarioDir;
    private String FecReserva;
    private String HorReserva;
    private int Estado;
    private int FormaPagoID;

    public int getOrdenID() {
        return OrdenID;
    }

    public void setOrdenID(int ordenID) {
        OrdenID = ordenID;
    }

    public EServicio getServicio() {
        return Servicio;
    }

    public void setServicio(EServicio servicio) {
        Servicio = servicio;
    }

    public EUsuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(EUsuario usuario) {
        Usuario = usuario;
    }

    public EAuto getUsuarioAuto() {
        return UsuarioAuto;
    }

    public void setUsuarioAuto(EAuto usuarioAuto) {
        UsuarioAuto = usuarioAuto;
    }

    public EDireccion getUsuarioDir() {
        return UsuarioDir;
    }

    public void setUsuarioDir(EDireccion usuarioDir) {
        UsuarioDir = usuarioDir;
    }

    public String getFecReserva() {
        return FecReserva;
    }

    public void setFecReserva(String fecReserva) {
        FecReserva = fecReserva;
    }

    public String getHorReserva() {
        return HorReserva;
    }

    public void setHorReserva(String horReserva) {
        HorReserva = horReserva;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getFormaPagoID() {
        return FormaPagoID;
    }

    public void setFormaPagoID(int formaPagoID) {
        FormaPagoID = formaPagoID;
    }
}
