package com.ceiba.mascota.modelo.dto;

import com.ceiba.tipomascota.modelo.dto.DtoTipoMascota;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.mascota.modelo.entidad.Mascota;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoMascota {

    private Long id;
    private String nombre;
    private DtoUsuario usuario;
    private DtoTipoMascota tipoMascota;

    public static DtoMascota fromDtoMascota(Mascota mascota){
        return new DtoMascota(mascota.getId(),mascota.getNombre(),
                DtoUsuario.usuarioToDto(mascota.getUsuario()),
                DtoTipoMascota.fromTipoMascotaDto(mascota.getTipoMascota()));
    }
}
