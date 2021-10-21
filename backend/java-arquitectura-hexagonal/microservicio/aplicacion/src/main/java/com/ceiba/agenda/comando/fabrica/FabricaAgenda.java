package com.ceiba.agenda.comando.fabrica;

import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.mascota.modelo.entidad.Mascota;
import org.springframework.stereotype.Component;

@Component
public class FabricaAgenda {

    public Agenda crearAgenda(ComandoAgenda comandoAgenda){
        return new Agenda(comandoAgenda.getId(),
                new Mascota(comandoAgenda.getIdMascota()),
                comandoAgenda.getFechaAgenda(),comandoAgenda.getPrecio(),
                comandoAgenda.getDireccionMascota());
    }
}
