package com.ceiba.jpa.repository;

import com.ceiba.jpa.model.AgendaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ImpAgendaJpaRepository extends JpaRepository<AgendaJpa,Long> {

    @Query("Select agenda From AgendaJpa agenda join agenda.mascota mascota where mascota.usuario.id =:idUsuario")
    List<AgendaJpa> findByUsuarioById(@Param("idUsuario") Long idUsuario);

    @Query("Select agenda From AgendaJpa agenda where agenda.fechaAgenda Between :fechaInicio and :fechaFin order by fechaAgenda ")
    List<AgendaJpa> buscarAgendasEntreFechas(@Param("fechaInicio")LocalDateTime fechaInicio,
                                             @Param("fechaFin")LocalDateTime fechaFin);
}
