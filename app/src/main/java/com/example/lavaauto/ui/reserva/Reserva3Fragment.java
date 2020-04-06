package com.example.lavaauto.ui.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lavaauto.R;


public class Reserva3Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserva3, container, false);

        Button btnAnterior = (Button) view.findViewById(R.id.idBtnAtras1);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonAtras();
            }
        });

        Button btnSiguiente = (Button) view.findViewById(R.id.idBtnSiguiente2);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonSiguiente();
            }
        });

        return view;
    }

    private void eventoBotonAtras(){
        Reserva2Fragment fgReserva2 = new Reserva2Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva2);
        ft.commit();
    }
    private void eventoBotonSiguiente(){

    }
}
