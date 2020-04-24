package com.example.lavaauto.ui.entidad;

public class EDireccion {
    private int UsuarioDirID;
    private int UsuarioID;
    private String Domicilio;
    private String Coordenada;
    private String Distrito;

    public int getUsuarioDirID() {
        return UsuarioDirID;
    }

    public void setUsuarioDirID(int usuarioDirID) {
        UsuarioDirID = usuarioDirID;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getCoordenada() {
        return Coordenada;
    }

    public void setCoordenada(String coordenada) {
        Coordenada = coordenada;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }
}
