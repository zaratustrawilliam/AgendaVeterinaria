package com.ceiba.mascota.servicios;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.tipomascota.puerto.repositorio.RepositorioTipoMascota;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;


public class ServicioCrearMascota {

    private static final String LA_MASCOTA_YA_EXISTE_EN_EL_SISTEMA = "El mascota ya existe en el sistema";
    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private static final String EL_TIPO_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "El tipo de mascota no existe en el sistema";

    private final RepositorioMascota repositorioMascota;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTipoMascota repositorioTipoMascota;

    public ServicioCrearMascota(RepositorioMascota repositorioMascota,
                                RepositorioUsuario repositorioUsuario,
                                RepositorioTipoMascota repositorioTipoMascota) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTipoMascota = repositorioTipoMascota;
    }

    public Long ejecutar(Mascota mascota){
        validarExistenciaUsuario(mascota.getUsuario().getId());
        validarExistenciaTipoMascota(mascota.getTipoMascota().getId());
        validarExistenciaPrevia(mascota.getUsuario().getId(), mascota.getNombre());
        return repositorioMascota.crear(mascota);
    }

    private boolean validarExistenciaPrevia(Long idUsuario,String nombreMascota){
        if(repositorioMascota.existePorUsuarioNombreMascota(idUsuario,nombreMascota)){
            throw new ExcepcionDuplicidad(LA_MASCOTA_YA_EXISTE_EN_EL_SISTEMA);
        }else{
            return Boolean.FALSE;
        }
    }

    private void validarExistenciaUsuario(Long idUsuario){
        if(!repositorioUsuario.existePorId(idUsuario)){
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaTipoMascota(Long idTipoMascota){
        if(!repositorioTipoMascota.existePorId(idTipoMascota)){
            throw new ExcepcionDuplicidad(EL_TIPO_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
