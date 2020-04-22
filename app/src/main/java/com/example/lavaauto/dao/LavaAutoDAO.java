package com.example.lavaauto.dao;

import android.os.StrictMode;
import android.util.Log;

import com.example.lavaauto.ui.entidad.EUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
