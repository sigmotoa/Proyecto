package com.prueba;

import android.content.res.Configuration;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edt_leccion;
    String diasLecciones[] = {"Lunes", "miércoles", "Viernes", "Domingo"};
    int leccion=13;
    int leccionActual;
    CalendarView simpleCalendarView;
    CalendarView calendar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad  = "es"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);
        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        Date date = new Date();
        String diaDate= android.text.format.DateFormat.format("EEEE", date).toString();
        Log.i("Date",""+ date);
        edt_leccion = (EditText) findViewById(R.id.edt_leccion);
        for (int i=0; i<diasLecciones.length; i++){
             Log.i("Coincidencia",""+ diaDate);
            if(diasLecciones[i].equals(diaDate)){
                String coincidencia= "Coincide "+diaDate;
                leccionActual=leccion+1;
                edt_leccion.setText("Hoy: Lección "+leccionActual);
                Log.i("Coincidencia",""+ coincidencia+" Leccion: " +leccionActual);
                simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        // display the selected date by using a toast
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year+"///"+dayOfWeek, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }






    }


}

