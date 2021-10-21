package com.ceiba.agenda.servicios;

import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioEliminarAgenda {

    private static final String LA_AGENDA_NO_EXISTE = "La agenda no existe en el sistema";
    private static final String AGENDA_NO_SE_PUEDE_ELIMINAR = "La fecha de la agenda ya expiro, no se puede eliminar";
    private static final String AGENDA_NO_SE_PUEDE_ELIMINAR_PLAZO_EXPIRO = "El plazo para eliminar la agenda ya espiro,no se puede eliminar";

    private final RepositorioAgenda repositorioAgenda;

    public ServicioEliminarAgenda(RepositorioAgenda repositorioAgenda) {
        this.repositorioAgenda = repositorioAgenda;
    }

    public void ejecutar(Long idAgenda){
        validarExistenciaAgemda(idAgenda);
        validarReglaEliminarConTiempo(idAgenda);
        repositorioAgenda.eliminar(idAgenda);
    }

    private void validarExistenciaAgemda(Long idAgenda){
        if(!repositorioAgenda.existe(idAgenda)){
            throw new ExcepcionDuplicidad(LA_AGENDA_NO_EXISTE);
        }
    }

    /**
     * Se valida que se elimina la agenda 48 horas antes, de lo contrario se rechaza
     */
    private void validarReglaEliminarConTiempo(Long idAgenda){
        Agenda agenda = repositorioAgenda.buscarPorId(idAgenda);
        if(agenda.getFechaAgenda().isAfter(LocalDateTime.now())){
            if(Math.abs(agenda.getFechaAgenda().until(LocalDateTime.now(), ChronoUnit.HOURS)) < 48){
                throw new ExcepcionValorInvalido(AGENDA_NO_SE_PUEDE_ELIMINAR_PLAZO_EXPIRO);
            }
        }else {
            throw new ExcepcionValorInvalido(AGENDA_NO_SE_PUEDE_ELIMINAR);
        }
    }
}
