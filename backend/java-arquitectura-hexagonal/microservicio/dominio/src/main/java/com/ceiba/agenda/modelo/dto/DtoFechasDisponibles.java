package com.ceiba.agenda.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DtoFechasDisponibles {
    private String dia;
    private String mes;
    private String anio;
    private String hora;

    private static final String COLON = ":";

    public DtoFechasDisponibles buildCita(LocalDateTime fechaCita){

        StringBuilder builderHora = new StringBuilder();
        builderHora.append(fechaCita.getHour());
        builderHora.append(COLON);
        builderHora.append(fechaCita.getMinute());

        return new DtoFechasDisponibles(String.valueOf(fechaCita.getDayOfMonth()),
                fechaCita.getMonth().getDisplayName(TextStyle.FULL, new Locale ( "es" , "ES" ))
                ,String.valueOf(fechaCita.getYear()),builderHora.toString());
    }
}
