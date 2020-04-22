package com.example.lavaauto.ui.servicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lavaauto.MenuNavegable;
import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.reserva.ReservaFragmento;

public class DetalleServicioFragment extends Fragment {

    EServicio eServicio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_servicio, container, false);
                // Inflate the layout for this fragmen
        TextView txtNombreServicioDetalle = (TextView)view.findViewById(R.id.idTxtNombreServicio);
        ImageView ImvServicioDetalle = (ImageView)view.findViewById(R.id.idIvServicioDetalle);
        TextView txtDetalleServicio = (TextView)view.findViewById(R.id.idTxtDetalle);
        TextView txtPrecioServicio = (TextView)view.findViewById(R.id.idTxtPrecio);
        Button btnReserva = (Button) view.findViewById(R.id.idBtnIrReserva);


        txtNombreServicioDetalle.setText(eServicio.getNombreServicio());
        ImvServicioDetalle.setImageResource(eServicio.getImagenID());
        txtDetalleServicio.setText(eServicio.getDetalle());
        txtPrecioServicio.setText("S/ " + eServicio.getPrecio());

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAReserva();
            }
        });
        
        return view;
    }

    public void setDetalleServicio(EServicio eServicio){
        this.eServicio = eServicio;
    }

    private void irAReserva(){
        ReservaFragmento fgReserva = new ReservaFragmento();
        fgReserva.setServicio(eServicio);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva);
        ft.addToBackStack(null);
        ft.commit();
    }
}
