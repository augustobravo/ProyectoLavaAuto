package com.example.lavaauto.ui.reserva;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EAuto;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;


public class Reserva2Fragment extends Fragment {

    private ArrayList<EAuto> listarAutos;
    private int selectedPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserva_2, container, false);
        // Inflate the layout for this fragment

        TextView txtNombre = (TextView) view.findViewById(R.id.txtCliente);
        TextView txtTipoDocumento = (TextView) view.findViewById(R.id.txtTipoDocumento);
        TextView txtDocumento = (TextView) view.findViewById(R.id.txtDocumento2);
        Button btnAgregarAuto = (Button) view.findViewById(R.id.btnRegistrarAuto);
        btnAgregarAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAAuto();
            }
        });
        txtNombre.setText(Constants.usuario.getNombre());
        if( Constants.usuario.getDocume().length() > 8 ){
            txtTipoDocumento.setText("R.U.C.");
        }else{
            txtTipoDocumento.setText("D.N.I.");
        }
        txtDocumento.setText(Constants.usuario.getDocume());
        Button btnAnterior = (Button) view.findViewById(R.id.idBtnAtras);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonAtras();
            }
        });

        Button btnSiguiente = (Button) view.findViewById(R.id.idBtnSiguiente1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonSiguiente();
            }
        });


        listarAutos =new LavaAutoDAO().obtenerAutos(Constants.usuario.getUsuarioID());

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idLvAuto);
        lv1.setAdapter(adaptador);

        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EAuto> {

        AdaptadorServicios(Activity context) {
            super(context, R.layout.auto, listarAutos);
        }
        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.auto, null);
            TextView txtPlaca = (TextView)item.findViewById(R.id.idTxtDesPlaca);
            TextView txtModelo = (TextView)item.findViewById(R.id.idTxtDesModelo);
            TextView txtMarca = (TextView)item.findViewById(R.id.idTxtDesMarca);

            txtPlaca.setText(listarAutos.get(position).getPlaca());
            txtModelo.setText(listarAutos.get(position).getModelo());
            txtMarca.setText(listarAutos.get(position).getMarca());
            ImageButton btnEliminar = (ImageButton) item.findViewById(R.id.idBtnEliminarAuto);
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
                    lavaAutoDAO.eliminarDireccion(listarAutos.get(position).getUsuarioAutoID());
                    // adapter.remove(adapter.getItem(position));
                    notifyDataSetChanged();
                    Toast.makeText(getActivity(),"Registro Eliminado", Toast.LENGTH_LONG).show();
                }
            });

            RadioButton rbSeleccion = (RadioButton)item.findViewById(R.id.idRbSeleccionarAuto);
            rbSeleccion.setChecked(position == selectedPosition);
            rbSeleccion.setTag(position);
            rbSeleccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = (Integer)view.getTag();
                    notifyDataSetChanged();
                    Constants.auto = listarAutos.get(position);
                }
            });
            return item;
        }
    }

    private void eventoBotonAtras(){
        ReservaFragmento fgReserva = new ReservaFragmento();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void eventoBotonSiguiente(){
        Reserva3Fragment fgReserva3 = new Reserva3Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva3);
        ft.commit();
    }

    public void irAAuto(){
        AutoFragment fgAuto = new AutoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgAuto);
        ft.addToBackStack(null);
        ft.commit();
    }
}
