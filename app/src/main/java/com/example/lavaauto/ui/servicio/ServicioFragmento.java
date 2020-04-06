package com.example.lavaauto.ui.servicio;

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
import com.example.lavaauto.ui.entidad.EServicio;

import java.util.ArrayList;

public class ServicioFragmento extends Fragment {
    private ArrayList<EServicio> listarServicios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servicio, container, false);

        listarServicios =new ArrayList<EServicio>();
        listarServicios.add(new EServicio("Lavado de Salón", "Lavadodesalon"));
        listarServicios.add(new EServicio("Lavado Premium", "lavadopremium"));
        listarServicios.add(new EServicio("Renovación / Protección de Pintura", "renovaciondepintura"));

        AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idLvServicios);
        lv1.setAdapter(adaptador);
        return view;
    }

    class AdaptadorServicios extends ArrayAdapter<EServicio> {


        AdaptadorServicios(Activity context) {
            super(context, R.layout.servicio, listarServicios);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(getContext()).inflate(R.layout.servicio, null);
            TextView textView1 = (TextView)item.findViewById(R.id.idtxtNombre);
            Button btnDetalle = (Button) item.findViewById(R.id.idBtnDetalleServicio);
            ImageView imageView1 = (ImageView)item.findViewById(R.id.idIvServicio);

            textView1.setText(listarServicios.get(position).getNombre());

            if (listarServicios.get(position).getImagen() =="Lavadodesalon")
                imageView1.setImageResource(R.mipmap.lavadodesalon);
            else  if (listarServicios.get(position).getImagen() =="lavadopremium")
                imageView1.setImageResource(R.mipmap.lavadopremium);
            else
                imageView1.setImageResource(R.mipmap.renovaciondepintura);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EDetalleServicio eDetalleServicio = null;
                    if (position == 0){
                        int idImagen = R.mipmap.lavadodesalon;
                        String sDetalle = "• LAVADO EXTERIOR DE CARROCERÍA CON SHAMPOO\n" +
                                "• SECADO CON ANTE SONAX\n" +
                                "• LAVADO DE LLANTAS Y GUARDARNOS\n" +
                                "• AIRE COMPRIMIDO PARA EXTERIORES E INTERIORES\n" +
                                "• SILICONA A GUARDAFANGO Y PARTES PLÁSTICAS\n" +
                                "• ASPIRADO INTEGRAL DE SALÓN\n" +
                                "• LAVADO DE ALFOMBRA, MALETERA Y CINTURONES\n" +
                                "• LAVADO DE PUERTAS Y TECHO";
                        eDetalleServicio = new EDetalleServicio(listarServicios.get(position).getNombre(), idImagen, sDetalle);
                    } else if (position == 1){
                        int idImagen = R.mipmap.lavadopremium;
                        String sDetalle = "• LAVADO EXTERIOR DE CARROCERÍA CON SHAMPOO\n" +
                                "• SECADO CON ANTE SONAX\n" +
                                "• LAVADO DE LLANTAS Y GUARDARNOS\n" +
                                "• AIRE COMPRIMIDO PARA EXTERIORES E INTERIORES\n" +
                                "• SILICONA A GUARDAFANGO Y PARTES PLÁSTICAS\n" +
                                "• ASPIRADO INTEGRAL DE SALÓN\n" +
                                "• LAVADO DE ALFOMBRA, MALETERA Y CINTURONES\n" +
                                "• LAVADO DE PUERTAS Y TECHO\n" +
                                "• LAVADO DE ASIENTOS CON DESMONTAJE\n" +
                                "• LIMPIEZA INTENSIVA DE TODAS LOS PLÁSTICOS\n" +
                                "  (CONSOLA, PANELES DE PUERTAS, TIMÓN, ETC.)\n" +
                                "• LIMPIEZA DE PLÁSTICOS INTERIORES Y LUNAS\n" +
                                "• LIMPIEZA Y SILICONEADO DE TABLERO\n" +
                                "• SILICONEDO DE PLÁSTICOS Y VINÍLICOS";
                        eDetalleServicio = new EDetalleServicio(listarServicios.get(position).getNombre(), idImagen,sDetalle);
                    }else {
                        int idImagen = R.mipmap.renovaciondepintura;
                        String sDetalle = "• LAVADO EXTERIOR DE CARROCERÍA CON SHAMPOO\n" +
                                "• SECADO CON ANTE SONAX\n" +
                                "• LAVADO DE LLANTAS Y GUARDARNOS\n" +
                                "• AIRE COMPRIMIDO PARA EXTERIORES E INTERIORES\n" +
                                "• SILICONA A GUARDAFANGO Y PARTES PLÁSTICAS\n" +
                                "• ASPIRADO INTEGRAL DE SALÓN\n" +
                                "• LAVADO DE ALFOMBRA, MALETERA Y CINTURONES\n" +
                                "• LAVADO DE PUERTAS Y TECHO";
                        eDetalleServicio = new EDetalleServicio(listarServicios.get(position).getNombre(), idImagen,sDetalle);

                    }
                    cambiarFragmentoDetalleServicio(eDetalleServicio);

                }
            });
            return(item);
        }

        private void cambiarFragmentoDetalleServicio(EDetalleServicio eDetalleServicio){
            DetalleServicioFragment fgDetalleServicio = new DetalleServicioFragment();
            fgDetalleServicio.setDetalleServicio(eDetalleServicio);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment, fgDetalleServicio);
            ft.addToBackStack(null);
            ft.commit();



        }
    }
}
