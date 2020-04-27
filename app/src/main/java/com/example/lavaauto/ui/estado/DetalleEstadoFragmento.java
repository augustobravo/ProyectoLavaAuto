package com.example.lavaauto.ui.estado;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lavaauto.MenuNavegable;
import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EDetalleOrdenServicio;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.supervisor.SolicitudServicioFragment;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;

public class DetalleEstadoFragmento extends Fragment{

    private LavaAutoDAO lavaAutoDAO;
    private EOrdenServicio eOrdenServicio;
    private ArrayList<EDetalleOrdenServicio> listarOrdenServicioHistorial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_detalle_estado, container, false);

        lavaAutoDAO = new LavaAutoDAO();

        TextView lblDocumento = (TextView) view.findViewById(R.id.textView62);
        TextView lblNombres = (TextView) view.findViewById(R.id.textView63);
        TextView lblTipoServicio = (TextView) view.findViewById(R.id.textView64);
        TextView lblDireccion = (TextView) view.findViewById(R.id.textView65);
        TextView lblAutomovil = (TextView) view.findViewById(R.id.textView66);
        TextView lblFormaPago = (TextView) view.findViewById(R.id.textView67);
        eOrdenServicio = Constants.ordenServicio;

        lblDocumento.setText(eOrdenServicio.getUsuario().getDocume());
        lblNombres.setText(eOrdenServicio.getUsuario().getNombre());
        lblTipoServicio.setText(eOrdenServicio.getServicio().getNombreServicio());
        lblDireccion.setText(eOrdenServicio.getUsuarioDir().getDomicilio().trim() +" - " + eOrdenServicio.getUsuarioDir().getDistrito());
        lblAutomovil.setText(eOrdenServicio.getUsuarioAuto().getPlaca().trim() +" - " + eOrdenServicio.getUsuarioAuto().getModelo().trim() +" - " + eOrdenServicio.getUsuarioAuto().getMarca().trim());

        if(eOrdenServicio.getFormaPagoID() == 1){
            lblFormaPago.setText("Efectivo");
        }else if(eOrdenServicio.getFormaPagoID() == 2){
            lblFormaPago.setText("Tarjeta de crédito");
        }else{
            lblFormaPago.setText("Transferencia electrónica");
        }

        ImageButton btnSalir = (ImageButton) view.findViewById(R.id.idBtnCancelar2);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAServicioCliente();
            }
        });

        listarOrdenServicioHistorial = new LavaAutoDAO().listarDetalleOrdenServicios(eOrdenServicio.getOrdenID());
        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lstHistorial =(ListView)view.findViewById(R.id.idLvHIstorialServicio);
        lstHistorial.setAdapter(adaptador);
        return  view;
    }

    class AdaptadorServicios extends ArrayAdapter<EDetalleOrdenServicio> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.orden_servicio_historia, listarOrdenServicioHistorial);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.orden_servicio_historia, null);
            TextView txtfecha = (TextView)item.findViewById(R.id.txtFechaServicioH);
            TextView txthora = (TextView)item.findViewById(R.id.txtHoraServicioH);
            TextView txtestado = (TextView)item.findViewById(R.id.txtEstadoH);

            txtfecha.setText(listarOrdenServicioHistorial.get(position).getFechaRegistro());
            txthora.setText(listarOrdenServicioHistorial.get(position).getHoraRegistro());
            txtestado.setText(listarOrdenServicioHistorial.get(position).getDescripcionEstado());

            return(item);
        }
    }


    private void irAServicioCliente()
    {
        EstadoFragmento fgCliente = new EstadoFragmento();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgCliente);
        ft.commit();
    }
}
