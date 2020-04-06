package com.example.lavaauto.ui.entidad;

public class EServicio {
    private String  nombre;
    private String imagen;

    public EServicio(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}
