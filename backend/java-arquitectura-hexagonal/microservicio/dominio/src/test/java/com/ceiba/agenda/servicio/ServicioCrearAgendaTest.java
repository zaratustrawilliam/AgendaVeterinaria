package com.ceiba.agenda.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.agenda.servicio.testdatabuilder.AgendaTestDataBuilder;
import com.ceiba.agenda.servicios.ServicioCrearAgenda;
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
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ServicioCrearAgendaTest {

    @Test
    @DisplayName("Se debe crear la agenda de manera correcta, su precio sera el estandar sin domicilio")
    void deberiaCrearAgendaDeManeraCorrecta() {
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

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.crear(agenda)).thenReturn(agenda.getId());

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act
        Long idAgenda = servicioCrearAgenda.ejecutar(agenda);

        //assert
        assertEquals(agenda.getId(), idAgenda);
        assertEquals(new BigDecimal(20000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).crear(agenda);
    }

    @Test
    @DisplayName("Se debe crear la agenda de manera correcta, su precio sera el estandar con domicilio")
    void deberiaCrearAgendaDeManeraCorrectaConDomicilio() {
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
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.crear(agenda)).thenReturn(agenda.getId());

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act
        Long idAgenda = servicioCrearAgenda.ejecutar(agenda);

        //assert
        assertEquals(agenda.getId(), idAgenda);
        assertEquals(new BigDecimal(25000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).crear(agenda);
    }

    @Test
    @DisplayName("Se debe crear la agenda de manera correcta, su precio sera el estandar " +
            "con domicilio en dia domingo o festivo")
    void deberiaCrearAgendaDeManeraCorrectaConDomicilioFestivo() {
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

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.crear(agenda)).thenReturn(agenda.getId());

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act
        Long idAgenda = servicioCrearAgenda.ejecutar(agenda);

        //assert
        assertEquals(agenda.getId(), idAgenda);
        assertEquals(new BigDecimal(28000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).crear(agenda);
    }

    @Test
    @DisplayName("Se debe crear la agenda de manera correcta, su precio sera el estandar " +
            "con domicilio en dia domingo")
    void deberiaCrearAgendaDeManeraCorrectaConDomicilioDomingo() {
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

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0);
        fechaCreacion = debeSerDomingo(fechaCreacion);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(true);
        Mockito.when(repositorioAgenda.crear(agenda)).thenReturn(agenda.getId());

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act
        Long idAgenda = servicioCrearAgenda.ejecutar(agenda);

        //assert
        assertEquals(agenda.getId(), idAgenda);
        assertEquals(new BigDecimal(28000), agenda.getPrecio());
        Mockito.verify(repositorioAgenda, Mockito.times(1)).crear(agenda);
    }

    @Test
    @DisplayName("Se debe lanzar una excepcion ya que la mascota no existe para esa referencia")
    void deberiaLanzarErrorMascotaNoExiste() {
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

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conFechaAgenda(fechaCreacion)
                .build();

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(false);

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioCrearAgenda.ejecutar(agenda);
                }
                , ExcepcionDuplicidad.class, "El mascota no existe en el sistema");
    }

    @Test
    @DisplayName("Se debe lanzar una excepcion ya que la agenda esta ocupado en es horario")
    void deberiaLanzarErrorAgendaNoDisponible() {
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

        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conFechaAgenda(fechaCreacion)
                .build();

        List<Agenda> listaSimulada = new LinkedList<>();
        listaSimulada.add(new Agenda());

        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.agendasEntreFechas(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenReturn(listaSimulada);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(mascota.getId())).thenReturn(false);

        ServicioCrearAgenda servicioCrearAgenda = new ServicioCrearAgenda
                (repositorioMascota, repositorioAgenda);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioCrearAgenda.ejecutar(agenda);
                }
                , ExcepcionDuplicidad.class, "No esta disponible este espacio para ser agendado");
    }

    private LocalDateTime debeSerDomingo(LocalDateTime fechaAgenda){
        do{
            if(!fechaAgenda.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                fechaAgenda.plusDays(1);
            }
        }while(fechaAgenda.getDayOfWeek().equals(DayOfWeek.SUNDAY));
        return fechaAgenda;
    }
}