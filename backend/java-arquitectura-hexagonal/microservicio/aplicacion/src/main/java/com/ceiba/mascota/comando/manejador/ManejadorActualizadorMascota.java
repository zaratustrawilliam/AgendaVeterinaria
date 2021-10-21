package com.ceiba.mascota.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.servicios.ServicioActualizarMascota;
import org.springframework.stereotype.Component;

@Component
public class  ManejadorActualizadorMascota implements ManejadorComando<ComandoMascota> {

    private final ServicioActualizarMascota servicioActualizarMascota;
    private final FabricaMascota fabricaMascota;

    public ManejadorActualizadorMascota(ServicioActualizarMascota servicioActualizarMascota, FabricaMascota fabricaMascota) {
        this.servicioActualizarMascota = servicioActualizarMascota;
        this.fabricaMascota = fabricaMascota;
    }

    @Override
    public void ejecutar(ComandoMascota comando) {
        servicioActualizarMascota.ejecutar(
                fabricaMascota.crearMascota(comando));
    }
}
