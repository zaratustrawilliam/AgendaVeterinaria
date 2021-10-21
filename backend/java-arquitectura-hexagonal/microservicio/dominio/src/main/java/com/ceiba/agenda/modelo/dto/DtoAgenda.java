package com.ceiba.agenda.modelo.dto;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.mascota.modelo.entidad.Mascota;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoAgenda {

    private Long id;
    private Long idMascota;
    private LocalDateTime fechaAgenda;
    private BigDecimal precio;
    private String direccionMascota;

    public static DtoAgenda fromDtoAgenda(Agenda agenda){
        return new DtoAgenda(agenda.getId(),agenda.getMascota().getId(),
                agenda.getFechaAgenda(),agenda.getPrecio(),agenda.getDireccionMascota());
    }
}
