package com.ceiba.mascota.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.servicios.ServicioEliminarMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarMascota implements ManejadorComando<Long> {

    private final ServicioEliminarMascota servicioEliminarMascota;
    private final FabricaMascota fabricaMascota;

    public ManejadorEliminarMascota(ServicioEliminarMascota servicioEliminarMascota, FabricaMascota fabricaMascota) {
        this.servicioEliminarMascota = servicioEliminarMascota;
        this.fabricaMascota = fabricaMascota;
    }

    @Override
    public void ejecutar(Long comando) {
        servicioEliminarMascota.ejecutar(comando);
    }
}
