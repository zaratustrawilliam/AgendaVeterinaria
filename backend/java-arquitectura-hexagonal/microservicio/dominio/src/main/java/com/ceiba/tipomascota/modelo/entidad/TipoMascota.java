package com.ceiba.tipomascota.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TipoMascota {
    private Long id;
    private String nombre;

    public TipoMascota(Long id){
        this.id = id;
    }
}
