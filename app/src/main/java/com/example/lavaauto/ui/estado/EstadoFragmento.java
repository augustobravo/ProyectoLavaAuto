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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EDetalleServicio;

import java.util.ArrayList;

public class EstadoFragmento extends Fragment {
    private ArrayList<EDetalleServicio> listarOrdenServicios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servicio, container, false);

        listarOrdenServicios =new ArrayList<EDetalleServicio>();
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20102", "estado_pendiente"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20105", "estado_progreso"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20110", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20111", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20112", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20113", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleServicio("Orden Servicio 20114", "Estado_completada"));

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idLvServicios);
        lv1.setAdapter(adaptador);
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EDetalleServicio> {


        AdaptadorServicios(Activity context) {
            super(context, R.layout.servicio, listarOrdenServicios);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.servicio, null);
            TextView textView1 = (TextView)item.findViewById(R.id.idtxtNombre);
            Button btnDetalle = (Button) item.findViewById(R.id.idBtnDetalleServicio);
            ImageView imageView1 = (ImageView)item.findViewById(R.id.idIvServicio);

            textView1.setText(listarOrdenServicios.get(position).getNombre());

            if (listarOrdenServicios.get(position).getImagen() =="estado_pendiente")
                imageView1.setImageResource(R.mipmap.estado_pendiente);
            else  if (listarOrdenServicios.get(position).getImagen() =="estado_progreso")
                imageView1.setImageResource(R.mipmap.estado_progreso);
            else {
                imageView1.setImageResource(R.mipmap.estado_completada);
            }

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0){
                        cambiarFragmentoDetalleServicio();
                        DetalleEstadoFragmento fgDetalleFragmento = new DetalleEstadoFragmento();
                        fgDetalleFragmento.setDetalleFragmento(eDetalleEstado);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment,fgDetalleFragmento);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
            });
            return(item);
        }

        private void cambiarFragmentoDetalleServicio(){
            Fragment detalleEstadoFragmento = new DetalleEstadoFragmento();
            if (detalleEstadoFragmento != null) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.idfrmServicio, detalleEstadoFragmento).commit();

            }
        }
    }
}
