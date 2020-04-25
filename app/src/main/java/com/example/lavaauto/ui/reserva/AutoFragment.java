package com.example.lavaauto.ui.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EAuto;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.utilitario.Constants;

public class AutoFragment extends Fragment {

    private EditText txtPlaca ;
    private EditText txtModelo;
    private EditText txtMarca;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        txtPlaca = (EditText) view.findViewById(R.id.txtPlaca);
        txtModelo = (EditText) view.findViewById(R.id.txtModelo);
        txtMarca = (EditText) view.findViewById(R.id.txtMarca);
        Button btnRegistrar = (Button) view.findViewById(R.id.btnRegistrarAuto2);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registraAuto();
            }
        });
        return view ;
    }

    private void registraAuto(){
        LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
        EAuto eAuto = new EAuto();
        eAuto.setUsuarioID(Constants.usuario.getUsuarioID());
        eAuto.setPlaca(txtPlaca.getText().toString());
        eAuto.setModelo(txtModelo.getText().toString());
        eAuto.setMarca(txtMarca.getText().toString());
        lavaAutoDAO.registrarAuto(eAuto);
        irAReserva2();
    }

    private void irAReserva2(){
        Reserva2Fragment fgReserva2 = new Reserva2Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva2);
        ft.addToBackStack(null);
        ft.commit();
    }
}
