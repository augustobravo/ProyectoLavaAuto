package com.example.lavaauto.ui.reserva;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EOrdenServicio;
import com.example.lavaauto.ui.servicio.ServicioFragmento;
import com.example.lavaauto.ui.utilitario.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;


public class Reserva3Fragment extends Fragment {

    private EditText txtFechaServicio;
    private EditText txtHoraServicio;
    DatePickerDialog datePickerServicio;
    TimePickerDialog timePickerServicio;
    Spinner spFormaPago;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserva3, container, false);
        txtFechaServicio = (EditText) view.findViewById(R.id.txtFechaServicio);
        txtFechaServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerServicio = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Formatter formatter = new Formatter();
                                String sDia = String.valueOf(formatter.format("%02d", dayOfMonth));
                                formatter = new Formatter();
                                String sMes = String.valueOf(formatter.format("%02d", (monthOfYear + 1)));

                                txtFechaServicio.setText(sDia + "/" + sMes + "/" + year);
                            }
                        }, year, month, day);
                datePickerServicio.show();
            }
        });

        txtHoraServicio = (EditText) view.findViewById(R.id.txtHoraServicio);
        txtHoraServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hora = cldr.get(Calendar.HOUR);
                int minuto = cldr.get(Calendar.MINUTE);

                timePickerServicio = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Formatter formatter = new Formatter();
                        String sHora = String.valueOf(formatter.format("%02d", hourOfDay));
                        formatter = new Formatter();
                        String sMinuto = String.valueOf(formatter.format("%02d", minute));

                        txtHoraServicio.setText(sHora + ":" + sMinuto +":00" );
                    }
                }, hora, minuto, true);

                timePickerServicio.show();
            }
        });

        spFormaPago = (Spinner) view.findViewById(R.id.idSpFormaPago);
        ArrayList<String> servicios = new ArrayList<>();
        servicios.add("Efectivo");
        servicios.add("Tarjeta de crédito");
        servicios.add("Transferencia electrónica");

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, servicios);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spFormaPago.setAdapter(adapter);

        Button btnAnterior = (Button) view.findViewById(R.id.idBtnAtras1);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoBotonAtras();
            }
        });

        Button btnReservar = (Button) view.findViewById(R.id.idBtnReserva);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoReservar();
            }
        });

        return view;
    }

    private void eventoBotonAtras(){
        Reserva2Fragment fgReserva2 = new Reserva2Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgReserva2);
        ft.commit();
    }

    private void eventoReservar(){
        LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
        EOrdenServicio eOrdenServicio =new EOrdenServicio();
        eOrdenServicio.setServicio(Constants.servicio);
        eOrdenServicio.setUsuario(Constants.usuario);
        eOrdenServicio.setUsuarioDir(Constants.direccion);
        eOrdenServicio.setUsuarioAuto(Constants.auto);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fecha = sdf.parse(txtFechaServicio.getText().toString());
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            eOrdenServicio.setFecReserva(sdf.format(fecha));
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        eOrdenServicio.setHorReserva(txtHoraServicio.getText().toString());
        eOrdenServicio.setEstado(1);
        String formaPago = spFormaPago.getSelectedItem().toString();
        int TipoPago = 0;
        if(formaPago.equals("Efectivo")){
            TipoPago = 1;
        }else if(formaPago.equals("Tarjeta de crédito")){
            TipoPago = 2;
        }else{
            TipoPago = 3;
        }
        eOrdenServicio.setFormaPagoID(TipoPago);
        int respuesta = lavaAutoDAO.registraOrdenSericio(eOrdenServicio);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sFechaActual = df.format(calendar.getTime());

        df = new SimpleDateFormat("HH:mm:ss");
        String sHoraActual = df.format(calendar.getTime());

        int maximaOrdenID = lavaAutoDAO.obtenerMaximaOrdenID();
        lavaAutoDAO.insertarHistorialEstadoOrdenServicio(maximaOrdenID, sFechaActual,sHoraActual ,1);

        if(respuesta >0){
            Toast.makeText(getActivity(),"Reserva Realizada con Exito", Toast.LENGTH_LONG).show();
            irAServicio();
        }else{
            Toast.makeText(getActivity(),"Error al Realizar la Reserva", Toast.LENGTH_LONG).show();
        }
    }

    private void irAServicio(){
        ServicioFragmento fgServicio = new ServicioFragmento();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fgServicio);
        ft.commit();
    }
}
