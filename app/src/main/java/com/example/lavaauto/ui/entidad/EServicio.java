package com.example.lavaauto.ui.entidad;

import java.util.List;

public class EServicio {
    private int ServicioID;
    private String  NombreServicio;
    private double Precio;
    private int ImagenID;
    private String Detalle;

    public void setServicioID(int ServicioID) {
       this.ServicioID = ServicioID;
    }

    public void setNombreServicio(String NombreServicio) {
        this.NombreServicio = NombreServicio;
    }

    public void setPrecio(double Precio){
        this.Precio = Precio;
    }

    public void setImagenID(int ImagenID) {
        this.ImagenID = ImagenID;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public int getServicioID() {
        return ServicioID;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public double getPrecio() {
        return Precio;
    }

    public int getImagenID() {
        return ImagenID;
    }

    public String getDetalle() {
        return Detalle;
    }

    @Override
    public String toString() {
        return NombreServicio;
    }
}
