package com.ceiba.tipomascota.puerto.repositorio;

import com.ceiba.tipomascota.modelo.entidad.TipoMascota;

public interface RepositorioTipoMascota {

     boolean existePorId(Long id);

     TipoMascota buscarPorId(Long id);
}
