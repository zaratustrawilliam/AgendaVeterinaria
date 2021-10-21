package com.ceiba.mascota.servicios;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.tipomascota.puerto.repositorio.RepositorioTipoMascota;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioActualizarMascota {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private static final String LA_MASCOTA_NO_EXISTE = "La mascota no existe en el sistema";
    private static final String EL_TIPO_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "El tipo de mascota no existe en el sistema";

    private final RepositorioMascota repositorioMascota;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTipoMascota repositorioTipoMascota;

    public ServicioActualizarMascota(RepositorioMascota repositorioMascota, RepositorioUsuario repositorioUsuario, RepositorioTipoMascota repositorioTipoMascota) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTipoMascota = repositorioTipoMascota;
    }

    public void ejecutar(Mascota mascota){
        validarExistenciaUsuario(mascota.getUsuario().getId());
        validarExistenciaTipoMascota(mascota.getTipoMascota().getId());
        validarExistenciaMascota(mascota.getId());
        Mascota mascota1 = completarInstancia(mascota);
        repositorioMascota.actualizar(mascota1);
    }

    private void validarExistenciaUsuario(Long idUsuario){
        if(!repositorioUsuario.existePorId(idUsuario)){
          throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaMascota(Long idMascota){
        if(!repositorioMascota.existePorId(idMascota)){
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE);
        }
    }

    private void validarExistenciaTipoMascota(Long idTipoMascota){
        if(!repositorioTipoMascota.existePorId(idTipoMascota)){
            throw new ExcepcionDuplicidad(EL_TIPO_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private Mascota completarInstancia(Mascota mascotaActual){
        return new Mascota(mascotaActual.getId(),
                mascotaActual.getNombre(),
                repositorioUsuario.buscarPorId(mascotaActual.getUsuario().getId()),
                repositorioTipoMascota.buscarPorId(mascotaActual.getTipoMascota().getId()));
    }

}
