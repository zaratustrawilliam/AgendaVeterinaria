package com.ceiba.mascota.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.testdatabuilder.TipoMascotaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class MascotaTest {

    @Test
    @DisplayName("Deberia crear correctamemte la mascota")
    void deberiaCrearCorrectamenteLaMascota(){
        //arrange
        TipoMascota tipoMascota = new TipoMascotaTestDataBuilder()
                .aplicarTipoPerro()
                .build();

        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();
        //act
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Firulais")
                .conTipoMascota(tipoMascota)
                .conUsuario(usuario)
                .conId(1L)
                .build();
        //assert
        assertEquals(1l,mascota.getId());
        assertEquals("Firulais",mascota.getNombre());
        assertEquals("Carlos",mascota.getUsuario().getNombre());
        assertEquals(1L,mascota.getUsuario().getId());
        assertEquals(1,mascota.getTipoMascota().getId());
        assertEquals("PERRO",mascota.getTipoMascota().getNombre());
    }

    @Test
    @DisplayName("Deberia fallar sin nombre de la mascota")
    void deberiaFallarSinNombreDeLaMascota(){

        //arrange-act
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder().conNombre(null);
        //assert
        BasePrueba.assertThrows(()->{
            mascotaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar el nombre de la mascota");
    }

    @Test
    @DisplayName("Deberia fallar sin un usuario asociado")
    void deberiaFallarSinUnUsuarioAsociado(){
        //arrange-act
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder()
                .conNombre("Bethoven")
                .conUsuario(null);

        //assert
        BasePrueba.assertThrows(()->{
            mascotaTestDataBuilder.build();
        },ExcepcionValorObligatorio.class,"Se debe relacionar la mascota a un usuario");
    }

    @Test
    @DisplayName("Deberia fallar sin un tipo de mascota asociado")
    void deberiaFallarSinUnTipoDeMascotaAsociado(){

        //arrange
        Usuario usuario = new UsuarioTestDataBuilder()
                .conNombre("Carlos")
                .conId(1L)
                .build();
        //act
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder()
                .conNombre("Bethoven")
                .conUsuario(usuario)
                .conTipoMascota(null);

        //assert
        BasePrueba.assertThrows(()->{
            mascotaTestDataBuilder.build();
        },ExcepcionValorObligatorio.class,"Debe indicar de que tipo es la mascota");
    }


}
