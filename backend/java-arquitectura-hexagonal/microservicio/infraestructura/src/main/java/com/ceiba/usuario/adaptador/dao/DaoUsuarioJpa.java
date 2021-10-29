package com.ceiba.usuario.adaptador.dao;

import com.ceiba.jpa.model.UsuarioJpa;
import com.ceiba.jpa.repository.ImpUsuarioJpaRepository;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class DaoUsuarioJpa implements DaoUsuario {

    private final ImpUsuarioJpaRepository repositorio;

    @Autowired
    public DaoUsuarioJpa(ImpUsuarioJpaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DtoUsuario> listar() {
        List<UsuarioJpa> resultados = repositorio.findAll();
        if(!resultados.isEmpty()){
            return resultados
                    .stream()
                    .map(UsuarioJpa::toUsuario)
                    .collect(Collectors.toList())
                    .stream()
                    .map(DtoUsuario::usuarioToDto)
                    .collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }

    }

    @Override
    public DtoUsuario buscarPorId(Long idUsuario) {
        return DtoUsuario.usuarioToDto(
                UsuarioJpa.toUsuario(repositorio.findById(idUsuario).get()));
    }
}
