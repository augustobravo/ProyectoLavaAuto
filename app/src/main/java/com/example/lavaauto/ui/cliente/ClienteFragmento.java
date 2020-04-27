package com.example.lavaauto.ui.cliente;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    public ClienteFragmento() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        listarOrdenServicios = new LavaAutoDAO().obtenerOrdenesServicioCliente(Constants.usuario.getUsuarioID());
        ClienteFragmento.AdaptadorServicios adaptador = new ClienteFragmento.AdaptadorServicios(getActivity());
        ListView lv1 = (ListView) view.findViewById(R.id.idlistordenusuario);
        lv1.setAdapter(adaptador);
        return view;
    }
    class AdaptadorServicios extends ArrayAdapter<EOrdenServicio> {
        AdaptadorServicios(Activity context) {
            super(context, R.layout.perfil_orden, listarOrdenServicios);
        }
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.perfil_orden, null);
        TextView txtordenservicio = (TextView)item.findViewById(R.id.idTxtOrdenServicio);
        TextView txtNombreCliente = (TextView)item.findViewById(R.id.idTxtNombreCliente);
        TextView txtfecha = (TextView)item.findViewById(R.id.idTxtfecha);
        TextView txtestado = (TextView)item.findViewById(R.id.idTxtestado);

      //  TextView name= txtNombreCliente;

        txtordenservicio.setText(String.valueOf(listarOrdenServicios.get(position).getOrdenID()));
        txtNombreCliente.setText(listarOrdenServicios.get(position).getUsuario().getNombre());
        txtfecha.setText(listarOrdenServicios.get(position).getFecReserva());
        txtestado.setText(listarOrdenServicios.get(position).getDesEstado());
        return(item);

    }
  //   public void regresar (View view){
    //Intent retorno = new Intent(this, MenuNavegable.class);
    //startActivity(retorno);
    // }

}
