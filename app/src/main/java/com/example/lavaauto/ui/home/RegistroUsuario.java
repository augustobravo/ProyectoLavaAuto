package com.example.lavaauto.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lavaauto.MainActivity;
import com.example.lavaauto.R;

import java.util.HashMap;
import java.util.Map;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }

    public void registrar(View v) {
        final EditText txtNombre = findViewById(R.id.txtNombre);
        final EditText txtclave = findViewById(R.id.txtclave);
        String url = "http://lavaauto.azurewebsites.net/LavaAuto.svc/Usuarios";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(Activity3.this, "Se insertó correctamente Usuario", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("nombre", txtNombre.getText().toString());
                params.put("descripcion", txtUbicacion.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void cancelarUsuario (View view) {
        Intent cancelarUsuario = new Intent(this, MainActivity.class);
        startActivity(cancelarUsuario);
    }
}
