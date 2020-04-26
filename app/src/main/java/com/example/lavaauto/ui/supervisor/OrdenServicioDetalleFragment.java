package com.example.lavaauto.ui.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EReserva;
import com.example.lavaauto.ui.utilitario.Constants;

public class OrdenServicioDetalleFragment extends Fragment {

    private LavaAutoDAO lavaAutoDAO;
    private EReserva eReserva;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orden_servicio_detalle, container, false);

        lavaAutoDAO = new LavaAutoDAO();

        TextView lblDocumento = (TextView) view.findViewById(R.id.textView42);
        TextView lblNombres = (TextView) view.findViewById(R.id.textView44);
        TextView lblTipoServicio = (TextView) view.findViewById(R.id.textView49);
        TextView lblDireccion = (TextView) view.findViewById(R.id.textView51);
        TextView lblAutomovil = (TextView) view.findViewById(R.id.textView53);
        TextView lblFecha = (TextView) view.findViewById(R.id.textView55);
        TextView lblHora = (TextView) view.findViewById(R.id.textView57);
        TextView lblFormaPago = (TextView) view.findViewById(R.id.textView60);
        eReserva = Constants.reserva;

        lblDocumento.setText(eReserva.getUsuario().getDocume());
        lblNombres.setText(eReserva.getUsuario().getNombre());
        lblTipoServicio.setText(eReserva.getServicio().getNombreServicio());
        lblDireccion.setText(eReserva.getUsuarioDir().getDomicilio().trim() +" - " + eReserva.getUsuarioDir().getDistrito());
        lblAutomovil.setText(eReserva.getUsuarioAuto().getPlaca().trim() +" - " + eReserva.getUsuarioAuto().getModelo().trim() +" - " + eReserva.getUsuarioAuto().getMarca().trim());
        lblFecha.setText(eReserva.getFecReserva());
        lblHora.setText(eReserva.getHorReserva());
        if(eReserva.getFormaPagoID() == 1){
            lblFormaPago.setText("Efectivo");
        }else if(eReserva.getFormaPagoID() == 2){
            lblFormaPago.setText("Tarjeta de crédito");
        }else{
            lblFormaPago.setText("Transferencia electrónica");
        }

        ImageButton btnSalir = (ImageButton) view.findViewById(R.id.idBtnCancelar);
        ImageButton btnRechazar = (ImageButton) view.findViewById(R.id.idBtnRechazar);
        ImageButton btnEnviarOrden = (ImageButton) view.findViewById(R.id.idBtnEnviarOrden);
        ImageButton btnReprogramar = (ImageButton) view.findViewById(R.id.idBtnReprogramar);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irASupervisor();
            }
        });

        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lavaAutoDAO.actualizarEstadoReserva(eReserva.getReservaID(), 3);
                Toast.makeText(getActivity(),"Reserva Rechazada", Toast.LENGTH_LONG).show();
                irASupervisor();
            }
        });

       btnEnviarOrden.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               lavaAutoDAO.actualizarEstadoReserva(eReserva.getReservaID(), 2);
               Toast.makeText(getActivity(),"Orden de Servicio Enviada", Toast.LENGTH_LONG).show();
               irASupervisor();
           }
       });

       btnReprogramar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Toast.makeText(getActivity(),"Reprogramación Relizada", Toast.LENGTH_LONG).show();
               irASupervisor();
           }
       });
        return view;
    }

    private void irASupervisor()
    {
        OrdenServicioFragment fgSupervisor = new OrdenServicioFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgSupervisor);
        ft.commit();
    }
}
