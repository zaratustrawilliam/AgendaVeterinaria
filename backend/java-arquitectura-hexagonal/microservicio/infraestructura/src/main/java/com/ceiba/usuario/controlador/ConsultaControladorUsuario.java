package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorConsultarUsuario;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;
    private final ManejadorConsultarUsuario manejadorConsultarUsuario;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios, ManejadorConsultarUsuario manejadorConsultarUsuario) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
        this.manejadorConsultarUsuario = manejadorConsultarUsuario;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping("/{idUsuario}")
    @ApiOperation("Extraer usuario por su id")
    public DtoUsuario buscarPorId(@PathVariable Long idUsuario){
        return this.manejadorConsultarUsuario.ejecutar(idUsuario);
    }

}
