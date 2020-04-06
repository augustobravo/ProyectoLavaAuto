package com.example.lavaauto.ui.servicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EDetalleServicio;

public class DetalleServicioFragment extends Fragment {

    EDetalleServicio eDetalleSericio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_servicio, container, false);
                // Inflate the layout for this fragmen
        TextView txtNombreServicioDetalle = (TextView)view.findViewById(R.id.idTxtNombreServicio);
        ImageView ImvServicioDetalle = (ImageView)view.findViewById(R.id.idIvServicioDetalle);
        TextView txtDetalleServicio = (TextView)view.findViewById(R.id.idTxtDetalle);

        txtNombreServicioDetalle.setText(eDetalleSericio.getNombre());
        ImvServicioDetalle.setImageResource(eDetalleSericio.getIdImagen());
        txtDetalleServicio.setText(eDetalleSericio.getDetalle());
        return view;
    }

    public void setDetalleServicio(EDetalleServicio eDetalleServicio){
        this.eDetalleSericio = eDetalleServicio;
    }
}
