package com.ceiba.agenda.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.comando.fabrica.FabricaAgenda;
import com.ceiba.agenda.servicios.ServicioCrearAgenda;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearAgenda implements ManejadorComandoRespuesta<ComandoAgenda, ComandoRespuesta<Long>> {

    private final ServicioCrearAgenda servicioCrearAgenda;
    private final FabricaAgenda fabricaAgenda;

    public ManejadorCrearAgenda(ServicioCrearAgenda servicioCrearAgenda, FabricaAgenda fabricaAgenda) {
        this.servicioCrearAgenda = servicioCrearAgenda;
        this.fabricaAgenda = fabricaAgenda;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoAgenda comando) {
        return new ComandoRespuesta<>(servicioCrearAgenda.ejecutar(
                fabricaAgenda.crearAgenda(comando)));
    }
}
