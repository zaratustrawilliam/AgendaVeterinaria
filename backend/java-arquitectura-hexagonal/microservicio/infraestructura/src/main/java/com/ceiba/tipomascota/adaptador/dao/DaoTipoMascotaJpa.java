package com.ceiba.tipomascota.adaptador.dao;

import com.ceiba.jpa.model.TipoMascotaJpa;
import com.ceiba.jpa.repository.ImpTipoMascotaJpaRepository;
import com.ceiba.tipomascota.modelo.dto.DtoTipoMascota;
import com.ceiba.tipomascota.puerto.dao.DaoTipoMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class DaoTipoMascotaJpa implements DaoTipoMascota {

    private final ImpTipoMascotaJpaRepository repository;

    @Autowired
    public DaoTipoMascotaJpa(ImpTipoMascotaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DtoTipoMascota> listar() {
        List<TipoMascotaJpa> resultados = repository.findAll();
        if(!resultados.isEmpty()){
            return resultados.stream()
                    .map(TipoMascotaJpa::fromTipoMascota)
                    .toList()
                    .stream()
                    .map(DtoTipoMascota::fromTipoMascotaDto)
                    .toList();
        }else {
            return new ArrayList<>();
        }
    }
}
