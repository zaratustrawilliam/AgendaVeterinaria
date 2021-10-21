package com.ceiba.tipomascota.adaptador.repositorio;

import com.ceiba.jpa.model.TipoMascotaJpa;
import com.ceiba.jpa.repository.ImpTipoMascotaJpaRepository;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.puerto.repositorio.RepositorioTipoMascota;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTipoMascotaJpa implements RepositorioTipoMascota {

    private final ImpTipoMascotaJpaRepository impTipoMascotaJpaRepository;

    public RepositorioTipoMascotaJpa(ImpTipoMascotaJpaRepository impTipoMascotaJpaRepository) {
        this.impTipoMascotaJpaRepository = impTipoMascotaJpaRepository;
    }

    @Override
    public boolean existePorId(Long id) {
        return impTipoMascotaJpaRepository.existsById(id);
    }

    @Override
    public TipoMascota buscarPorId(Long id) {
        return TipoMascotaJpa.fromTipoMascota(impTipoMascotaJpaRepository.getById(id));
    }
}
