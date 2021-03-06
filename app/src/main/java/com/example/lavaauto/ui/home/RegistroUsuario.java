package com.example.lavaauto.ui.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lavaauto.MainActivity;
import com.example.lavaauto.MenuNavegable;
import com.example.lavaauto.R;
import com.example.lavaauto.ui.entidad.EUsuario;
import com.example.lavaauto.dao.LavaAutoDAO;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
public class RegistroUsuario extends AppCompatActivity {

    private EditText txtNombres;
    private EditText txtdocumento;
    private EditText txtpassword;
    private EditText txtNacimiento;
    private EditText txtfecharegistro;
    DatePickerDialog datePickerNacimiento;
    DatePickerDialog datePickerRegistro;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        txtNombres = (EditText)findViewById(R.id.txtnombres);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        txtNacimiento = (EditText)findViewById(R.id.txtnacimiento);
        txtdocumento = (EditText)findViewById(R.id.txtdocumento);
        txtfecharegistro = (EditText)findViewById(R.id.txtfecharegistro);


        /*txtNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistroUsuario.this, date1, calendario1
                        .get(Calendar.YEAR), calendario1.get(Calendar.MONTH),
                        calendario1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        txtNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerNacimiento = new DatePickerDialog(RegistroUsuario.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Formatter formatter = new Formatter();
                                String sDia = String.valueOf(formatter.format("%02d", dayOfMonth));
                                formatter = new Formatter();
                                String sMes = String.valueOf(formatter.format("%02d", (monthOfYear + 1)));

                                txtNacimiento.setText(sDia + "/" + sMes + "/" + year);
                            }
                        }, year, month, day);
                datePickerNacimiento.show();
            }
        });

        /*txtfecharegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistroUsuario.this, date2, calendario2
                        .get(Calendar.YEAR), calendario2.get(Calendar.MONTH),
                        calendario2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

          txtfecharegistro.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final Calendar cldr = Calendar.getInstance();
                  int day = cldr.get(Calendar.DAY_OF_MONTH);
                  int month = cldr.get(Calendar.MONTH);
                  int year = cldr.get(Calendar.YEAR);
                  // date picker dialog
                  datePickerRegistro = new DatePickerDialog(RegistroUsuario.this,
                          new DatePickerDialog.OnDateSetListener() {
                              @Override
                              public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                  Formatter formatter = new Formatter();
                                  String sDia = String.valueOf(formatter.format("%02d", dayOfMonth));
                                  formatter = new Formatter();
                                  String sMes = String.valueOf(formatter.format("%02d", (monthOfYear + 1)));

                                  txtfecharegistro.setText(sDia + "/" + sMes + "/" + year);
                              }
                          }, year, month, day);
                  datePickerRegistro.show();
              }
          });

    }

    /*DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario1.set(Calendar.YEAR, year);
            calendario1.set(Calendar.MONTH, monthOfYear);
            calendario1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };
    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
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
*/


    public void registrar(View v) {
        Log.i("RegistroUsuario", "registrar()");
        //String url = "http://lavaauto.azurewebsites.net/LavaAuto.svc/Usuarios";
        //JSONObject jsonObject = new JSONObject();

        try {
            String resultado = "";
            if (txtNombres.length() == 0){
                resultado = "Falta Nombre ";
            }
            if (txtpassword.length() == 0){
                resultado = resultado + "Falta Clave ";
            }
            if (txtNacimiento.length() == 0){
                resultado = resultado + "Falta fecha Nac ";
            }
            if (txtdocumento.length() == 0){
                resultado = resultado + "Falta Documento ";
            }
            if (txtfecharegistro.length() == 0){
                resultado = resultado + "Falta fecha Reg ";
            }
            /*Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
            jsonObject.put("Nombres", txtNombres.getText().toString());
            jsonObject.put("Password", txtpassword.getText().toString());
            jsonObject.put("FechaNacimiento", txtNacimiento.getText().toString());
            jsonObject.put("Documento", txtdocumento.getText().toString());
            jsonObject.put("FechaRegistro", txtfecharegistro.getText().toString());
            jsonObject.put("UsuariosID", 0);*/
            //jsonObject.put("autos", new String[]{});
            //jsonObject.put("direcciones", new String[]{});

            EUsuario eUsuario = new EUsuario();
            eUsuario.setNombre(txtNombres.getText().toString());
            eUsuario.setDocume(txtdocumento.getText().toString());
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            try {
                Date fecha = sdf.parse(txtNacimiento.getText().toString());
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                eUsuario.setFecNac(sdf.format(fecha));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            eUsuario.setPassword(txtpassword.getText().toString());

            try {
                Date fecha = sdf.parse(txtfecharegistro.getText().toString());
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                eUsuario.setFecReg(sdf.format(fecha));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            int filas = new LavaAutoDAO().registrarUsuario(eUsuario);
            Toast toast = Toast.makeText(RegistroUsuario.this , "Usuario Registrado ", Toast.LENGTH_LONG);
            Intent cancelarUsuario = new Intent(this, MainActivity.class);
            startActivity(cancelarUsuario);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String codigo = response.optString("Codigo");
                String descripcion = response.optString("Descripcion");
                Log.i("codigo respuesta====>", codigo.toString());
                Log.i("respuesta====>", descripcion.toString());


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
        RequestQueue requestQueue = Volley.newRequestQueue(this);

           byte[] bytes = jsonObjectRequest.getBody();
            if(bytes != null){
                try {
                    String str = new String(bytes, "UTF-8");
                    Log.i("Body ======>", str);
                    Toast toast = Toast.makeText(RegistroUsuario.this , "Se insertó Usuario ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent ingresarmenu = new Intent(this, MainActivity.class);
                    startActivity(ingresarmenu);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        requestQueue.add(jsonObjectRequest);*/
    }


    public void cancelarUsuario (View view) {
        Intent cancelarUsuario = new Intent(this, MainActivity.class);
        startActivity(cancelarUsuario);
    }
}
