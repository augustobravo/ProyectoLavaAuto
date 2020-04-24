package com.example.lavaauto.dao;

import android.os.StrictMode;
import android.util.Log;

import com.example.lavaauto.ui.entidad.EAuto;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.entidad.EUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Log.i("obtenerUsuario==> ", ex.getMessage());
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
            pst.setString(3, auto.getMarca());
            filas = pst.executeUpdate();

        }catch (SQLException ex){
            Log.i("registrarAuto==> ", ex.getMessage());
        }
        return filas;
    }
}
