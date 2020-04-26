package com.example.lavaauto.ui.supervisor;

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
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EReserva;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;

public class OrdenServicioFragment extends Fragment {

    private ArrayList<EReserva> listarReserva;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orden_servicio, container, false);

        listarReserva = new LavaAutoDAO().listarReservaRegistradas();

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lvReserva = (ListView) view.findViewById(R.id.idLvReservas);
        lvReserva.setAdapter(adaptador);

        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EReserva> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.servicios_registrados, listarReserva);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.servicios_registrados, null);

            TextView txtReservaID = (TextView)item.findViewById(R.id.idTxtReservaID);
            TextView txtClienteServicio = (TextView)item.findViewById(R.id.idTxtClienteServicio);
            TextView txtOrdenServicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio);
            TextView txtFechaReservaServicio = (TextView)item.findViewById(R.id.idTxtFechaReservaServicio);
            TextView txtHoraReservaServicio = (TextView)item.findViewById(R.id.idTxtHoraReservaServicio);


            txtReservaID.setText(String.valueOf(listarReserva.get(position).getReservaID()));
            txtClienteServicio.setText(listarReserva.get(position).getUsuario().getNombre());
            txtOrdenServicio.setText(listarReserva.get(position).getServicio().getNombreServicio());
            txtFechaReservaServicio.setText(listarReserva.get(position).getFecReserva());
            txtHoraReservaServicio.setText(listarReserva.get(position).getHorReserva());

            ImageButton btnDetale = (ImageButton) item.findViewById(R.id.idBtnVerDetalleReserva);
            btnDetale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.reserva = listarReserva.get(position);
                    OrdenServicioDetalleFragment fgSupervisorDetalle = new OrdenServicioDetalleFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fgSupervisorDetalle);
                    ft.commit();
                }
            });

            return item;
        }
    }
}
