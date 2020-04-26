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
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;


public class OrdenServicioFragment extends Fragment {

    private ArrayList<EOrdenServicio> listarOrdenes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orden_servicio, container, false);

        ImageButton btnMenuPrincipal =(ImageButton)view.findViewById(R.id.idBtnMenuPrincipal1);
        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuSupervisorFragment fgMenu = new MenuSupervisorFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgMenu);
                ft.commit();
            }
        });

        listarOrdenes = new LavaAutoDAO().listarOrdenServicios();

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lvOrdenes = (ListView) view.findViewById(R.id.idLvOrdenes);
        lvOrdenes.setAdapter(adaptador);
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EOrdenServicio> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.ordenes_registrados, listarOrdenes);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.ordenes_registrados, null);

            TextView txtReservaID = (TextView)item.findViewById(R.id.idTxtReservaID1);
            TextView txtClienteServicio = (TextView)item.findViewById(R.id.idTxtClienteServicio1);
            TextView txtOrdenServicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio1);
            TextView txtFechaReservaServicio = (TextView)item.findViewById(R.id.idTxtFechaReservaServicio1);
            TextView txtHoraReservaServicio = (TextView)item.findViewById(R.id.idTxtHoraReservaServicio1);


            txtReservaID.setText(String.valueOf(listarOrdenes.get(position).getOrdenID()));
            txtClienteServicio.setText(listarOrdenes.get(position).getUsuario().getNombre());
            txtOrdenServicio.setText(listarOrdenes.get(position).getServicio().getNombreServicio());
            txtFechaReservaServicio.setText(listarOrdenes.get(position).getFecReserva());
            txtHoraReservaServicio.setText(listarOrdenes.get(position).getHorReserva());

            ImageButton btnEditar= (ImageButton) item.findViewById(R.id.idBtnEditarDetalleReserva);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.reserva = listarOrdenes.get(position);
                    OrdenServicioDetalleFragment fgOrdenDetalle = new OrdenServicioDetalleFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fgOrdenDetalle);
                    ft.commit();
                }
            });

            return item;
        }
    }
}
