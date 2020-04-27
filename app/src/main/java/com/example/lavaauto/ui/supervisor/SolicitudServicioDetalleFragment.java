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
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.utilitario.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SolicitudServicioDetalleFragment extends Fragment {

    private LavaAutoDAO lavaAutoDAO;
    private EOrdenServicio eOrdenServicio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solicitud_servicio_detalle, container, false);

        lavaAutoDAO = new LavaAutoDAO();

        TextView lblDocumento = (TextView) view.findViewById(R.id.textView42);
        TextView lblNombres = (TextView) view.findViewById(R.id.textView44);
        TextView lblTipoServicio = (TextView) view.findViewById(R.id.textView49);
        TextView lblDireccion = (TextView) view.findViewById(R.id.textView51);
        TextView lblAutomovil = (TextView) view.findViewById(R.id.textView53);
        TextView lblFecha = (TextView) view.findViewById(R.id.textView55);
        TextView lblHora = (TextView) view.findViewById(R.id.textView57);
        TextView lblFormaPago = (TextView) view.findViewById(R.id.textView60);
        eOrdenServicio = Constants.ordenServicio;

        lblDocumento.setText(eOrdenServicio.getUsuario().getDocume());
        lblNombres.setText(eOrdenServicio.getUsuario().getNombre());
        lblTipoServicio.setText(eOrdenServicio.getServicio().getNombreServicio());
        lblDireccion.setText(eOrdenServicio.getUsuarioDir().getDomicilio().trim() +" - " + eOrdenServicio.getUsuarioDir().getDistrito());
        lblAutomovil.setText(eOrdenServicio.getUsuarioAuto().getPlaca().trim() +" - " + eOrdenServicio.getUsuarioAuto().getModelo().trim() +" - " + eOrdenServicio.getUsuarioAuto().getMarca().trim());
        lblFecha.setText(eOrdenServicio.getFecReserva());
        lblHora.setText(eOrdenServicio.getHorReserva());
        if(eOrdenServicio.getFormaPagoID() == 1){
            lblFormaPago.setText("Efectivo");
        }else if(eOrdenServicio.getFormaPagoID() == 2){
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
                lavaAutoDAO.actualizarEstadoOrdenServicio(eOrdenServicio.getOrdenID(), 0);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String sFechaActual = df.format(calendar.getTime());

                df = new SimpleDateFormat("HH:mm:ss");
                String sHoraActual = df.format(calendar.getTime());

                lavaAutoDAO.insertarHistorialEstadoOrdenServicio(eOrdenServicio.getOrdenID(), sFechaActual,sHoraActual ,0);

                Toast.makeText(getActivity(),"Reserva Rechazada", Toast.LENGTH_LONG).show();
                irASupervisor();
            }
        });

       btnEnviarOrden.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               lavaAutoDAO.actualizarEstadoOrdenServicio(eOrdenServicio.getOrdenID(), 2);
               Calendar calendar = Calendar.getInstance();
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               String sFechaActual = df.format(calendar.getTime());

               df = new SimpleDateFormat("HH:mm:ss");
               String sHoraActual = df.format(calendar.getTime());

               lavaAutoDAO.insertarHistorialEstadoOrdenServicio(eOrdenServicio.getOrdenID(), sFechaActual,sHoraActual ,2);

               Toast.makeText(getActivity(),"Orden de Servicio Enviada", Toast.LENGTH_LONG).show();
               irASupervisor();
           }
       });

       btnReprogramar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              ReprogramarFragment fgReprogramar = new ReprogramarFragment();
               FragmentManager fragmentManager = getFragmentManager();
               FragmentTransaction ft = fragmentManager.beginTransaction();
               ft.replace(R.id.nav_host_fragment, fgReprogramar);
               ft.commit();
           }
       });
        return view;
    }

    private void irASupervisor()
    {
        SolicitudServicioFragment fgSupervisor = new SolicitudServicioFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgSupervisor);
        ft.commit();
    }
}
