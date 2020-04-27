package com.example.lavaauto.ui.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.utilitario.Constants;


public class RegistroServicioFragment extends Fragment {

    private EServicio eServicio;
    private EditText txtPrecio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_servicio, container, false);
        eServicio = Constants.servicio;

        TextView txtServicioID = (TextView)view.findViewById(R.id.txtServicioID3);
        TextView txtServicio = (TextView)view.findViewById(R.id.txtNombreServicio3);
        txtPrecio = (EditText)view.findViewById(R.id.idTxtPrecio1);
        txtServicioID.setText(String.valueOf(eServicio.getServicioID()));
        txtServicio.setText(eServicio.getNombreServicio());
        txtPrecio.setText(String.valueOf(eServicio.getPrecio()));

        ImageButton btnSalir = (ImageButton) view.findViewById(R.id.imageButton6);
        ImageButton btnAceptar = (ImageButton) view.findViewById(R.id.imageButton5);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAListaServicio();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LavaAutoDAO().actualizaPrecioServicio(Constants.servicio.getServicioID(), Double.parseDouble(txtPrecio.getText().toString()) );
                irAListaServicio();
            }
        });

        return  view;
    }

    private void irAListaServicio() {
        ListaServicioFragment fgListaServicio = new ListaServicioFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgListaServicio);
        ft.commit();
    }
}
