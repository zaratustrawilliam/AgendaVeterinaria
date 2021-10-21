package com.ceiba.agenda.comando.manejador;

import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.comando.fabrica.FabricaAgenda;
import com.ceiba.agenda.servicios.ServicioActualizarAgenda;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarAgenda implements ManejadorComando<ComandoAgenda> {

    private final ServicioActualizarAgenda servicioActualizarAgenda;
    private final FabricaAgenda fabricaAgenda;

    public ManejadorActualizarAgenda(ServicioActualizarAgenda servicioActualizarAgenda, FabricaAgenda fabricaAgenda) {
        this.servicioActualizarAgenda = servicioActualizarAgenda;
        this.fabricaAgenda = fabricaAgenda;
    }

    @Override
    public void ejecutar(ComandoAgenda comando) {
        servicioActualizarAgenda.ejecutar(fabricaAgenda.crearAgenda(comando));
    }
}
