package com.example.lavaauto.ui.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.utilitario.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class OrdenServicioDetalleFragment extends Fragment {

    private LavaAutoDAO lavaAutoDAO;
    private EOrdenServicio eOrdenServicio;
    private Spinner spEstado;

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

        TextView lblDocumento = (TextView) view.findViewById(R.id.textView33);
        TextView lblNombres = (TextView) view.findViewById(R.id.textView34);
        TextView lblTipoServicio = (TextView) view.findViewById(R.id.textView35);
        TextView lblDireccion = (TextView) view.findViewById(R.id.textView36);
        TextView lblAutomovil = (TextView) view.findViewById(R.id.textView37);
        TextView lblFecha = (TextView) view.findViewById(R.id.textView38);
        TextView lblHora = (TextView) view.findViewById(R.id.textView39);
        spEstado = (Spinner) view.findViewById(R.id.idSpEstado);
        ArrayList<String> estados = new ArrayList<>();
        estados.add("Servicio Iniciado");
        estados.add("Lavado y Aspirado");
        estados.add("Encerado");
        estados.add("Servicio Concluido");
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, estados);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spEstado.setAdapter(adapter);

        eOrdenServicio = Constants.reserva;

        lblDocumento.setText(eOrdenServicio.getUsuario().getDocume());
        lblNombres.setText(eOrdenServicio.getUsuario().getNombre());
        lblTipoServicio.setText(eOrdenServicio.getServicio().getNombreServicio());
        lblDireccion.setText(eOrdenServicio.getUsuarioDir().getDomicilio().trim() +" - " + eOrdenServicio.getUsuarioDir().getDistrito());
        lblAutomovil.setText(eOrdenServicio.getUsuarioAuto().getPlaca().trim() +" - " + eOrdenServicio.getUsuarioAuto().getModelo().trim() +" - " + eOrdenServicio.getUsuarioAuto().getMarca().trim());
        lblFecha.setText(eOrdenServicio.getFecReserva());
        lblHora.setText(eOrdenServicio.getHorReserva());


        ImageButton btnSalir = (ImageButton) view.findViewById(R.id.idBtnCancelar1);
        ImageButton btnAceptar = (ImageButton) view.findViewById(R.id.idBtnAceptar);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAOrdenServicio();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEstado = spEstado.getSelectedItem().toString();
                int iEstado = 0;

                if(sEstado.equals("Servicio Iniciado")){
                    iEstado = 3;
                }else if(sEstado.equals("Lavado y aspirado")){
                    iEstado = 4;
                }else if(sEstado.equals("Encerado")){
                    iEstado = 5;
                }else{
                    iEstado = 6;
                }
                lavaAutoDAO.actualizarEstadoOrdenServicio(eOrdenServicio.getOrdenID(), iEstado);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String sFechaActual = df.format(calendar.getTime());

                df = new SimpleDateFormat("HH:mm:ss");
                String sHoraActual = df.format(calendar.getTime());

                lavaAutoDAO.insertarHistorialEstadoOrdenServicio(eOrdenServicio.getOrdenID(), sFechaActual,sHoraActual ,iEstado);
                Toast.makeText(getActivity(),"Cambio de Estado Existoso", Toast.LENGTH_LONG).show();
                irAOrdenServicio();
            }
        });

        return  view;
    }

    private void irAOrdenServicio()
    {
        OrdenServicioFragment fgOrden = new OrdenServicioFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgOrden);
        ft.commit();
    }
}
