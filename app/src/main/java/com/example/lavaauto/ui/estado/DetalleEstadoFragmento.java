package com.example.lavaauto.ui.estado;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lavaauto.R;

public class DetalleEstadoFragmento extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        return inflater.inflate(R.layout.fragment_detalle_estado, container, false);
    }
}
