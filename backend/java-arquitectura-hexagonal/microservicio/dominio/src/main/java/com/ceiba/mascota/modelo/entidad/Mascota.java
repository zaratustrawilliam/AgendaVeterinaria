package com.ceiba.mascota.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.usuario.modelo.entidad.Usuario;
import lombok.Getter;

@Getter
public class Mascota {

    private static final String NOMBRE_MASCOTA_OBLIGATORIO = "Se debe ingresar el nombre de la mascota";
    private static final String AMO_MASCOTA_OBLIGATORIO = "Se debe relacionar la mascota a un usuario";
    private static final String TIPO_MASCOTA_OBLIGATORIO = "Debe indicar de que tipo es la mascota";

    private Long id;
    private String nombre;
    private Usuario usuario;
    private TipoMascota tipoMascota;

    public Mascota(Long id, String nombre, Usuario usuario, TipoMascota tipoMascota) {

        ValidadorArgumento.validarObligatorio(nombre,NOMBRE_MASCOTA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(usuario,AMO_MASCOTA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(usuario.getId(),AMO_MASCOTA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(tipoMascota,TIPO_MASCOTA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(tipoMascota.getId(),TIPO_MASCOTA_OBLIGATORIO);

        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.tipoMascota = tipoMascota;
    }

    public Mascota(Long id){
        this.id = id;
    }

}
