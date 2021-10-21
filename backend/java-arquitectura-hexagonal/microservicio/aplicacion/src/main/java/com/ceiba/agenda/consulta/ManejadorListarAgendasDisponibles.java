package com.ceiba.agenda.consulta;

import com.ceiba.agenda.modelo.dto.DtoFechasDisponibles;
import com.ceiba.agenda.puerto.dao.DaoAgenda;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ManejadorListarAgendasDisponibles {

    private final DaoAgenda daoAgenda;

    public ManejadorListarAgendasDisponibles(DaoAgenda daoAgenda) {
        this.daoAgenda = daoAgenda;
    }

    public List<DtoFechasDisponibles> ejecutar(LocalDateTime fechaInicial,int resultados){
        return daoAgenda.listarAgendaLibre(fechaInicial,resultados);
    };
}
