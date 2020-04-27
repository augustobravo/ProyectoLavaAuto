package com.example.lavaauto.ui.supervisor;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;


public class ListaServicioFragment extends Fragment {

    private LavaAutoDAO lavaAutoDAO;
    private ArrayList<EServicio> listarServicios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_servicio, container, false);

        lavaAutoDAO = new LavaAutoDAO();

        listarServicios = new LavaAutoDAO().listarServicio();
        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lvServicios= (ListView) view.findViewById(R.id.idLvServicioEditar);
        lvServicios.setAdapter(adaptador);

        ImageButton btnMenuPrincipal =(ImageButton)view.findViewById(R.id.idBtnMenuPrincipal2);
        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuSupervisorFragment fgMenu = new MenuSupervisorFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgMenu);
                ft.commit();
            }
        });

        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EServicio> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.mantenimiento_servicio, listarServicios);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.mantenimiento_servicio, null);
            TextView lblServicioID = (TextView) item.findViewById(R.id.txtServicioIDM);
            TextView lblServicio = (TextView) item.findViewById(R.id.txtNombreServicioM);
            TextView lblPrecio = (TextView) item.findViewById(R.id.txtPrecioM);

            lblServicioID.setText(String.valueOf(listarServicios.get(position).getServicioID()));
            lblServicio.setText(String.valueOf(listarServicios.get(position).getNombreServicio()));
            lblPrecio.setText("S/ "+ String.valueOf(listarServicios.get(position).getPrecio()));
            ImageButton btnEditar = (ImageButton) item.findViewById(R.id.idBtnEditarServicio);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.servicio = listarServicios.get(position);
                    RegistroServicioFragment fgRegistroSocio = new RegistroServicioFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fgRegistroSocio);
                    ft.commit();
                }
            });
            return (item);
        }
    }


}
