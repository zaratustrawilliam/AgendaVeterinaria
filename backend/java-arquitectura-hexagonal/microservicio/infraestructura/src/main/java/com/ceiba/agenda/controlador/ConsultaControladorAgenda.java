package com.ceiba.agenda.controlador;

import com.ceiba.agenda.consulta.ManejadorListarAgendas;
import com.ceiba.agenda.consulta.ManejadorListarAgendasDisponibles;
import com.ceiba.agenda.consulta.ManejadorListarAgendasPorUsuario;
import com.ceiba.agenda.modelo.dto.DtoAgenda;
import com.ceiba.agenda.modelo.dto.DtoFechasDisponibles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendas")
@Api(tags={"Controlador consulta agenda"})
public class ConsultaControladorAgenda {

    private final ManejadorListarAgendas manejadorListarAgendas;
    private final ManejadorListarAgendasPorUsuario manejadorListarAgendasPorUsuario;
    private final ManejadorListarAgendasDisponibles manejadorListarAgendasDisponibles;


    public ConsultaControladorAgenda(ManejadorListarAgendas manejadorListarAgendas,
                                     ManejadorListarAgendasPorUsuario manejadorListarAgendasPorUsuario,
                                     ManejadorListarAgendasDisponibles manejadorListarAgendasDisponibles) {
        this.manejadorListarAgendas = manejadorListarAgendas;
        this.manejadorListarAgendasPorUsuario = manejadorListarAgendasPorUsuario;
        this.manejadorListarAgendasDisponibles = manejadorListarAgendasDisponibles;
    }

    @GetMapping
    @ApiOperation("Listar Agendas")
    public List<DtoAgenda> listar(){ return this.manejadorListarAgendas.ejecutar();}

    @GetMapping("/{idUsuario}")
    @ApiOperation("Se lista las agendas de un usuario en especifico")
    public List<DtoAgenda> listarPorUsuario(@PathVariable Long idUsuario){
        return manejadorListarAgendasPorUsuario.ejecutar(idUsuario);
    }

    @GetMapping("/disponibilidad")
    @ApiOperation("Se lista las agendas de un usuario en especifico")
    public List<DtoFechasDisponibles> listarDisponibilidad(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                       LocalDateTime fecha, @RequestParam int registros){
        return manejadorListarAgendasDisponibles.ejecutar(fecha,registros);
    }
}
