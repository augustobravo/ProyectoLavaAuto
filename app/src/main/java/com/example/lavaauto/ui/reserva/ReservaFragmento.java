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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.servicio.DetalleServicioFragment;
import com.example.lavaauto.ui.servicio.ServicioFragmento;

import java.util.ArrayList;


public class ReservaFragmento extends Fragment {

    private ArrayList<String> listarDomicilio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserva, container, false);

        Button btnSiguiente = (Button) view.findViewById(R.id.idBtnSiguiente1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonSiguiente();
            }
        });

        listarDomicilio =new ArrayList<String>();
        listarDomicilio.add("AV. ZARUMILLA 856 - LA VICTORIA");
        listarDomicilio.add("AV. LAS CAMELIASA 123 - SAN ISIDRO");
        listarDomicilio.add("JR. SAENZ PEÑA 895 - BREÑA");

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idLvDomicilio);
        lv1.setAdapter(adaptador);

        // Inflate the layout for this fragment
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<String> {

        AdaptadorServicios(Activity context) {
            super(context, R.layout.domicilio, listarDomicilio);
        }
        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.domicilio, null);
            TextView textView1 = (TextView)item.findViewById(R.id.idTxtDesDomicilio);
            textView1.setText(listarDomicilio.get(position));
            return item;
        }
    }

   private void eventoBotonSiguiente(){
  /*      Reserva2Fragment fgReserva2 = new Reserva2Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva2);
        ft.commit();*/
    }
}
