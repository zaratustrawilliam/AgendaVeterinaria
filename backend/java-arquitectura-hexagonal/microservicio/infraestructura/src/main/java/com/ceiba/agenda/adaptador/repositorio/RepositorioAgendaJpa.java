package com.ceiba.agenda.adaptador.repositorio;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.jpa.model.AgendaJpa;
import com.ceiba.jpa.repository.ImpAgendaJpaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class RepositorioAgendaJpa implements RepositorioAgenda {

    private final ImpAgendaJpaRepository impAgendaJpaRepository;

    public RepositorioAgendaJpa(ImpAgendaJpaRepository impAgendaJpaRepository) {
        this.impAgendaJpaRepository = impAgendaJpaRepository;
    }

    @Override
    public Long crear(Agenda agenda) {
        return impAgendaJpaRepository.save(AgendaJpa.toAgenda(agenda)).getId();
    }

    @Override
    public void actualizar(Agenda agenda) {
         impAgendaJpaRepository.save(AgendaJpa.toAgenda(agenda));
    }

    @Override
    public void eliminar(Long idAgenda) {
        impAgendaJpaRepository.delete(impAgendaJpaRepository.getById(idAgenda));
    }

    @Override
    public boolean existe(Long idAgenda) {
        return impAgendaJpaRepository.existsById(idAgenda);
    }

    @Override
    public Agenda buscarPorId(Long idAgenda) {
        return AgendaJpa.fromAgenda(impAgendaJpaRepository.getById(idAgenda));
    }

    @Override
    public List<Agenda> agendasEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return impAgendaJpaRepository
                .buscarAgendasEntreFechas(fechaInicio,fechaFin)
                .stream()
                .map(AgendaJpa::fromAgenda)
                .collect(Collectors.toList());
    }
}
