package com.ceiba.mascota.controlador;

import com.ceiba.mascota.consulta.ManejadorListarMascotas;
import com.ceiba.mascota.consulta.ManejadorListarMascotasPorUsuario;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mascotas")
@Api(tags = {"Controlador consulta mascotas"})
public class ConsultaControladorMascota {

    private final ManejadorListarMascotas manejadorListarMascotas;
    private final ManejadorListarMascotasPorUsuario manejadorListarMascotasPorUsuario;

    public ConsultaControladorMascota(ManejadorListarMascotas manejadorListarMascotas, ManejadorListarMascotasPorUsuario manejadorListarMascotasPorUsuario) {
        this.manejadorListarMascotas = manejadorListarMascotas;
        this.manejadorListarMascotasPorUsuario = manejadorListarMascotasPorUsuario;
    }

    @GetMapping
    @ApiOperation("Se lista todas las mascotas")
    public List<DtoMascota> listar(){
        return manejadorListarMascotas.ejecutar();
    }

    @GetMapping("/{idUsuario}")
    @ApiOperation("Se lista las mascotas de un usuario en especifico")
    public List<DtoMascota> listarPorUsuario(@PathVariable Long idUsuario){
        return manejadorListarMascotasPorUsuario.ejecutar(idUsuario);
    }
}
