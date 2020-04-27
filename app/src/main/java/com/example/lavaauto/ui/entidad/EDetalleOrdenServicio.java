package com.example.lavaauto.ui.entidad;

public class EDetalleOrdenServicio {


    private int OrdenDetalleID;
    private int OrdenID;
    private String FechaRegistro;
    private String HoraRegistro;
    private int EstadoID;
    private String DescripcionEstado;

    public int getOrdenDetalleID() {
        return OrdenDetalleID;
    }

    public void setOrdenDetalleID(int ordenDetalleID) {
        OrdenDetalleID = ordenDetalleID;
    }

    public int getOrdenID() {
        return OrdenID;
    }

    public void setOrdenID(int ordenID) {
        OrdenID = ordenID;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getHoraRegistro() {
        return HoraRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
        HoraRegistro = horaRegistro;
    }

    public int getEstadoID() {
        return EstadoID;
    }

    public void setEstadoID(int estadoID) {
        EstadoID = estadoID;
    }

    public String getDescripcionEstado() {
        return DescripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        DescripcionEstado = descripcionEstado;
    }
}
