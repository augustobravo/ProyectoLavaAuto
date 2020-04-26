package com.example.lavaauto.dao;

import android.os.StrictMode;
import android.util.Log;

import com.example.lavaauto.ui.entidad.EAuto;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.entidad.EReserva;
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
import java.util.List;

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

    public int registraReserva(EReserva eReserva){
        int filas = 0;
        try {
            String sql ="insert into RESERVA values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, eReserva.getServicio().getServicioID());
            pst.setInt(2, eReserva.getUsuario().getUsuarioID());
            pst.setInt(3, eReserva.getUsuarioAuto().getUsuarioAutoID());
            pst.setInt(4, eReserva.getUsuarioDir().getUsuarioDirID());
            pst.setString(5,eReserva.getFecReserva());
            pst.setString(6,eReserva.getHorReserva());
            pst.setInt(7, eReserva.getEstado());
            pst.setInt(8,eReserva.getFormaPagoID());

            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("registraReserva==> ", ex.getMessage());
        }
        return filas;
    }

    public ArrayList<EOrdenServicio> obtenerOrdenesServicio(int UsuarioID){
        ArrayList<EOrdenServicio> ordenServicios = new ArrayList<EOrdenServicio>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "select ReservaID, UsuarioID, FecReserva, Estado from RESERVA where UsuarioID = "+ UsuarioID;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EOrdenServicio EordenServicio = new EOrdenServicio();
                EordenServicio.setReservaID(rs.getInt("ReservaID"));
                EordenServicio.setUsuarioID(rs.getInt("UsuarioID"));
                EordenServicio.setFecReserva(rs.getDate("FecReserva"));
                EordenServicio.setEstado(rs.getInt("Estado"));
                ordenServicios.add(EordenServicio);
            }
        }catch (SQLException ex) {
            Log.i("obtenerOS==> ", ex.getMessage());
        }
        return ordenServicios;
    }


    public ArrayList<EReserva> listarReservaRegistradas(){
        ArrayList<EReserva> reservas = new ArrayList<EReserva>();
        try {
            Statement st = conectarBD().createStatement();
            String sql = "SELECT A.ReservaID, A.ServicioID, B.Descripcion, B.Precio, A.UsuarioID,\n" +
                    "       C.Docume, C.Nombre, A.UsuarioDirID, D.Direccion, D.Distrito, A.UsuarioAutoID,\n" +
                    "       E.Placa, E.Modelo, E.Marca, A.FecReserva, A.HorReserva, A.Estado, A.FormaPagoID \n" +
                    "FROM [dbo].[RESERVA] A\n" +
                    "    INNER JOIN [dbo].[SERVICIO] B ON A.ServicioID = B.ServicioID\n" +
                    "    INNER JOIN [dbo].[USUARIO] C ON A.UsuarioID = C.UsuarioID\n" +
                    "    INNER JOIN [dbo].[USUARIODIRECCION] D ON A.UsuarioDirID = D.UsuarioDirID\n" +
                    "    INNER JOIN [dbo].[USUARIOAUTO] E ON A.UsuarioAutoID = E.UsuarioAutoID WHERE A.Estado = 1";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                EReserva eReserva = new EReserva();
                eReserva.setReservaID(rs.getInt("ReservaID"));
                eReserva.setServicio(new EServicio());
                eReserva.getServicio().setServicioID(rs.getInt("ServicioID"));
                eReserva.getServicio().setNombreServicio(rs.getString("Descripcion"));
                eReserva.getServicio().setPrecio(rs.getDouble("Precio"));
                eReserva.setUsuario(new EUsuario());
                eReserva.getUsuario().setUsuarioID(rs.getInt("UsuarioID"));
                eReserva.getUsuario().setDocume(rs.getString("Docume"));
                eReserva.getUsuario().setNombre(rs.getString("Nombre"));
                eReserva.setUsuarioDir(new EDireccion());
                eReserva.getUsuarioDir().setUsuarioDirID(rs.getInt("UsuarioDirID"));
                eReserva.getUsuarioDir().setDomicilio(rs.getString("Direccion"));
                eReserva.getUsuarioDir().setDistrito(rs.getString("Distrito"));
                eReserva.setUsuarioAuto(new EAuto());
                eReserva.getUsuarioAuto().setUsuarioAutoID(rs.getInt("UsuarioAutoID"));
                eReserva.getUsuarioAuto().setPlaca(rs.getString("Placa"));
                eReserva.getUsuarioAuto().setModelo(rs.getString("Modelo"));
                eReserva.getUsuarioAuto().setMarca(rs.getString("Marca"));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(rs.getDate("FecReserva"));
                eReserva.setFecReserva(strDate);
                dateFormat = new SimpleDateFormat("HH:mm");
                String strTime = dateFormat.format(rs.getTime("HorReserva"));
                eReserva.setHorReserva(strTime);
                eReserva.setEstado(rs.getInt("Estado"));
                eReserva.setFormaPagoID(rs.getInt("FormaPagoID"));

                reservas.add(eReserva);
            }
        }catch (SQLException ex) {
            Log.i("listarReserva==> ", ex.getMessage());
        }
        return reservas;
    }

    public int actualizarEstadoReserva(int ReservaID, int EstadoID){
        int filas = 0;
        try {
            String sql ="Update RESERVA set Estado = ? where ReservaID = ? ";
            PreparedStatement pst= conectarBD().prepareStatement(sql);
            pst.setInt(1, EstadoID);
            pst.setInt(2, ReservaID);
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("actualizarEstadoRes==> ", ex.getMessage());
        }
        return filas;
    }
}
