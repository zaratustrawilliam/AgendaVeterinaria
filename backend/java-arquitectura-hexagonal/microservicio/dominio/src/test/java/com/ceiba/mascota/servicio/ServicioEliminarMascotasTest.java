package com.ceiba.mascota.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicios.ServicioEliminarMascota;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarMascotasTest {

    @Test
    @DisplayName("Se debe eliminar la mascota de la base de datos")
    void debeEliminarMascota(){
        //arrange
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(1L)).thenReturn(Boolean.TRUE);

        ServicioEliminarMascota servicioEliminarMascota = new ServicioEliminarMascota(repositorioMascota);

        //act - assert
        servicioEliminarMascota.ejecutar(1L);
        Mockito.verify(repositorioMascota,Mockito.times(1)).eliminar(1L);
    }

    @Test
    @DisplayName("Se debe lanzar una excepcion dado que no existe la mascota")
    void debeLanzarExcepcionNoExisteMascota(){
        //arrange
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existePorId(1L)).thenReturn(Boolean.FALSE);

        ServicioEliminarMascota servicioEliminarMascota = new ServicioEliminarMascota(repositorioMascota);

        //act - assert
        BasePrueba.assertThrows(()->{
            servicioEliminarMascota.ejecutar(1L);
        }, ExcepcionDuplicidad.class,"La mascota no existe en el sistema");

    }
}
