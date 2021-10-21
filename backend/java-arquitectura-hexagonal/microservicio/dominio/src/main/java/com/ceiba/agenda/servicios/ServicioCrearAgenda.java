package com.ceiba.agenda.servicios;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

import java.time.LocalDateTime;

public class ServicioCrearAgenda {

    private static final String LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "El mascota no existe en el sistema";
    private static final String AGENDA_NO_ESTA_DISPONIBLE = "No esta disponible este espacio para ser agendado";

    private final RepositorioMascota repositorioMascota;
    private final RepositorioAgenda repositorioAgenda;

    public ServicioCrearAgenda(RepositorioMascota repositorioMascota, RepositorioAgenda repositorioAgenda) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioAgenda = repositorioAgenda;
    }

    public Long ejecutar(Agenda agenda){
        validarAgendaOcupada(agenda.getFechaAgenda());
        validarExistenciaMascota(agenda.getMascota().getId());
        agenda.calcularPrecio();
        agenda.eliminarMinutosFechaAgenda();
        return repositorioAgenda.crear(agenda);
    }

    private void validarAgendaOcupada(LocalDateTime fechaAgenda){
        if(!repositorioAgenda.agendasEntreFechas(fechaInicial(fechaAgenda),
                fechaFinal(fechaAgenda)).isEmpty()){
            throw new ExcepcionDuplicidad(AGENDA_NO_ESTA_DISPONIBLE);
        }
    }

    private void validarExistenciaMascota(Long idMascota){
        if(!repositorioMascota.existePorId(idMascota)){
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private LocalDateTime fechaInicial(LocalDateTime fechaAgenda){
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                fechaAgenda.getHour(),
                0);
    }

    private LocalDateTime fechaFinal(LocalDateTime fechaAgenda){
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                fechaAgenda.getHour(),
                59);
    }
}
