package com.ceiba.agenda.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.agenda.servicio.testdatabuilder.AgendaTestDataBuilder;
import com.ceiba.agenda.servicios.ServicioActualizarAgenda;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.testdatabuilder.TipoMascotaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ServicioActualizarAgendaTest {

    @Test
    @DisplayName("Se debe actualizar la agenda de manera correcta, no modifica hora,quita domicilio")
    void deberiaActualizarAgendaDeManeraCorrectaNoModificaHoraQuitaDomicilio() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota(null)
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act
        servicioActualizarAgenda.ejecutar(agenda);

        //assert
        assertEquals(new BigDecimal(20000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Se debe actualizar la agenda de manera correcta, no modifica hora,no quita domicilio")
    void deberiaActualizarAgendaDeManeraCorrectaNoModificaHoraNoQuitaDomicilio() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act
        servicioActualizarAgenda.ejecutar(agenda);

        //assert
        assertEquals(new BigDecimal(25000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Se debe actualizar la agenda de manera correcta, modifica fecha, la retrasa encontro espacio libre")
    void deberiaActualizarAgendaDeManeraCorrectaRetrasaFecha() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        LocalDateTime fechaCreacionAnterior = LocalDateTime.of(2021, Month.DECEMBER, 2, 8, 0);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacionAnterior)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act
        servicioActualizarAgenda.ejecutar(agenda);

        //assert
        assertEquals(new BigDecimal(25000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Se debe actualizar la agenda de manera correcta, modifica fecha, la fecha es posterior cancela antes " +
            "de las 48 horas")
    void deberiaActualizarAgendaDeManeraCorrectaModificaFechaPosteriorSinMulta() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        LocalDateTime fechaCreacionAnterior = LocalDateTime.now().plusDays(3).withHour(9);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacionAnterior)
                .build();

        agendaAnterior.calcularPrecio();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act
        servicioActualizarAgenda.ejecutar(agenda);
        BigDecimal diferenciaPrecios = agenda.getPrecio().subtract(agendaAnterior.getPrecio()).abs();
        //assert
        assertEquals(diferenciaPrecios.compareTo(new BigDecimal(10000)) >= 0, false);
        Mockito.verify(repositorioAgenda, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Se debe actualizar la agenda de manera correcta, modifica fecha, la fecha es posterior cancela dentro " +
            "de las 48 horas")
    void deberiaActualizarAgendaDeManeraCorrectaModificaFechaPosteriorConMulta() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        LocalDateTime fechaCreacionAnterior = LocalDateTime.now().plusDays(1).withHour(9);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacionAnterior)
                .build();

        agendaAnterior.calcularPrecio();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act
        servicioActualizarAgenda.ejecutar(agenda);
        BigDecimal diferenciaPrecios = agenda.getPrecio().subtract(agendaAnterior.getPrecio()).abs();
        //assert
        assertEquals(diferenciaPrecios.compareTo(new BigDecimal(10000)) >= 0, true);
        Mockito.verify(repositorioAgenda, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Lanza error, no existe la agenda")
    void lanzaErrorNoExisteAgenda() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();


        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(false);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioActualizarAgenda.ejecutar(agenda);
                }
                , ExcepcionDuplicidad.class, "La agenda no existe en el sistema");
    }

    @Test
    @DisplayName("Lanza error, no existe la mascota")
    void lanzaErrorNoExisteMascota() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();


        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(false);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioActualizarAgenda.ejecutar(agenda);
                }
                , ExcepcionDuplicidad.class, "El mascota no existe en el sistema");
    }

    @Test
    @DisplayName("Se debe lanzar error, no esta disponible ese espacio")
    void lanzarErrorEspacioNoEstaDisponible() {
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1L)
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 1, 8, 0);
        LocalDateTime fechaCreacionAnterior = LocalDateTime.now().plusDays(1).withHour(9);
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conPrecio(new BigDecimal(25000))
                .conDireccionMascota("San Judas")
                .conFechaAgenda(fechaCreacion)
                .build();

        Agenda agendaAnterior = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("San patricio")
                .conFechaAgenda(fechaCreacionAnterior)
                .build();

        agendaAnterior.calcularPrecio();

        List<Agenda> listaSimulada = new LinkedList<>();
        listaSimulada.add(new Agenda());

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(listaSimulada);
        Mockito.when(repositorioAgenda.existe(agenda.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.buscarPorId(agenda.getId())).thenReturn(agendaAnterior);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);

        ServicioActualizarAgenda servicioActualizarAgenda = new ServicioActualizarAgenda
                (repositorioAgenda,repositorioMascota);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioActualizarAgenda.ejecutar(agenda);
                }
                , ExcepcionDuplicidad.class, "No esta disponible este espacio para ser agendado");
    }
}
