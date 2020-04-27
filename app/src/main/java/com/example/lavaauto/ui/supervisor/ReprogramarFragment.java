package com.example.lavaauto.ui.supervisor;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lavaauto.R;
import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.utilitario.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;


public class ReprogramarFragment extends Fragment {

    private EditText txtFechaServicio;
    private EditText txtHoraServicio;
    DatePickerDialog datePickerServicio;
    TimePickerDialog timePickerServicio;

    public static ReprogramarFragment newInstance(String param1, String param2) {
        ReprogramarFragment fragment = new ReprogramarFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_reprogramar, container, false);

        txtFechaServicio = (EditText) view.findViewById(R.id.txtFechaServicioRepro);
        txtFechaServicio.setText(Constants.ordenServicio.getFecReserva());
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

        txtHoraServicio = (EditText) view.findViewById(R.id.txtHoraServicioRepro);
        txtHoraServicio.setText(Constants.ordenServicio.getHorReserva());
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

        ImageButton btnSalir = (ImageButton) view.findViewById(R.id.idBtnAtras2);
        ImageButton btnAceptar = (ImageButton) view.findViewById(R.id.idBtnAceptaReprograma);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SolicitudServicioDetalleFragment fgSupervisorDetalle = new SolicitudServicioDetalleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgSupervisorDetalle);
                ft.commit();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fecha = sdf.parse(txtFechaServicio.getText().toString());
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Constants.ordenServicio.setFecReserva(sdf.format(fecha));
                } catch (ParseException ex) {
                    Log.v("Exception", ex.getLocalizedMessage());
                }

                Constants.ordenServicio.setHorReserva(txtHoraServicio.getText().toString());

                int fila = new LavaAutoDAO().reprogramarOrdenServicio(Constants.ordenServicio.getOrdenID(), Constants.ordenServicio.getFecReserva(),Constants.ordenServicio.getHorReserva());

                Toast.makeText(getActivity(),"Reprogramacion Realizada", Toast.LENGTH_LONG).show();

                SolicitudServicioFragment fgSupervisor = new SolicitudServicioFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fgSupervisor);
                ft.commit();
            }
        });
        return view;
    }
}
