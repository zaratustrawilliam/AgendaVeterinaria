package com.ceiba.dominio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneradorDiasFestivos {

    private GeneradorDiasFestivos(){
    }

    public static List<LocalDateTime> listarDiasFestivos(int anio){
        List<LocalDateTime> diasFestivos  = new LinkedList<>();
        /**
         * los dias fijos
         */
        diasFestivos.add(LocalDateTime.of(anio, Month.JANUARY,1,0,0));
        diasFestivos.add(LocalDateTime.of(anio, Month.MAY,1,0,0));
        diasFestivos.add(LocalDateTime.of(anio, Month.JULY,20,0,0));
        diasFestivos.add(LocalDateTime.of(anio, Month.AUGUST,7,0,0));
        diasFestivos.add(LocalDateTime.of(anio, Month.DECEMBER,8,0,0));
        diasFestivos.add(LocalDateTime.of(anio, Month.DECEMBER,25,0,0));
        /**
         * Festivos fecha trasladable
         */
        List<LocalDateTime> listaFestivosTrasladables = new LinkedList<>();
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.JANUARY,6,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.MARCH, 19,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.JUNE,29,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.AUGUST,15,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.OCTOBER,12,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.NOVEMBER,1,0,0));
        listaFestivosTrasladables.add(LocalDateTime.of(anio, Month.NOVEMBER,11,0,0));

        listaFestivosTrasladables.forEach(dia ->
            diasFestivos.add(dia.getDayOfWeek().equals(DayOfWeek.MONDAY)  ?
                    dia : ajustar(dia))
        );

        return diasFestivos;
    }

    public static List<Integer> daysToDaysOfYear(List<LocalDateTime> dias){
        return dias.stream()
                .map(LocalDateTime::getDayOfYear)
                .collect(Collectors.toList());
    }

    private static LocalDateTime ajustar(LocalDateTime fechaAjustable){
        if(fechaAjustable.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            return fechaAjustable;
        } return ajustar(fechaAjustable.plusDays(1));
    }
}
