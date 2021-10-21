package com.ceiba.agenda.puerto.dao;

import com.ceiba.agenda.modelo.dto.DtoAgenda;
import com.ceiba.agenda.modelo.dto.DtoFechasDisponibles;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoAgenda {

    List<DtoAgenda> listar();

    List<DtoAgenda> listarPorUsuario(Long idUsuario);

    List<DtoFechasDisponibles> listarAgendaLibre(LocalDateTime fechaInicial, int cantidadEspacios);
}
