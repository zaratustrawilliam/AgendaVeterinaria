package com.ceiba.mascota.adaptador.dao;

import com.ceiba.jpa.model.MascotaJpa;
import com.ceiba.jpa.repository.ImpMascotaJpaRepository;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class DaoMascotaJpa implements DaoMascota {

    private final ImpMascotaJpaRepository impMascotaJpaRepository;

    @Autowired
    public DaoMascotaJpa(ImpMascotaJpaRepository impMascotaJpaRepository) {
        this.impMascotaJpaRepository = impMascotaJpaRepository;
    }

    @Override
    public List<DtoMascota> listar() {
        List<MascotaJpa> resultado =  impMascotaJpaRepository.findAll();
        if(!resultado.isEmpty()){
            return resultado.stream()
                    .map(MascotaJpa::fromMascota)
                    .collect(Collectors.toList())
                    .stream()
                    .map(DtoMascota::fromDtoMascota)
                    .collect(Collectors.toList());
        }else return new ArrayList<>();
    }

    @Override
    public List<DtoMascota> listarPorUsuario(Long idUsuario) {
        List<MascotaJpa> resultado = impMascotaJpaRepository.listarPorUsuario(idUsuario);
        if(!resultado.isEmpty()){
            return resultado.stream()
                    .map(MascotaJpa::fromMascota)
                    .collect(Collectors.toList())
                    .stream()
                    .map(DtoMascota::fromDtoMascota)
                    .collect(Collectors.toList());
        }else return new ArrayList<>();
    }
}
