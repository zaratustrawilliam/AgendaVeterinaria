package com.ceiba.tipomascota.controlador;

import com.ceiba.tipomascota.consulta.ManejadorListaTipoMascota;
import com.ceiba.tipomascota.modelo.dto.DtoTipoMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tiposmascotas")
@Api(tags = {"Controlador consulta tipos mascotas"})
public class ConsultaControladorTipoMascota {

    private final ManejadorListaTipoMascota manejadorListaTipoMascota;

    public ConsultaControladorTipoMascota(ManejadorListaTipoMascota manejadorListaTipoMascota) {
        this.manejadorListaTipoMascota = manejadorListaTipoMascota;
    }

    @GetMapping
    @ApiOperation("Listar Tipos mascotas")
    public List<DtoTipoMascota> listar(){
        return manejadorListaTipoMascota.ejecutar();
    }

}
