package com.example.lavaauto.ui.estado;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
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

    /*  listarOrdenServicios =new ArrayList<EDetalleEstado>();
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20102", "estado_pendiente"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20105", "estado_progreso"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20110", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20111", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20112", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20113", "Estado_completada"));
        listarOrdenServicios.add(new EDetalleEstado("Orden Servicio 20114", "Estado_completada"));*/

      /*  listarOrdenServicios = new LavaAutoDAO().obtenerOrdenesServicio(Constants.usuario.getUsuarioID());
        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idLvOrdenServicio);
        lv1.setAdapter(adaptador);*/
        return view;
    }

    //class AdaptadorServicios extends ArrayAdapter<EOrdenServicio> {
      //  AdaptadorServicios(Activity context) {
           // super(context, R.layout.ordenservicio, listarOrdenServicios);
       // }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.ordenservicio, null);
            TextView txtordenservicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio);
            TextView txtservicio = (TextView) item.findViewById(R.id.idTxtServicio);
            TextView txtfecha = (TextView)item.findViewById(R.id.idTxtfecha);
            TextView txtestado = (TextView)item.findViewById(R.id.idTxtestado);

            txtordenservicio.setText(listarOrdenServicios.get(position).getReservaID());
            txtservicio.setText(listarOrdenServicios.get(position).getServicioID());
            txtfecha.setText((CharSequence) listarOrdenServicios.get(position).getFecReserva());
            txtestado.setText(listarOrdenServicios.get(position).getEstado());

            RadioButton rbSeleccion = (RadioButton)item.findViewById(R.id.idRbSeleccionar);
            rbSeleccion.setChecked(position == selectedPosition);
            rbSeleccion.setTag(position);
            rbSeleccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = (Integer)view.getTag();
                   // notifyDataSetChanged();
                    Constants.ordenServicio = listarOrdenServicios.get(position);
                }
            });

           /* if (listarOrdenServicios.get(position).getImagen() =="estado_pendiente")
                imageView1.setImageResource(R.mipmap.estado_pendiente);
            else  if (listarOrdenServicios.get(position).getImagen() =="estado_progreso")
                imageView1.setImageResource(R.mipmap.estado_progreso);
            else {
                imageView1.setImageResource(R.mipmap.estado_completada);
            }*/

          /*  btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0){
                        cambiarFragmentoDetalleServicio();
                        DetalleEstadoFragmento fgDetalleFragmento = new DetalleEstadoFragmento();
                       // fgDetalleFragmento.setDetalleFragmento(eDetalleEstado);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment,fgDetalleFragmento);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
            });*/
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
//}
