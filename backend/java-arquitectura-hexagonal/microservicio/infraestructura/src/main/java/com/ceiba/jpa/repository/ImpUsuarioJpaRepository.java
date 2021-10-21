package com.ceiba.jpa.repository;

import com.ceiba.jpa.model.UsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpUsuarioJpaRepository extends JpaRepository<UsuarioJpa,Long> {

    UsuarioJpa findByNombre (String nombre);
}
