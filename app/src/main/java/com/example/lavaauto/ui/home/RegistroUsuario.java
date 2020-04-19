package com.example.lavaauto.ui.home;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.example.lavaauto.MainActivity;
import com.example.lavaauto.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class RegistroUsuario extends AppCompatActivity {

    private EditText txtNombres;
    private EditText txtdocumento;
    private EditText txtpassword;
    private EditText txtNacimiento;
    private EditText txtfecharegistro;
    private Calendar calendario1 = Calendar.getInstance();
    private Calendar calendario2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        txtNombres = (EditText)findViewById(R.id.txtnombres);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        txtNacimiento = (EditText)findViewById(R.id.txtnacimiento);
        txtdocumento = (EditText)findViewById(R.id.txtdocumento);
        txtfecharegistro = (EditText)findViewById(R.id.txtfecharegistro);


        txtNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistroUsuario.this, date, calendario1
                        .get(Calendar.YEAR), calendario1.get(Calendar.MONTH),
                        calendario1.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        txtfecharegistro = findViewById(R.id.txtfecharegistro);
        txtfecharegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistroUsuario.this, date, calendario2
                        .get(Calendar.YEAR), calendario2.get(Calendar.MONTH),
                        calendario2.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario1.set(Calendar.YEAR, year);
            calendario1.set(Calendar.MONTH, monthOfYear);
            calendario1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
            calendario2.set(Calendar.YEAR, year);
            calendario2.set(Calendar.MONTH, monthOfYear);
            calendario2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput1();
        }

    };
    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf1;

        sdf1 = new SimpleDateFormat(formatoDeFecha, Locale.US);
        txtNacimiento.setText(sdf1.format(calendario1.getTime()));

    }

    private void actualizarInput1(){
        String formatoDeFecha = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf2;
        sdf2 = new SimpleDateFormat(formatoDeFecha, Locale.US);
        txtfecharegistro.setText(sdf2.format(calendario2.getTime()));

    }



    public void registrar(View v) {

        String url = "http://lavaauto.azurewebsites.net/LavaAuto.svc/Usuarios";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Nombres", txtNombres.getText().toString());
            jsonObject.put("Password", txtpassword.getText().toString());
            jsonObject.put("FechaNacimiento", txtNacimiento.getText().toString());
            jsonObject.put("Documento", txtdocumento.getText().toString());
            jsonObject.put("FechaRegistro", txtfecharegistro.getText().toString());
            jsonObject.put("UsuariosID", 0);
            //jsonObject.put("autos", new String[]{});
            //jsonObject.put("direcciones", new String[]{});
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String codigo = response.optString("Codigo");
                String descripcion = response.optString("Descripcion");
                Log.i("codigo respuesta====>", codigo.toString());
                Log.i("respuesta====>", descripcion.toString());
                Toast toast = Toast.makeText(RegistroUsuario.this , descripcion, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        Log.i("e1====>", e1.toString());
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        Log.i("e2====>", e2.toString());
                    }
                }
            }
        });

        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(RegistroUsuario.this , "Se insertó correctamente Usuario", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                Log.i("e1====>", e1.toString());
                             } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                Log.i("e2====>", e2.toString());
                            }
                        }

                    }
                }
        ) {
           @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("Nombres", txtNombres.getText().toString());
                params.put("Password", txtpassword.getText().toString());
                params.put("FechaNacimiento", txtNacimiento.getText().toString());
                params.put("Documento", txtdocumento.getText().toString());
                params.put("FechaRegistro", txtfecharegistro.getText().toString());
                params.put("UsuariosID","0");
                params.put("autos","[]");
                params.put("direcciones","[]");
                return params;
            }
        };*/

        RequestQueue requestQueue = Volley.newRequestQueue(this);

           byte[] bytes = jsonObjectRequest.getBody();
            if(bytes != null){
                try {
                    String str = new String(bytes, "UTF-8");
                    Log.i("Body ======>", str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        requestQueue.add(jsonObjectRequest);
    }


    public void cancelarUsuario (View view) {
        Intent cancelarUsuario = new Intent(this, MainActivity.class);
        startActivity(cancelarUsuario);
    }
}
