package com.example.lavaauto.ui.cliente;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.utilitario.Constants;

import java.util.ArrayList;

public class ClienteFragmento extends Fragment {

    private ArrayList<EOrdenServicio> listarOrdenServicios;
    private int selectedPosition = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClienteFragmento() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ClienteFragmento newInstance(String param1, String param2) {
        ClienteFragmento fragment = new ClienteFragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        listarOrdenServicios = new LavaAutoDAO().UsuarioxOrden(Constants.usuario.getUsuarioID());
        ClienteFragmento.AdaptadorServicios adaptador = new ClienteFragmento.AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idlistordenusuario);
        lv1.setAdapter(adaptador);
        return view;
    }
    class AdaptadorServicios extends ArrayAdapter<EOrdenServicio> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.fragment_cliente, listarOrdenServicios);
        }
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.fragment_cliente, null);
        TextView txtordenservicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio);
        TextView txtfecha = (TextView)item.findViewById(R.id.idTxtfecha);
        TextView txtestado = (TextView)item.findViewById(R.id.idTxtestado);

        txtordenservicio.setText(String.valueOf(listarOrdenServicios.get(position).getOrdenID()));
        txtfecha.setText(listarOrdenServicios.get(position).getFecReserva());
        txtestado.setText(Integer.toString(listarOrdenServicios.get(position).getEstado()));

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
        return(item);

    }
  //   public void regresar (View view){
    //Intent retorno = new Intent(this, MenuNavegable.class);
    //startActivity(retorno);
    // }

}
