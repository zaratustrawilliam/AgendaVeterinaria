package com.ceiba.mascota.servicios;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioEliminarMascota {

    private static final String LA_MASCOTA_NO_EXISTE = "La mascota no existe en el sistema";

    private final RepositorioMascota repositorioMascota;

    public ServicioEliminarMascota(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    public void ejecutar(Long idMascota){
        validarExistenciaMascota(idMascota);
        repositorioMascota.eliminar(idMascota);
    }

    private void validarExistenciaMascota(Long idMascota){
        if(!repositorioMascota.existePorId(idMascota)){
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE);
        }
    }
}
