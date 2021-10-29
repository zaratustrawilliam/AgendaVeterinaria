package com.ceiba.mascota.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.manejador.ManejadorActualizadorMascota;
import com.ceiba.mascota.comando.manejador.ManejadorCrearMascota;
import com.ceiba.mascota.comando.manejador.ManejadorEliminarMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mascotas")
@Api(tags = { "Controlador comando mascotas"})
public class ComandoControladorMascotas {

    private final ManejadorActualizadorMascota manejadorActualizadorMascota;
    private final ManejadorEliminarMascota manejadorEliminarMascota;
    private final ManejadorCrearMascota manejadorCrearMascota;

    @Autowired
    public ComandoControladorMascotas(ManejadorActualizadorMascota manejadorActualizadorMascota,
                                      ManejadorEliminarMascota manejadorEliminarMascota,
                                      ManejadorCrearMascota manejadorCrearMascota) {
        this.manejadorActualizadorMascota = manejadorActualizadorMascota;
        this.manejadorEliminarMascota = manejadorEliminarMascota;
        this.manejadorCrearMascota = manejadorCrearMascota;
    }

    @PostMapping
    @ApiOperation("Permite crear una entidad mascota para un usuario")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoMascota comandoMascota){
        return manejadorCrearMascota.ejecutar(comandoMascota);
    }

    @PutMapping
    @ApiOperation("Actualiza una mascota ya existente")
    public void actualizar(@RequestBody ComandoMascota comandoMascota){
        manejadorActualizadorMascota.ejecutar(comandoMascota);
    }

    @DeleteMapping(value="/{idMascota}")
    @ApiOperation("Elimina una mascota existente")
    public void eliminar(@PathVariable Long idMascota){
        manejadorEliminarMascota.ejecutar(idMascota);
    }
}
