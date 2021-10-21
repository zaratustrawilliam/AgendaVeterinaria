package com.ceiba.mascota.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearMascotaTest {

    @Test
    @DisplayName("Se debe crear el usuario de maneraCorrecta")
    void deberiaCrearUsuarioDeManeraCorrecta(){
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
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.TRUE);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorUsuarioNombreMascota(1L,"Firulais")).thenReturn(Boolean.FALSE);
        Mockito.when(repositorioMascota.crear(mascota)).thenReturn(10L);

        ServicioCrearMascota servicioCrearMascotaTest = new ServicioCrearMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act
        Long idMascota = servicioCrearMascotaTest.ejecutar(mascota);

        //assert
        assertEquals(10L,idMascota);
        Mockito.verify(repositorioMascota,Mockito.times(1)).crear(mascota);
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

        ServicioCrearMascota servicioCrearMascotaTest = new ServicioCrearMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioCrearMascotaTest.ejecutar(mascota);}
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
                .build();

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.FALSE);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);

        ServicioCrearMascota servicioCrearMascotaTest = new ServicioCrearMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioCrearMascotaTest.ejecutar(mascota);}
                , ExcepcionDuplicidad.class,"El tipo de mascota no existe en el sistema");
    }

    @Test
    @DisplayName("Se debe lanzar error dado que ya existe una mascota con ese nombre para ese usuario")
    void deberiaLanzarErrorMascotaUsuarioExiste(){
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
        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(Boolean.TRUE);
        RepositorioTipoMascota repositorioTipoMascota = Mockito.mock(RepositorioTipoMascota.class);
        Mockito.when(repositorioTipoMascota.existePorId(tipoMascota.getId())).thenReturn(Boolean.TRUE);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorUsuarioNombreMascota(1L,"Firulais")).thenReturn(Boolean.TRUE);

        ServicioCrearMascota servicioCrearMascotaTest = new ServicioCrearMascota(repositorioMascota,
                repositorioUsuario,repositorioTipoMascota);

        //act -assert
        BasePrueba.assertThrows(()->{servicioCrearMascotaTest.ejecutar(mascota);}
                , ExcepcionDuplicidad.class,"El mascota ya existe en el sistema");
    }
}
