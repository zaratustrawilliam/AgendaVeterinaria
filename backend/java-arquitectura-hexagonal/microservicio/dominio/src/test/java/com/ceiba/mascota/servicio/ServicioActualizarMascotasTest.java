package com.ceiba.mascota.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import com.ceiba.mascota.servicios.ServicioActualizarMascota;
import com.ceiba.mascota.servicios.ServicioCrearMascota;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.puerto.repositorio.RepositorioTipoMascota;
import com.ceiba.tipomascota.testdatabuilder.TipoMascotaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ServicioActualizarMascotasTest {

    @Test
    @DisplayName("Se debe actualizar el usuario de maneraCorrecta")
    void deberiaActualizarUsuarioDeManeraCorrecta(){
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Firulais")
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .conId(1L)
                .build();

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        Mockito.when(repositorioUsuario.buscarPorId(usuario.getId())).thenReturn(usuario);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.TRUE);
        Mockito.when(repositorioTipoMascota.buscarPorId(tipoMascota.getId())).thenReturn(tipoMascota);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(1L)).thenReturn(Boolean.TRUE);

        ServicioActualizarMascota servicioActualizarMascota = new ServicioActualizarMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act
        servicioActualizarMascota.ejecutar(mascota);

        Mockito.verify(repositorioMascota,Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Se debe lanzar error dado que el usuario no existe")
    void deberiaLanzarErrorUsuarioNoExiste(){
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Firulais")
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .build();

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.FALSE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);

        ServicioActualizarMascota servicioActualizarMascota = new ServicioActualizarMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioActualizarMascota.ejecutar(mascota);}
                , ExcepcionDuplicidad.class,"El usuario no existe en el sistema");
    }

    @Test
    @DisplayName("Se debe lanzar error dado que el tipo de mascota no existe")
    void deberiaLanzarErrorTipoMascotaNoExiste(){
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Firulais")
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .conId(1L)
                .build();

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.FALSE);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);

        ServicioActualizarMascota servicioActualizarMascota = new ServicioActualizarMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioActualizarMascota.ejecutar(mascota);}
                , ExcepcionDuplicidad.class,"El tipo de mascota no existe en el sistema");
    }

    @Test
    @DisplayName("Se debe lanzar error dado que no existe una mascota con ese id")
    void deberiaLanzarErrorMascotaNoExiste(){
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();

        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Firulais")
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .conId(1L)
                .build();

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.TRUE);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(1L)).thenReturn(Boolean.FALSE);

        ServicioActualizarMascota servicioActualizarMascota = new ServicioActualizarMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioActualizarMascota.ejecutar(mascota);}
                , ExcepcionDuplicidad.class,"La mascota no existe en el sistema");
    }
}
