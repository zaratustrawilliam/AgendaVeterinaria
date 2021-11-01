package com.ceiba.agenda.servicio.testdatabuilder;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.mascota.modelo.entidad.Mascota;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AgendaTestDataBuilder {

    private Long id;
    private Mascota mascota;
    private LocalDateTime fechaAgenda;
    private String direccionMascota;
    private BigDecimal precio;

    public AgendaTestDataBuilder(){
        fechaAgenda = LocalDateTime.now();
        direccionMascota = "Calle arturo # 56 - 56";
    }

    public AgendaTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public AgendaTestDataBuilder conMascota(Mascota mascota){
        this.mascota=mascota;
        return this;
    }

    public AgendaTestDataBuilder conFechaAgenda(LocalDateTime fechaAgenda){
        this.fechaAgenda=fechaAgenda;
        return this;
    }

    public AgendaTestDataBuilder conDireccionMascota(String direccionMascota){
        this.direccionMascota=direccionMascota;
        return this;
    }

    public AgendaTestDataBuilder conPrecio(BigDecimal precio){
        this.precio = precio;
        return this;
    }

    public Agenda build(){
        return new Agenda(id,mascota,fechaAgenda,precio,direccionMascota);
    }

    public Agenda build(boolean mapper){
        return new Agenda(id,mascota,fechaAgenda,precio,direccionMascota,mapper);
    }
}
