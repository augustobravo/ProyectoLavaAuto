package com.example.lavaauto.ui.entidad;

import java.util.Date;

public class EUsuario {

    private int UsuarioID;
    private String Docume;
    private String Nombre;
    private Date FecNac;
    private String Password;
    private Date FecReg;

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getDocume() {
        return Docume;
    }

    public void setDocume(String docume) {
        Docume = docume;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFecNac() {
        return FecNac;
    }

    public void setFecNac(Date fecNac) {
        FecNac = fecNac;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getFecReg() {
        return FecReg;
    }

    public void setFecReg(Date fecReg) {
        FecReg = fecReg;
    }
}
