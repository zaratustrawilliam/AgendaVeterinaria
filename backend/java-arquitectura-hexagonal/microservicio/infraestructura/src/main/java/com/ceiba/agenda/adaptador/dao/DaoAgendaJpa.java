package com.ceiba.agenda.adaptador.dao;

import com.ceiba.agenda.modelo.dto.DtoAgenda;
import com.ceiba.agenda.modelo.dto.DtoFechasDisponibles;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.dao.DaoAgenda;
import com.ceiba.jpa.model.AgendaJpa;
import com.ceiba.jpa.repository.ImpAgendaJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DaoAgendaJpa implements DaoAgenda {

    private final ImpAgendaJpaRepository impAgendaJpaRepository;

    public DaoAgendaJpa(ImpAgendaJpaRepository impAgendaJpaRepository) {
        this.impAgendaJpaRepository = impAgendaJpaRepository;
    }

    @Override
    public List<DtoAgenda> listar() {
        return impAgendaJpaRepository.findAll()
                .stream()
                .map(AgendaJpa::fromAgenda)
                .collect(Collectors.toList())
                .stream()
                .map(DtoAgenda::fromDtoAgenda)
                .collect(Collectors.toList());
    }

    @Override
    public List<DtoAgenda> listarPorUsuario(Long idUsuario) {
        return impAgendaJpaRepository.findByUsuarioById(idUsuario)
                .stream()
                .map(AgendaJpa::fromAgenda)
                .collect(Collectors.toList())
                .stream()
                .map(DtoAgenda::fromDtoAgenda)
                .collect(Collectors.toList());
    }

    @Override
    public List<DtoFechasDisponibles> listarAgendaLibre(LocalDateTime fechaInicial, int cantidadEspacios) {
       int contadorEspacios = 0;
       List<DtoFechasDisponibles> listaSalida = new ArrayList<>();
       int horaInicial = 7;
       if(validarFechaHoy(fechaInicial)){
           horaInicial = fechaInicial.getHour() + 1;
       }
       do{
           List<Integer> listaConsulta = impAgendaJpaRepository
                   .buscarAgendasEntreFechas(fechaInicial(fechaInicial),fechaFinal(fechaInicial))
                   .stream()
                   .map(AgendaJpa::fromAgenda)
                   .collect(Collectors.toList())
                   .stream()
                   .map(Agenda::getFechaAgenda)
                   .collect(Collectors.toList())
                   .stream()
                   .map(LocalDateTime::getHour)
                   .collect(Collectors.toList());
           for(int i = horaInicial; i < 16 ;i++){
               if(contadorEspacios == cantidadEspacios){
                   break;
               }
               if(!listaConsulta.contains(i)){
                   listaSalida.add(new DtoFechasDisponibles()
                           .buildCita(generarFechaDisponible(fechaInicial,i)));
                   contadorEspacios++;
               }
           }
           fechaInicial = fechaInicial.plusDays(1);
           horaInicial = 7;
       }while(contadorEspacios < cantidadEspacios);

        return listaSalida;
    }

    private LocalDateTime fechaInicial(LocalDateTime fechaAgenda){
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                7,
                0);
    }

    private LocalDateTime fechaFinal(LocalDateTime fechaAgenda){
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                16,
                59);
    }

    private LocalDateTime generarFechaDisponible(LocalDateTime fechaAgenda,int hora){
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                hora,
                0);
    }

    private boolean validarFechaHoy(LocalDateTime fecha){
        LocalDateTime actual = LocalDateTime.now();
        return fecha.getYear() == actual.getYear() &&
                fecha.getMonth() == actual.getMonth() &&
                fecha.getDayOfMonth() == actual.getDayOfMonth();
    }

}
