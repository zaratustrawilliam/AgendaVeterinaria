package com.ceiba.usuario.modelo.dto;

import com.ceiba.usuario.modelo.entidad.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoUsuario {
    private Long id;
    private String nombre;
    private String clave;
    private LocalDateTime fechaCreacion;

    public static DtoUsuario usuarioToDto(Usuario usuario){
       return new DtoUsuario(usuario.getId(),
               usuario.getNombre(),usuario.getClave(),usuario.getFechaCreacion());
    }

}
