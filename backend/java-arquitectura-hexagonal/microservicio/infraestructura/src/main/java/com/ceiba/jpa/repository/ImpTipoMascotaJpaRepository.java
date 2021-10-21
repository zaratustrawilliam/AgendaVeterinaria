package com.ceiba.jpa.repository;

import com.ceiba.jpa.model.TipoMascotaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpTipoMascotaJpaRepository extends JpaRepository<TipoMascotaJpa,Long> {
}
