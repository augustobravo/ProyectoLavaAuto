package com.example.lavaauto.ui.entidad;

import android.widget.ImageView;

public class EDetalleServicio {

    private String nombre;
    private int idimagen;
    private String detalle;

    public EDetalleServicio(String nombre, int idimagen, String detalle) {
        this.nombre = nombre;
        this.idimagen = idimagen;
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdImagen() {
        return idimagen;
    }
    public String getDetalle() {
        return detalle;
    }
}
