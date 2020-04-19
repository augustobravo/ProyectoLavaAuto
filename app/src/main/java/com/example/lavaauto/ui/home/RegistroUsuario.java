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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lavaauto.MainActivity;
import com.example.lavaauto.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
public class RegistroUsuario extends AppCompatActivity {


    EditText txtnacimiento;
    EditText txtfecharegistro;
    Calendar calendario1 = Calendar.getInstance();
    Calendar calendario2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        txtnacimiento = findViewById(R.id.txtnacimiento);
        txtnacimiento.setOnClickListener(new View.OnClickListener() {
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
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf1;

        sdf1 = new SimpleDateFormat(formatoDeFecha, Locale.US);
        txtnacimiento.setText(sdf1.format(calendario1.getTime()));

    }

    private void actualizarInput1(){
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf2;
        sdf2 = new SimpleDateFormat(formatoDeFecha, Locale.US);
        txtfecharegistro.setText(sdf2.format(calendario2.getTime()));

    }



    public void registrar(View v) {
        final EditText txtNombres = findViewById(R.id.txtnombres);
        final EditText txtpassword = findViewById(R.id.txtpassword);
        final EditText txtNacimiento = findViewById(R.id.txtnacimiento);
        final EditText txtdocumento = findViewById(R.id.txtdocumento);
        final EditText txtfecharegistro = findViewById(R.id.txtfecharegistro);

        String url = "http://lavaauto.azurewebsites.net/LavaAuto.svc/Usuarios";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(RegistroUsuario.this, "Se insertó correctamente Usuario", Toast.LENGTH_LONG);
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void cancelarUsuario (View view) {
        Intent cancelarUsuario = new Intent(this, MainActivity.class);
        startActivity(cancelarUsuario);
    }
}
