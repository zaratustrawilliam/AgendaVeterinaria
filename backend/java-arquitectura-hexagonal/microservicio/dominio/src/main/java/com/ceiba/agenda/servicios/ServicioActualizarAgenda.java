package com.ceiba.agenda.servicios;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioActualizarAgenda {

    private static final String LA_AGENDA_NO_EXISTE = "La agenda no existe en el sistema";
    private static final String LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "El mascota no existe en el sistema";
    private static final String AGENDA_NO_ESTA_DISPONIBLE = "No esta disponible este espacio para ser agendado";

    private final RepositorioAgenda repositorioAgenda;
    private final RepositorioMascota repositorioMascota;

    public ServicioActualizarAgenda(RepositorioAgenda repositorioAgenda, RepositorioMascota repositorioMascota) {
        this.repositorioAgenda = repositorioAgenda;
        this.repositorioMascota = repositorioMascota;
    }

    public void ejecutar(Agenda agenda) {
        validarExistenciaAgemda(agenda.getId());
        validarExistenciaMascota(agenda.getMascota().getId());

        Agenda agendaAnterior = repositorioAgenda.buscarPorId(agenda.getId());

        if ((agenda.getDireccionMascota() != null && !agenda.getDireccionMascota().isEmpty()
                && agendaAnterior.getDireccionMascota() != null && !agendaAnterior.getDireccionMascota().isEmpty()) &&
                (agenda.getFechaAgenda().getDayOfWeek() == agendaAnterior.getFechaAgenda().getDayOfWeek())) {
            agenda.ingresarValorAgenda(agendaAnterior.getPrecio());
        } else {
            agenda.calcularPrecio();
        }

        if (!(agenda.getFechaAgenda().getDayOfYear() == agendaAnterior.getFechaAgenda().getDayOfYear()
                && agenda.getFechaAgenda().getHour() == agendaAnterior.getFechaAgenda().getHour())) {
            validarAgendaOcupada(agenda.getFechaAgenda());
            if (agenda.getFechaAgenda().isAfter(agendaAnterior.getFechaAgenda()) &&
                    (agendaAnterior.getFechaAgenda().isAfter(LocalDateTime.now())) &&
                    (Math.abs(agendaAnterior.getFechaAgenda().until(LocalDateTime.now(), ChronoUnit.HOURS)) < 48)) {
                //penalizamos al usuario
                agenda.agregarValorMultaTrasladoCita();
            }

        }
        repositorioAgenda.actualizar(completarInstancias(agenda));
    }

    private void validarExistenciaAgemda(Long idAgenda) {
        if (!repositorioAgenda.existe(idAgenda)) {
            throw new ExcepcionDuplicidad(LA_AGENDA_NO_EXISTE);
        }
    }

    private void validarAgendaOcupada(LocalDateTime fechaAgenda) {
        if (!repositorioAgenda.agendasEntreFechas(fechaInicial(fechaAgenda),
                fechaFinal(fechaAgenda)).isEmpty()) {
            throw new ExcepcionDuplicidad(AGENDA_NO_ESTA_DISPONIBLE);
        }
    }

    private void validarExistenciaMascota(Long idMascota) {
        if (!repositorioMascota.existePorId(idMascota)) {
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private LocalDateTime fechaInicial(LocalDateTime fechaAgenda) {
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                fechaAgenda.getHour(),
                0);
    }

    private LocalDateTime fechaFinal(LocalDateTime fechaAgenda) {
        return LocalDateTime.of(fechaAgenda.getYear(),
                fechaAgenda.getMonth(),
                fechaAgenda.getDayOfMonth(),
                fechaAgenda.getHour(),
                59);
    }

    private Agenda completarInstancias(Agenda agenda){
        return new Agenda(agenda.getId(),repositorioMascota.buscarPorId(
                agenda.getMascota().getId()),agenda.getFechaAgenda(),
                agenda.getPrecio(),agenda.getDireccionMascota());
    }

}
