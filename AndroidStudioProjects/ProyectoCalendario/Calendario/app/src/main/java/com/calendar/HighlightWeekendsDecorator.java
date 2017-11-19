package com.calendar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Highlight Saturdays and Sundays with a background
 */
public class HighlightWeekendsDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#228BC34A");
    private static final int colorSpan = Color.parseColor("#000000");


    String diasLecciones[] = {"lunes", "miércoles", "viernes", "domingo"};
    private final List list = new ArrayList();
    private int bandera=0;

    public HighlightWeekendsDecorator() {
        highlightDrawable = new ColorDrawable(color);
        bandera=0;
        if(bandera==0){
            for (int i=0; i<diasLecciones.length; i++){
                if(diasLecciones[i].equalsIgnoreCase("Lunes")){
                    list.add(2);
                }
                else if(diasLecciones[i].equalsIgnoreCase("Martes")){
                    list.add(3);
                }
                else if(diasLecciones[i].equalsIgnoreCase("Miércoles")){
                    list.add(4 );
                }
                else if(diasLecciones[i].equalsIgnoreCase("Jueves")){
                    list.add(5);
                }
                else if(diasLecciones[i].equalsIgnoreCase("Viernes")){
                    list.add(6);

                }
                else if(diasLecciones[i].equalsIgnoreCase("Sábado")){
                    list.add(7);
                }
                else if(diasLecciones[i].equalsIgnoreCase("Domingo")){
                    list.add(1);
                }
                bandera=1;
            }
        }
    }
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int lenght= list.size();
        int dia1;
        int dia2;
        int dia3;
        int dia4;
        int dia5;
        int dia6;
        int dia7;

        switch (lenght){
            case 1:
                dia1= Integer.parseInt(list.get(0).toString());
                return weekDay==dia1;

            case 2:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());

                return weekDay==dia1 || weekDay==dia2;

            case 3:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());
                dia3= Integer.parseInt(list.get(2).toString());

                return weekDay==dia1 || weekDay==dia2 || weekDay==dia3;

            case 4:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());
                dia3= Integer.parseInt(list.get(2).toString());
                dia4= Integer.parseInt(list.get(3).toString());

                return weekDay==dia1 || weekDay==dia2 || weekDay==dia3 || weekDay==dia4;

            case 5:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());
                dia3= Integer.parseInt(list.get(2).toString());
                dia4= Integer.parseInt(list.get(3).toString());
                dia5= Integer.parseInt(list.get(4).toString());

                return weekDay==dia1 || weekDay==dia2 || weekDay==dia3 || weekDay==dia4 ||  weekDay==dia5;

            case 6:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());
                dia3= Integer.parseInt(list.get(2).toString());
                dia4= Integer.parseInt(list.get(3).toString());
                dia5= Integer.parseInt(list.get(4).toString());
                dia6= Integer.parseInt(list.get(5).toString());

                return weekDay==dia1 || weekDay==dia2 || weekDay==dia3 || weekDay==dia4 ||  weekDay==dia5 || weekDay==dia6;

            case 7:
                dia1 = Integer.parseInt(list.get(0).toString());
                dia2= Integer.parseInt(list.get(1).toString());
                dia3= Integer.parseInt(list.get(2).toString());
                dia4= Integer.parseInt(list.get(3).toString());
                dia5= Integer.parseInt(list.get(4).toString());
                dia6= Integer.parseInt(list.get(5).toString());
                dia7= Integer.parseInt(list.get(6).toString());

                return weekDay==dia1 || weekDay==dia2 || weekDay==dia3 || weekDay==dia4 ||  weekDay==dia5 || weekDay==dia6 || weekDay==dia7;
        }

        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
        view.addSpan(new DotSpan(5, colorSpan));
    }
}
