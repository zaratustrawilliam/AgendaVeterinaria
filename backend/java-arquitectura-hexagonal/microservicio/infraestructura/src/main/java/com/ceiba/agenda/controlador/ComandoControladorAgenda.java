package com.ceiba.agenda.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.comando.manejador.ManejadorActualizarAgenda;
import com.ceiba.agenda.comando.manejador.ManejadorCrearAgenda;
import com.ceiba.agenda.comando.manejador.ManejadorEliminarAgenda;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendas")
@Api(tags = { "Controlador comando agendas"})
public class ComandoControladorAgenda {

    private final ManejadorCrearAgenda manejadorCrearAgenda;
    private final ManejadorActualizarAgenda manejadorActualizarAgenda;
    private final ManejadorEliminarAgenda manejadorEliminarAgenda;

    @Autowired
    public ComandoControladorAgenda(ManejadorCrearAgenda manejadorCrearAgenda,
                                    ManejadorActualizarAgenda manejadorActualizarAgenda,
                                    ManejadorEliminarAgenda manejadorEliminarAgenda) {
        this.manejadorCrearAgenda = manejadorCrearAgenda;
        this.manejadorActualizarAgenda = manejadorActualizarAgenda;
        this.manejadorEliminarAgenda = manejadorEliminarAgenda;
    }

    @PostMapping
    @ApiOperation("Permite crear una entidad agenda para un usuario")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoAgenda comandoAgenda){
        return manejadorCrearAgenda.ejecutar(comandoAgenda);
    }

    @PutMapping
    @ApiOperation("Actualiza una agenda ya existente")
    public void actualizar(@RequestBody ComandoAgenda comandoAgenda){
        manejadorActualizarAgenda.ejecutar(comandoAgenda);
    }

    @DeleteMapping(value="/{idAgenda}")
    @ApiOperation("Elimina una agenda existente")
    public void eliminar(@PathVariable Long idAgenda){
        manejadorEliminarAgenda.ejecutar(idAgenda);
    }
}
