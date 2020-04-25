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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;


public class ReservaFragmento extends Fragment {

    private ArrayList<EDireccion> listarDomicilio;
    private int selectedPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserva, container, false);

        Button btnDireccion = (Button) view.findViewById(R.id.btnRegistrarDireccion);
        Button btnSiguiente = (Button) view.findViewById(R.id.idBtnSiguiente1);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonSiguiente();
            }
        });

        //Listo Los Servicios
        Spinner spinner = (Spinner) view.findViewById(R.id.idSpServicio);
        ArrayList<String> servicios = new ArrayList<>();
        servicios.add("Pack BÁSICO");
        servicios.add("Pack COMPLETO");
        servicios.add("Pack PREMIUM");
        servicios.add("Pack DELUXE");

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, servicios);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(Constants.servicio != null && Constants.servicio.getNombreServicio() != null){
            int spinnerPosition = adapter.getPosition(Constants.servicio.getNombreServicio());
            spinner.setSelection(spinnerPosition);
        }

        listarDomicilio = new LavaAutoDAO().obtenerDirecciones(Constants.usuario.getUsuarioID());

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lvDomicilio = (ListView) view.findViewById(R.id.idLvDomicilio);
        lvDomicilio.setAdapter(adaptador);


        btnDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irADireccion();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EDireccion> {

        AdaptadorServicios(Activity context) {
            super(context, R.layout.domicilio, listarDomicilio);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.domicilio, null);
            TextView txtDomicilio = (TextView)item.findViewById(R.id.idTxtDesDomicilio);
            TextView txtDistrito = (TextView)item.findViewById(R.id.idTxtDesDistrito);

            txtDomicilio.setText(listarDomicilio.get(position).getDomicilio());
            txtDistrito.setText(listarDomicilio.get(position).getDistrito());
            ImageButton btnEliminar = (ImageButton) item.findViewById(R.id.btnEliminarDireccion);
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
                    lavaAutoDAO.eliminarDireccion(listarDomicilio.get(position).getUsuarioDirID());
                   // adapter.remove(adapter.getItem(position));
                    notifyDataSetChanged();
                    Toast.makeText(getActivity(),"Registro Eliminado", Toast.LENGTH_LONG).show();
                }
            });

            RadioButton rbSeleccion = (RadioButton)item.findViewById(R.id.idRbSeleccionar);
            rbSeleccion.setChecked(position == selectedPosition);
            rbSeleccion.setTag(position);
            rbSeleccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = (Integer)view.getTag();
                    notifyDataSetChanged();
                    Constants.direccion = listarDomicilio.get(position);
                }
            });

            return item;
        }
    }

   private void eventoBotonSiguiente(){
        Reserva2Fragment fgReserva2 = new Reserva2Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva2);
        ft.commit();
    }

    public void irADireccion(){
        DomicilioFragment fgDomicilio = new DomicilioFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgDomicilio);
        ft.addToBackStack(null);
        ft.commit();
    }
}
