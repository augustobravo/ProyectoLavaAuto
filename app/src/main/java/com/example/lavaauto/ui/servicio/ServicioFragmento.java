package com.example.lavaauto.ui.servicio;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EServicio;
import com.example.lavaauto.ui.utilitario.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServicioFragmento extends Fragment {
    private ArrayList<EServicio> listarServicios;
    private ListView lv1;
    private Gson gson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servicio, container, false);
        lv1 = (ListView) view.findViewById(R.id.idLvServicios);
        cargarLista(view);
        return view;
    }

    public void cargarLista(View view){

        String url = "http://lavaauto.azurewebsites.net/LavaAuto.svc/Servicios";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.i("======>", jsonArray.toString());

                    listarServicios =new ArrayList<EServicio>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.i("======>", object.toString());
                        EServicio eServicio = new EServicio();
                        eServicio.setServicioID(object.getInt("ServicioID"));
                        eServicio.setNombreServicio(object.getString("Descripcion"));
                        eServicio.setPrecio(object.getDouble("Precio"));
                        eServicio.setImagenID(object.getInt("ImagenID"));

                        JSONArray jsonArrayDetalle = object.getJSONArray("Detalles");
                        String detalle = "";
                        for (int j = 0; j <jsonArrayDetalle.length() ; j++) {
                            JSONObject objectDetalle = jsonArrayDetalle.getJSONObject(j);
                            Log.i("======>", objectDetalle.toString());
                            detalle = detalle + "• "+ objectDetalle.getString("Detalle") +"\n";
                        }
                        eServicio.setDetalle(detalle);
                       listarServicios.add(eServicio);
                    }

                    AdaptadorServicios adaptador = new AdaptadorServicios(getActivity());
                    lv1.setAdapter(adaptador);

                } catch (JSONException e) {
                    Log.i("======>", e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                    }
                }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

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

            textView1.setText(listarServicios.get(position).getNombreServicio());
            imageView1.setImageResource(listarServicios.get(position).getImagenID());
            Constants.servicio = listarServicios.get(position);
            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cambiarFragmentoDetalleServicio();
                }
            });
            return(item);
        }

        private void cambiarFragmentoDetalleServicio(){
            DetalleServicioFragment fgDetalleServicio = new DetalleServicioFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment, fgDetalleServicio);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
