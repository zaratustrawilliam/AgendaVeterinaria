package com.ceiba.tipomascota.modelo.dto;

import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTipoMascota {
    private Long id;
    private String nombre;

    public static DtoTipoMascota fromTipoMascotaDto(TipoMascota tipoMascota){
        return new DtoTipoMascota(tipoMascota.getId(),tipoMascota.getNombre());
    }
}
