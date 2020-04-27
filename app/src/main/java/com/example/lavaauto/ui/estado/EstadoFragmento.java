package com.example.lavaauto.ui.estado;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.supervisor.OrdenServicioDetalleFragment;
import com.example.lavaauto.ui.utilitario.Constants;
import java.util.ArrayList;

public class EstadoFragmento extends Fragment {
    private ArrayList<EOrdenServicio> listarOrdenServicios;
    private int selectedPosition = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estado, container, false);

        listarOrdenServicios = new LavaAutoDAO().obtenerOrdenesServicioCliente(Constants.usuario.getUsuarioID());
        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lvServicioCliente = (ListView) view.findViewById(R.id.idLvOrdenServicio);
        lvServicioCliente.setAdapter(adaptador);
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EOrdenServicio> {
      AdaptadorServicios(Activity context) {
            super(context, R.layout.solicitud_servicio, listarOrdenServicios);
       }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.solicitud_servicio, null);
            TextView txtordenservicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio);
            TextView txtNombreCliente = (TextView)item.findViewById(R.id.idTxtNombreCliente);
            TextView txtfecha = (TextView)item.findViewById(R.id.idTxtfecha);
            TextView txtestado = (TextView)item.findViewById(R.id.idTxtestado);
            ImageButton btnVerDetalle = (ImageButton) item.findViewById(R.id.idBtnVerDetalleCliente);

            txtordenservicio.setText(String.valueOf(listarOrdenServicios.get(position).getOrdenID()));
            txtNombreCliente.setText(listarOrdenServicios.get(position).getServicio().getNombreServicio());
            txtfecha.setText(listarOrdenServicios.get(position).getFecReserva());
            txtestado.setText(listarOrdenServicios.get(position).getDesEstado());

            btnVerDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.ordenServicio = listarOrdenServicios.get(position);
                    cambiarFragmentoDetalleServicio();
                }
            });
            return(item);
        }

        private void cambiarFragmentoDetalleServicio(){
            DetalleEstadoFragmento fgDetalleEstado = new DetalleEstadoFragmento();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment,fgDetalleEstado);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
