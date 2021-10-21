package com.ceiba.agenda.comando.manejador;

import com.ceiba.agenda.comando.fabrica.FabricaAgenda;
import com.ceiba.agenda.servicios.ServicioEliminarAgenda;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarAgenda implements ManejadorComando<Long> {

    private final ServicioEliminarAgenda servicioEliminarAgenda;
    private final FabricaAgenda fabricaAgenda;

    public ManejadorEliminarAgenda(ServicioEliminarAgenda servicioEliminarAgenda, FabricaAgenda fabricaAgenda) {
        this.servicioEliminarAgenda = servicioEliminarAgenda;
        this.fabricaAgenda = fabricaAgenda;
    }

    @Override
    public void ejecutar(Long comando) {
        servicioEliminarAgenda.ejecutar(comando);
    }
}
