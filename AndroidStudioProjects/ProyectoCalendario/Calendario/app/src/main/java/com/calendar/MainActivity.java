package com.calendar;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String diasLecciones[] = {"lunes", "miércoles", "viernes", "domingo"};
    String estado="finalizado";
    TextView txt_leccion;
    int leccion=14;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        txt_leccion = (TextView) findViewById(R.id.txt_leccion);

         CalendarDay date= new CalendarDay();
         String diaDate= android.text.format.DateFormat.format("EEEE", date.getDate()).toString();

        MaterialCalendarView materialCalendarView= (MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 9, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        if(leccion!=24 || leccion==24 && estado!="finalizado"){
            int bandera=0;
            materialCalendarView.addDecorators(new HighlightWeekendsDecorator(), new TodayDecorator());

            for (i=0; i<diasLecciones.length; i++){
                if(diasLecciones[i].equalsIgnoreCase(diaDate)){
                    if(bandera==0){
                        if(estado=="finalizado"){
                            txt_leccion.setText("Hoy Lección: "+(leccion+1));
                            mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("¡Hola Lina!")
                                    .setContentText("Hoy tienes clase, empieza con la lección "+(leccion+1))
                                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                    .setAutoCancel(true);
                            mNotifyMgr.notify(1, mBuilder.build());
                            bandera=1;
                        }else{
                            txt_leccion.setText("Hoy Lección: "+leccion);
                            mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("¡Hola Lina!")
                                    .setContentText("Hoy tienes clase, empieza con la lección "+leccion)
                                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                    .setAutoCancel(true);
                            mNotifyMgr.notify(1, mBuilder.build());
                            bandera=1;
                        }
                    }
                }
            }

            if(i==diasLecciones.length && bandera==0){
                txt_leccion.setText("Hoy no tienes clase.");
                mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("¡Hola Lina!")
                        .setContentText("Hoy no tienes clase.")
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setAutoCancel(true);
                mNotifyMgr.notify(1, mBuilder.build());
            }
        }else{
            materialCalendarView.addDecorators(new TodayDecorator());
            txt_leccion.setText("Terminaste las lecciones ¡Felicidades!");
            mBuilder.setStyle(new NotificationCompat.BigTextStyle(mBuilder)
                    .bigText("Terminaste las 24 lecciones, ¡Espera nuestro nuevo curso!")
                    .setBigContentTitle("¡Hola Lina!"))
                    .setContentTitle("¡Hola Lina!")
                    .setContentText("Terminaste las 24 lecciones, ¡Espera nuestro nuevo curso!")
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher);
            mNotifyMgr.notify(1, mBuilder.build());
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int bandera = 0;
                String diaDate= android.text.format.DateFormat.format("EEEE", date.getDate()).toString();
                if (leccion != 24 || leccion == 24 && estado != "finalizado") {
                    for (i = 0; i < diasLecciones.length; i++) {
                        if (diasLecciones[i].equalsIgnoreCase(diaDate)) {
                            if (bandera == 0) {
                                AlertDialog.Builder dialogoClase = new AlertDialog.Builder(MainActivity.this);
                                dialogoClase.setTitle("Importante");
                                dialogoClase.setMessage("Tienes clase, empieza con la lección ahora");
                                dialogoClase.setCancelable(false);
                                dialogoClase.setPositiveButton("Ir a cuestionario", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogoClase, int id) {
                                        //code
                                    }
                                });
                                dialogoClase.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogoC, int id) {
                                        dialogoC.cancel();
                                    }
                                });
                                dialogoClase.show();
                                bandera = 1;
                            }
                        }
                    }

                    if (i == diasLecciones.length && bandera == 0) {
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                        dialogo.setTitle("Importante");
                        dialogo.setMessage("No tienes clase.");
                        dialogo.setCancelable(false);
                        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo, int id) {
                                dialogo.cancel();
                            }
                        });
                        dialogo.show();
                    }
                }
            }
        });
    }

    private class TodayDecorator implements DayViewDecorator {

        private final CalendarDay today;
        private final Drawable backgroundDrawable;

        public TodayDecorator() {
            today = CalendarDay.today();
            backgroundDrawable = getResources().getDrawable(R.drawable.today_circle_background);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return today.equals(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(backgroundDrawable);
        }
    }
}


