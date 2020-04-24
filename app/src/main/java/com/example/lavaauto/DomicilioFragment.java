package com.example.lavaauto;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EDireccion;
import com.example.lavaauto.ui.reserva.ReservaFragmento;
import com.example.lavaauto.ui.utilitario.Constants;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.Objects;

public class DomicilioFragment extends Fragment {

    Spinner spinner;
    EditText txtDomicilio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_domicilio, container, false);
        txtDomicilio = (EditText) view.findViewById(R.id.txtDireccion);

        spinner = (Spinner) view.findViewById(R.id.spnDistrito);
        ArrayList<String> distritos = new ArrayList<>();
        distritos.add("ANCON");
        distritos.add("ATE");
        distritos.add("BARRANCO");
        distritos.add("BREÑA");
        distritos.add("CARABAYLLO");
        distritos.add("CHACLACAYO");
        distritos.add("CHORRILLOS");
        distritos.add("CIENEGUILLA");
        distritos.add("COMAS");
        distritos.add("EL AGUSTINO");
        distritos.add("INDEPENDENCIA");
        distritos.add("JESUS MARIA");
        distritos.add("LA MOLINA");
        distritos.add("LA VICTORIA");
        distritos.add("LINCE");
        distritos.add("LOS OLIVOS");
        distritos.add("LURIGANCHO-CHOSICA");
        distritos.add("LURIN");
        distritos.add("MAGDALENA DEL MAR");
        distritos.add("PUEBLO LIBRE");
        distritos.add("MIRAFLORES");
        distritos.add("PACHACAMAC");
        distritos.add("PUCUSANA");
        distritos.add("PUENTE PIEDRA");
        distritos.add("PUNTA HERMOSA");
        distritos.add("PUNTA NEGRA");
        distritos.add("RIMAC");
        distritos.add("SAN BARTOLO");
        distritos.add("SAN BORJA");
        distritos.add("SAN ISIDRO");
        distritos.add("SAN JUAN DE LURIGANCHO");
        distritos.add("SAN JUAN DE MIRAFLORES");
        distritos.add("SAN LUIS");
        distritos.add("SAN MARTIN DE PORRES");
        distritos.add("SAN MIGUEL");
        distritos.add("SANTA ANITA");
        distritos.add("SANTA MARIA DEL MAR");
        distritos.add("SANTA ROSA");
        distritos.add("SANTIAGO DE SURCO");
        distritos.add("SURQUILLO");
        distritos.add("VILLA EL SALVADOR");
        distritos.add("VILLA MARIA DEL TRIUNFO");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, distritos);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       /* if(Servicio != null && Servicio.getNombreServicio() != null){
            int spinnerPosition = adapter.getPosition(Servicio.getNombreServicio());
            spinner.setSelection(spinnerPosition);
        }*/
        Button btnRegistrar = (Button) view.findViewById(R.id.btnRegistrarDir);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registraDomicilio();
            }
        });
        return view;
    }

    private void registraDomicilio(){
        LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
        String distrito = spinner.getSelectedItem().toString();
        EDireccion eDireccion = new EDireccion();
        eDireccion.setUsuarioID(Constants.usuario.getUsuarioID());
        eDireccion.setDomicilio(txtDomicilio.getText().toString());
        eDireccion.setDistrito(distrito);
        lavaAutoDAO.registrarDireccion(eDireccion);
        irAReserva();
    }

    private void irAReserva(){
        ReservaFragmento fgReserva = new ReservaFragmento();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva);
        ft.addToBackStack(null);
        ft.commit();
    }

}
