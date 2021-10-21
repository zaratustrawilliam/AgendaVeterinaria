package com.ceiba.agenda.puerto.repositorio;

import com.ceiba.agenda.modelo.entidad.Agenda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioAgenda {

    Long crear(Agenda agenda);

    void actualizar(Agenda agenda);

    void eliminar(Long idAgenda);

    boolean existe(Long idAgenda);

    Agenda buscarPorId(Long idAgenda);

    List<Agenda> agendasEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
