package com.ceiba.agenda.servicioTestDataBuilder;

import com.ceiba.agenda.comando.ComandoAgenda;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComandoAgendaTestDataBuilder {

    private Long id;
    private Long idMascota;
    private LocalDateTime fechaAgenda;
    private BigDecimal precio;
    private String direccionMascota;

    public ComandoAgendaTestDataBuilder(){
        fechaAgenda = LocalDateTime.now().plusHours(1);
        direccionMascota = "Avenida Bulevar";
        precio = BigDecimal.ZERO;
    }

    public ComandoAgendaTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public ComandoAgendaTestDataBuilder conMascota(Long idMascota){
        this.idMascota=idMascota;
        return this;
    }

    public ComandoAgendaTestDataBuilder conFechaAgenda(LocalDateTime fechaAgenda){
        this.fechaAgenda=fechaAgenda;
        return this;
    }

    public ComandoAgendaTestDataBuilder conPrecio(BigDecimal precio){
        this.precio=precio;
        return this;
    }

    public ComandoAgendaTestDataBuilder conDireccionMascota(String direccionMascota){
        this.direccionMascota=direccionMascota;
        return this;
    }

    public ComandoAgenda build(){ return new ComandoAgenda(id,idMascota,fechaAgenda,precio,direccionMascota);}
}
