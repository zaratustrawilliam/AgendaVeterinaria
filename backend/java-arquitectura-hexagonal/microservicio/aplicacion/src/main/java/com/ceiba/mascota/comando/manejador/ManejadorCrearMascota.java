package com.ceiba.mascota.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.servicios.ServicioCrearMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearMascota implements ManejadorComandoRespuesta<ComandoMascota, ComandoRespuesta<Long>> {

    private final ServicioCrearMascota servicioCrearMascota;
    private final FabricaMascota fabricaMascota;

    public ManejadorCrearMascota(ServicioCrearMascota servicioCrearMascota, FabricaMascota fabricaMascota) {
        this.servicioCrearMascota = servicioCrearMascota;
        this.fabricaMascota = fabricaMascota;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoMascota comando) {
        return new ComandoRespuesta<>(servicioCrearMascota.ejecutar(
                fabricaMascota.crearMascota(comando)));
    }
}
