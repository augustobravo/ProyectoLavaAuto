package com.example.lavaauto.ui.cliente;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EUsuario;
import com.example.lavaauto.ui.utilitario.Constants;


public class ClienteFragmento extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        TextView txtNombre = (TextView)view.findViewById(R.id.txtname);
        TextView txtTipoDocumento = (TextView)view.findViewById(R.id.txtTipodocumento);
        TextView txtDocumento = (TextView)view.findViewById(R.id.txtdni);
        TextView txtFecNacimiento = (TextView)view.findViewById(R.id.txtfechanac);
        TextView txtFecRegistro = (TextView)view.findViewById(R.id.txtfechareg);
        EUsuario eUsuario = Constants.usuario;

        txtNombre.setText(eUsuario.getNombre());
        if(eUsuario.getDocume().length() > 8) {
            txtTipoDocumento.setText("R.U.C.");
        }else{
            txtTipoDocumento.setText("D.N.I.");
        }
        txtDocumento.setText(eUsuario.getDocume());
        txtFecNacimiento.setText(eUsuario.getFecNac());
        txtFecRegistro.setText(eUsuario.getFecReg());
        return view;
    }

}
