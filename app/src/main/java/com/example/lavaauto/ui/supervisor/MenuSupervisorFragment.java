package com.example.lavaauto.ui.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lavaauto.R;

public class MenuSupervisorFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_supervisor, container, false);
        ImageButton btnOpcion1 =(ImageButton)view.findViewById(R.id.idBtnSoliServicio);
        ImageButton btnOpcion2 =(ImageButton)view.findViewById(R.id.idBtnProcesoSolicitud);
        ImageButton btnOpcion3 =(ImageButton)view.findViewById(R.id.idBtnMantenimiento);

        btnOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SolicitudServicioFragment fgSupervisor = new SolicitudServicioFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgSupervisor);
                ft.commit();
            }
        });

        btnOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrdenServicioFragment fgOrdenServicio = new OrdenServicioFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgOrdenServicio);
                ft.commit();
            }
        });

        btnOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaServicioFragment fgRegistroServicio = new ListaServicioFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgRegistroServicio);
                ft.commit();
            }
        });

        return view;
    }
}
