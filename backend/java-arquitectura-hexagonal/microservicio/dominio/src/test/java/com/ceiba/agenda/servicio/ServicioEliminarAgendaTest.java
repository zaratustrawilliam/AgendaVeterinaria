package com.ceiba.agenda.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.agenda.servicio.testdatabuilder.AgendaTestDataBuilder;
import com.ceiba.agenda.servicios.ServicioEliminarAgenda;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.testdatabuilder.TipoMascotaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ServicioEliminarAgendaTest {
    
    @Test
    @DisplayName("Se debe eliminar la agenda de manera correcta")
    void deberiaEliminarAgenda(){
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
                .conId(1L)
                .conDireccionMascota(null)
                .conFechaAgenda(fechaCreacion)
                .build();
        
        Long idAgenda = 1L;
        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.existe(idAgenda)).thenReturn(Boolean.TRUE);
        Mockito.when(repositorioAgenda.buscarPorId(idAgenda)).thenReturn(agenda);
        ServicioEliminarAgenda servicioEliminarAgenda = new ServicioEliminarAgenda(repositorioAgenda);
        //act -assert
        servicioEliminarAgenda.ejecutar(idAgenda);
        Mockito.verify(repositorioAgenda,Mockito.times(1)).eliminar(idAgenda);
    }

    @Test
    @DisplayName("Debe lanzar una excepcion dado que no existe una agenda con ese id")
    void deberiaLanzarErrorNoExiste(){
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
                .conId(1L)
                .conDireccionMascota(null)
                .conFechaAgenda(fechaCreacion)
                .build();

        Long idAgenda = 1L;
        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.existe(idAgenda)).thenReturn(Boolean.FALSE);
        ServicioEliminarAgenda servicioEliminarAgenda = new ServicioEliminarAgenda(repositorioAgenda);
        //act -assert
        BasePrueba.assertThrows(() -> {
                    servicioEliminarAgenda.ejecutar(idAgenda);
                        }
                        , ExcepcionDuplicidad.class, "La agenda no existe en el sistema");
    }

    @Test
    @DisplayName("Debe lanzar una excepcion dado que no se puede eliminar un agenda que expirar dentro de 48 horas")
    void deberiaLanzarErrorAgendaExpirara(){
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

        LocalDateTime fechaCreacion = LocalDateTime.now().plusDays(1).withHour(9);

        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(1L)
                .conDireccionMascota(null)
                .conFechaAgenda(fechaCreacion)
                .build();

        Long idAgenda = 1L;
        RepositorioAgenda repositorioAgenda = Mockito.mock(RepositorioAgenda.class);
        Mockito.when(repositorioAgenda.existe(idAgenda)).thenReturn(Boolean.TRUE);
        Mockito.when(repositorioAgenda.buscarPorId(idAgenda)).thenReturn(agenda);
        ServicioEliminarAgenda servicioEliminarAgenda = new ServicioEliminarAgenda(repositorioAgenda);
        //act -assert
        BasePrueba.assertThrows(() -> {
                    servicioEliminarAgenda.ejecutar(idAgenda);
                }
                , ExcepcionValorInvalido.class, "El plazo para eliminar la agenda ya espiro,no se puede eliminar");
    }


}
