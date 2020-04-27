package com.example.lavaauto.dao;

import android.os.StrictMode;
import android.util.Log;

import com.example.lavaauto.ui.entidad.EAuto;
import com.example.lavaauto.ui.entidad.EDetalleOrdenServicio;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.entidad.EUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LavaAutoDAO {

    public Connection conectarBD(){
        Connection conexion = null;

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://upcmoviles.database.windows.net:1433/lavado;user=abravo@upcmoviles;password=Upc123456;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        }catch (Exception ex){
            Log.i("conectarBD==> ", ex.getMessage());
        }

        return conexion;
    }

    public EUsuario obtenerUsuario(String documento, String password){
        EUsuario usuarioEncontrado = null;
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select UsuarioID, Docume, Nombre, FecNac, Password, FecReg from usuario where Docume = '"+ documento +"' and Password = '" + password+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                usuarioEncontrado = new EUsuario();
                usuarioEncontrado.setUsuarioID(rs.getInt("UsuarioID"));
                usuarioEncontrado.setDocume(rs.getString("Docume"));
                usuarioEncontrado.setNombre(rs.getString("Nombre"));
            }
        }catch (SQLException ex) {
            Log.i("obtenerUsuario==> ", ex.getMessage());
        }
        return usuarioEncontrado;
    }

    public int registrarDireccion(EDireccion direccion){
        int filas = 0;
        try {
            String sql ="insert into USUARIODIRECCION values (?,?,?)";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, direccion.getUsuarioID());
            pst.setString(2, direccion.getDomicilio());
            pst.setString(3, direccion.getDistrito());
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("registrarDireccion==> ", ex.getMessage());
        }
        return filas;
    }

    public int eliminarDireccion(int UsuarioDirID){
        int filas = 0;
        try {
            String sql ="delete from USUARIODIRECCION where UsuarioDirID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, UsuarioDirID);
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("eliminarDireccion==> ", ex.getMessage());
        }
        return filas;
    }

    public ArrayList<EDireccion> obtenerDirecciones(int UsuarioID){
        ArrayList<EDireccion> dirrecciones = new ArrayList<EDireccion>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select UsuarioID, UsuarioDirID, Direccion, Distrito from USUARIODIRECCION where UsuarioID = "+ UsuarioID;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EDireccion eDireccion = new EDireccion();
                eDireccion.setUsuarioID(rs.getInt("UsuarioID"));
                eDireccion.setUsuarioDirID(rs.getInt("UsuarioDirID"));
                eDireccion.setDomicilio(rs.getString("Direccion"));
                eDireccion.setDistrito(rs.getString("Distrito"));
                dirrecciones.add(eDireccion);
            }
        }catch (SQLException ex) {
            Log.i("obtenerDirecciones==> ", ex.getMessage());
        }
        return dirrecciones;
    }

    public int registrarAuto(EAuto auto){
        int filas = 0;
        try {
            String sql ="insert into USUARIOAUTO values (?,?,?,?)";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, auto.getUsuarioID());
            pst.setString(2, auto.getPlaca());
            pst.setString(3, auto.getModelo());
            pst.setString(4, auto.getMarca());
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("registrarAuto==> ", ex.getMessage());
        }
        return filas;
    }

    public int eliminarAuto(int UsuarioAutoID){
        int filas = 0;
        try {
            String sql ="delete from USUARIOAUTO where UsuarioAutoID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, UsuarioAutoID);
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("eliminarAuto==> ", ex.getMessage());
        }
        return filas;
    }

    public ArrayList<EAuto> obtenerAutos(int UsuarioID){
        ArrayList<EAuto> dirrecciones = new ArrayList<EAuto>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select UsuarioID, UsuarioAutoID, Placa, Modelo, Marca from USUARIOAUTO where UsuarioID = "+ UsuarioID;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EAuto eAuto = new EAuto();
                eAuto.setUsuarioID(rs.getInt("UsuarioID"));
                eAuto.setUsuarioAutoID(rs.getInt("UsuarioAutoID"));
                eAuto.setPlaca(rs.getString("Placa"));
                eAuto.setModelo(rs.getString("Modelo"));
                eAuto.setMarca(rs.getString("Marca"));

                dirrecciones.add(eAuto);
            }
        }catch (SQLException ex) {
            Log.i("obtenerAutos==> ", ex.getMessage());
        }
        return dirrecciones;
    }

    public int registraOrdenSericio(EOrdenServicio eOrdenServicio){
        int filas = 0;
        try {
            String sql ="insert into ORDENSERVICIO values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, eOrdenServicio.getServicio().getServicioID());
            pst.setInt(2, eOrdenServicio.getUsuario().getUsuarioID());
            pst.setInt(3, eOrdenServicio.getUsuarioAuto().getUsuarioAutoID());
            pst.setInt(4, eOrdenServicio.getUsuarioDir().getUsuarioDirID());
            pst.setString(5, eOrdenServicio.getFecReserva());
            pst.setString(6, eOrdenServicio.getHorReserva());
            pst.setInt(7, eOrdenServicio.getEstado());
            pst.setInt(8, eOrdenServicio.getFormaPagoID());

            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("registraReserva==> ", ex.getMessage());
        }
        return filas;
    }

    public EDetalleOrdenServicio obtenerFechaMaximaOrdenServicio(int OrdenID){
        EDetalleOrdenServicio detalleOrdenServicio = null;

        try {
            Statement st = conectarBD().createStatement();

            String sql = "Select MAX(FechaRegistro) as Fecha, MAX(HoraRegistro) As Hora from [dbo].[DETALLEORDENSERVICIO] Where OrdenID = " +  OrdenID ;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                detalleOrdenServicio = new EDetalleOrdenServicio();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(rs.getDate("Fecha"));
                detalleOrdenServicio.setFechaRegistro(strDate);
                dateFormat = new SimpleDateFormat("HH:mm");
                String strTime = dateFormat.format(rs.getTime("Hora"));
                detalleOrdenServicio.setHoraRegistro(strTime);
            }
        }catch (SQLException ex) {
            Log.i("obtenerOS==> ", ex.getMessage());
        }
            return detalleOrdenServicio;
        }

    public ArrayList<EOrdenServicio> obtenerOrdenesServicioCliente(int UsuarioID){
        ArrayList<EOrdenServicio> ordenServicios = new ArrayList<EOrdenServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "SELECT A.OrdenID, A.ServicioID, B.Descripcion, B.Precio, A.UsuarioID,\n" +
                    "       C.Docume, C.Nombre, A.UsuarioDirID, D.Direccion, D.Distrito, A.UsuarioAutoID,\n" +
                    "       E.Placa, E.Modelo, E.Marca, \n" +
                    "       A.Estado, A.FormaPagoID\n" +
                    "FROM [dbo].[ORDENSERVICIO] A\n" +
                    "    INNER JOIN [dbo].[SERVICIO] B ON A.ServicioID = B.ServicioID\n" +
                    "    INNER JOIN [dbo].[USUARIO] C ON A.UsuarioID = C.UsuarioID\n" +
                    "    INNER JOIN [dbo].[USUARIODIRECCION] D ON A.UsuarioDirID = D.UsuarioDirID\n" +
                    "    INNER JOIN [dbo].[USUARIOAUTO] E ON A.UsuarioAutoID = E.UsuarioAutoID \n" +
                    "WHERE A.UsuarioID = " + UsuarioID;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EOrdenServicio eOrdenServicio = new EOrdenServicio();
                eOrdenServicio.setOrdenID(rs.getInt("OrdenID"));
                eOrdenServicio.setServicio(new EServicio());
                eOrdenServicio.getServicio().setServicioID(rs.getInt("ServicioID"));
                eOrdenServicio.getServicio().setNombreServicio(rs.getString("Descripcion"));
                eOrdenServicio.getServicio().setPrecio(rs.getDouble("Precio"));
                eOrdenServicio.setUsuario(new EUsuario());
                eOrdenServicio.getUsuario().setUsuarioID(rs.getInt("UsuarioID"));
                eOrdenServicio.getUsuario().setDocume(rs.getString("Docume"));
                eOrdenServicio.getUsuario().setNombre(rs.getString("Nombre"));
                eOrdenServicio.setUsuarioDir(new EDireccion());
                eOrdenServicio.getUsuarioDir().setUsuarioDirID(rs.getInt("UsuarioDirID"));
                eOrdenServicio.getUsuarioDir().setDomicilio(rs.getString("Direccion"));
                eOrdenServicio.getUsuarioDir().setDistrito(rs.getString("Distrito"));
                eOrdenServicio.setUsuarioAuto(new EAuto());
                eOrdenServicio.getUsuarioAuto().setUsuarioAutoID(rs.getInt("UsuarioAutoID"));
                eOrdenServicio.getUsuarioAuto().setPlaca(rs.getString("Placa"));
                eOrdenServicio.getUsuarioAuto().setModelo(rs.getString("Modelo"));
                eOrdenServicio.getUsuarioAuto().setMarca(rs.getString("Marca"));

                EDetalleOrdenServicio detalleOrdenServicio = obtenerFechaMaximaOrdenServicio(eOrdenServicio.getOrdenID());
                eOrdenServicio.setFecReserva(detalleOrdenServicio.getFechaRegistro());
                eOrdenServicio.setHorReserva(detalleOrdenServicio.getHoraRegistro());
                eOrdenServicio.setEstado(rs.getInt("Estado"));
                switch (eOrdenServicio.getEstado()){
                    case 0:
                        eOrdenServicio.setDesEstado("RECHAZADO");
                        break;
                    case 1:
                        eOrdenServicio.setDesEstado("REGISTRADO");
                        break;
                    case 2:
                        eOrdenServicio.setDesEstado("APROBADA");
                        break;
                    case 3:
                        eOrdenServicio.setDesEstado("INICIADO");
                        break;
                    case 4:
                        eOrdenServicio.setDesEstado("LAVADO");
                        break;
                    case 5:
                        eOrdenServicio.setDesEstado("ENCERADO");
                        break;
                    case 6:
                        eOrdenServicio.setDesEstado("CONCLUIDO");
                        break;
                }
                eOrdenServicio.setFormaPagoID(rs.getInt("FormaPagoID"));
                ordenServicios.add(eOrdenServicio);
            }
        }catch (SQLException ex) {
            Log.i("obtenerOS==> ", ex.getMessage());
        }
        return ordenServicios;
    }

    public ArrayList<EOrdenServicio> listarSolicitudesServicio(){
        ArrayList<EOrdenServicio> reservas = new ArrayList<EOrdenServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "SELECT A.OrdenID, A.ServicioID, B.Descripcion, B.Precio, A.UsuarioID,\n" +
                    "       C.Docume, C.Nombre, A.UsuarioDirID, D.Direccion, D.Distrito, A.UsuarioAutoID,\n" +
                    "       E.Placa, E.Modelo, E.Marca, A.FecReserva, A.HorReserva, A.Estado, A.FormaPagoID \n" +
                    "FROM [dbo].[ORDENSERVICIO] A\n" +
                    "    INNER JOIN [dbo].[SERVICIO] B ON A.ServicioID = B.ServicioID\n" +
                    "    INNER JOIN [dbo].[USUARIO] C ON A.UsuarioID = C.UsuarioID\n" +
                    "    INNER JOIN [dbo].[USUARIODIRECCION] D ON A.UsuarioDirID = D.UsuarioDirID\n" +
                    "    INNER JOIN [dbo].[USUARIOAUTO] E ON A.UsuarioAutoID = E.UsuarioAutoID WHERE A.Estado = 1";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EOrdenServicio eOrdenServicio = new EOrdenServicio();
                eOrdenServicio.setOrdenID(rs.getInt("OrdenID"));
                eOrdenServicio.setServicio(new EServicio());
                eOrdenServicio.getServicio().setServicioID(rs.getInt("ServicioID"));
                eOrdenServicio.getServicio().setNombreServicio(rs.getString("Descripcion"));
                eOrdenServicio.getServicio().setPrecio(rs.getDouble("Precio"));
                eOrdenServicio.setUsuario(new EUsuario());
                eOrdenServicio.getUsuario().setUsuarioID(rs.getInt("UsuarioID"));
                eOrdenServicio.getUsuario().setDocume(rs.getString("Docume"));
                eOrdenServicio.getUsuario().setNombre(rs.getString("Nombre"));
                eOrdenServicio.setUsuarioDir(new EDireccion());
                eOrdenServicio.getUsuarioDir().setUsuarioDirID(rs.getInt("UsuarioDirID"));
                eOrdenServicio.getUsuarioDir().setDomicilio(rs.getString("Direccion"));
                eOrdenServicio.getUsuarioDir().setDistrito(rs.getString("Distrito"));
                eOrdenServicio.setUsuarioAuto(new EAuto());
                eOrdenServicio.getUsuarioAuto().setUsuarioAutoID(rs.getInt("UsuarioAutoID"));
                eOrdenServicio.getUsuarioAuto().setPlaca(rs.getString("Placa"));
                eOrdenServicio.getUsuarioAuto().setModelo(rs.getString("Modelo"));
                eOrdenServicio.getUsuarioAuto().setMarca(rs.getString("Marca"));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(rs.getDate("FecReserva"));
                eOrdenServicio.setFecReserva(strDate);
                dateFormat = new SimpleDateFormat("HH:mm");
                String strTime = dateFormat.format(rs.getTime("HorReserva"));
                eOrdenServicio.setHorReserva(strTime);
                eOrdenServicio.setEstado(rs.getInt("Estado"));
                eOrdenServicio.setFormaPagoID(rs.getInt("FormaPagoID"));

                reservas.add(eOrdenServicio);
            }
        }catch (SQLException ex) {
            Log.i("listarReserva==> ", ex.getMessage());
        }
        return reservas;
    }

    public ArrayList<EOrdenServicio> listarOrdenServicios(){
        ArrayList<EOrdenServicio> reservas = new ArrayList<EOrdenServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "SELECT A.OrdenID, A.ServicioID, B.Descripcion, B.Precio, A.UsuarioID,\n" +
                    " C.Docume, C.Nombre, A.UsuarioDirID, D.Direccion, D.Distrito, A.UsuarioAutoID,\n" +
                    " E.Placa, E.Modelo, E.Marca, A.FecReserva, A.HorReserva, A.Estado, A.FormaPagoID \n" +
                    "  FROM [dbo].[ORDENSERVICIO] A\n" +
                    "    INNER JOIN [dbo].[SERVICIO] B ON A.ServicioID = B.ServicioID\n" +
                    "    INNER JOIN [dbo].[USUARIO] C ON A.UsuarioID = C.UsuarioID\n" +
                    "    INNER JOIN [dbo].[USUARIODIRECCION] D ON A.UsuarioDirID = D.UsuarioDirID\n" +
                    "    INNER JOIN [dbo].[USUARIOAUTO] E ON A.UsuarioAutoID = E.UsuarioAutoID WHERE A.Estado > 1";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EOrdenServicio eOrdenServicio = new EOrdenServicio();
                eOrdenServicio.setOrdenID(rs.getInt("OrdenID"));
                eOrdenServicio.setServicio(new EServicio());
                eOrdenServicio.getServicio().setServicioID(rs.getInt("ServicioID"));
                eOrdenServicio.getServicio().setNombreServicio(rs.getString("Descripcion"));
                eOrdenServicio.getServicio().setPrecio(rs.getDouble("Precio"));
                eOrdenServicio.setUsuario(new EUsuario());
                eOrdenServicio.getUsuario().setUsuarioID(rs.getInt("UsuarioID"));
                eOrdenServicio.getUsuario().setDocume(rs.getString("Docume"));
                eOrdenServicio.getUsuario().setNombre(rs.getString("Nombre"));
                eOrdenServicio.setUsuarioDir(new EDireccion());
                eOrdenServicio.getUsuarioDir().setUsuarioDirID(rs.getInt("UsuarioDirID"));
                eOrdenServicio.getUsuarioDir().setDomicilio(rs.getString("Direccion"));
                eOrdenServicio.getUsuarioDir().setDistrito(rs.getString("Distrito"));
                eOrdenServicio.setUsuarioAuto(new EAuto());
                eOrdenServicio.getUsuarioAuto().setUsuarioAutoID(rs.getInt("UsuarioAutoID"));
                eOrdenServicio.getUsuarioAuto().setPlaca(rs.getString("Placa"));
                eOrdenServicio.getUsuarioAuto().setModelo(rs.getString("Modelo"));
                eOrdenServicio.getUsuarioAuto().setMarca(rs.getString("Marca"));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(rs.getDate("FecReserva"));
                eOrdenServicio.setFecReserva(strDate);
                dateFormat = new SimpleDateFormat("HH:mm");
                String strTime = dateFormat.format(rs.getTime("HorReserva"));
                eOrdenServicio.setHorReserva(strTime);
                eOrdenServicio.setEstado(rs.getInt("Estado"));

                switch (eOrdenServicio.getEstado()){
                    case 0:
                        eOrdenServicio.setDesEstado("RECHAZADO");
                        break;
                    case 1:
                        eOrdenServicio.setDesEstado("REGISTRADO");
                        break;
                    case 2:
                        eOrdenServicio.setDesEstado("APROBADA");
                        break;
                    case 3:
                        eOrdenServicio.setDesEstado("INICIADO");
                        break;
                    case 4:
                        eOrdenServicio.setDesEstado("LAVADO");
                        break;
                    case 5:
                        eOrdenServicio.setDesEstado("ENCERADO");
                        break;
                    case 6:
                        eOrdenServicio.setDesEstado("CONCLUIDO");
                        break;
                }
                eOrdenServicio.setFormaPagoID(rs.getInt("FormaPagoID"));

                reservas.add(eOrdenServicio);
            }
        }catch (SQLException ex) {
            Log.i("listarReserva==> ", ex.getMessage());
        }
        return reservas;
    }

    public ArrayList<EDetalleOrdenServicio> listarDetalleOrdenServicios(int OrdenID){
        ArrayList<EDetalleOrdenServicio> detalleOrdenServicios = new ArrayList<EDetalleOrdenServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "SELECT OrdenID, OrdenDetalleID, FechaRegistro, HoraRegistro, EstadoServicio from DETALLEORDENSERVICIO Where OrdenID = " + OrdenID;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EDetalleOrdenServicio eDetalleOrdenServicio = new EDetalleOrdenServicio();
                eDetalleOrdenServicio.setOrdenID(rs.getInt("OrdenID"));
                eDetalleOrdenServicio.setOrdenDetalleID(rs.getInt("OrdenDetalleID"));

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(rs.getDate("FechaRegistro"));
                eDetalleOrdenServicio.setFechaRegistro(strDate);
                dateFormat = new SimpleDateFormat("HH:mm");
                String strTime = dateFormat.format(rs.getTime("HoraRegistro"));
                eDetalleOrdenServicio.setHoraRegistro(strTime);
                eDetalleOrdenServicio.setEstadoID(rs.getInt("EstadoServicio"));

                switch (eDetalleOrdenServicio.getEstadoID()){
                    case 0:
                        eDetalleOrdenServicio.setDescripcionEstado("RECHAZADO");
                        break;
                    case 1:
                        eDetalleOrdenServicio.setDescripcionEstado("REGISTRADO");
                        break;
                    case 2:
                        eDetalleOrdenServicio.setDescripcionEstado("APROBADA");
                        break;
                    case 3:
                        eDetalleOrdenServicio.setDescripcionEstado("INICIADO");
                        break;
                    case 4:
                        eDetalleOrdenServicio.setDescripcionEstado("LAVADO");
                        break;
                    case 5:
                        eDetalleOrdenServicio.setDescripcionEstado("ENCERADO");
                        break;
                    case 6:
                        eDetalleOrdenServicio.setDescripcionEstado("CONCLUIDO");
                        break;
                }

                detalleOrdenServicios.add(eDetalleOrdenServicio);
            }
        }catch (SQLException ex) {
            Log.i("listarReserva==> ", ex.getMessage());
        }
        return detalleOrdenServicios;
    }

    public int actualizarEstadoOrdenServicio(int OrdenID, int EstadoID){
        int filas = 0;
        try {
            String sql ="Update ORDENSERVICIO set Estado = ? where OrdenID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, EstadoID);
            pst.setInt(2, OrdenID);
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("actualizarEstadoRes==> ", ex.getMessage());
        }
        return filas;
    }

    public int insertarHistorialEstadoOrdenServicio(int OrdenID, String Fecha , String Hora, int EstadoID){
        int filas = 0;
        try {
            String sql ="insert into DETALLEORDENSERVICIO values (?,?,?,?)";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, OrdenID);
            pst.setString(2, Fecha);
            pst.setString(3, Hora);
            pst.setInt(4, EstadoID);

            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("actualizarEstadoRes==> ", ex.getMessage());
        }
        return filas;
    }

    public int reprogramarOrdenServicio(int OrdenID, String Fecha , String Hora){
        int filas = 0;
        try {
            String sql ="Update ORDENSERVICIO set FecReserva = ? , HorReserva = ? where OrdenID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setString(1, Fecha);
            pst.setString(2, Hora);
            pst.setInt(3, OrdenID);
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("reprogramacion==> ", ex.getMessage());
        }
        return filas;
    }

    public int obtenerMaximaOrdenID (){
        int OrdenIDMaximo = 0;
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select max(OrdenID) AS MaxOrdenID from ORDENSERVICIO";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                OrdenIDMaximo = rs.getInt("MaxOrdenID");
            }
        }catch (SQLException ex) {
            Log.i("obtenerUsuario==> ", ex.getMessage());
        }
        return OrdenIDMaximo;
    }

    public ArrayList<EServicio> listarServicio(){
        ArrayList<EServicio> servicios = new ArrayList<EServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select ServicioID, Descripcion, Precio, ImagenID from SERVICIO";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EServicio eServicio = new EServicio();
                eServicio.setServicioID(rs.getInt("ServicioID"));
                eServicio.setNombreServicio(rs.getString("Descripcion"));
                eServicio.setPrecio(rs.getDouble("Precio"));
                eServicio.setImagenID(rs.getInt("ImagenID"));
                servicios.add(eServicio);
            }
        }catch (SQLException ex) {
            Log.i("listarServicio==> ", ex.getMessage());
        }
        return servicios;
    }

    public int actualizaPrecioServicio(int ServicioID, double Precio){
        int filas = 0;
        try {
            String sql ="update SERVICIO set Precio = ? where ServicioID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setDouble(1, Precio);
            pst.setInt(2, ServicioID);

            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("actualizaservicio==> ", ex.getMessage());
        }
        return filas;
    }

}
