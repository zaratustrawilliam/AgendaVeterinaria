package com.ceiba.agenda.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.agenda.modelo.entidad.Agenda;
import com.ceiba.agenda.servicio.testdatabuilder.AgendaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.tipomascota.testdatabuilder.TipoMascotaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgendaTest {

    @Test
    @DisplayName("Deberia crear correctamemte la agenda")
    void deberiaCrearCorrectamenteLaAgenda(){
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
        //act
        Agenda agenda = new AgendaTestDataBuilder()
                .conMascota(mascota)
                .conId(10L)
                .conDireccionMascota("Bulevar Cable")
                .conFechaAgenda(fechaCreacion)
                .build();
        //assert
        assertEquals(10L,agenda.getId());
        assertEquals("Bulevar Cable",agenda.getDireccionMascota());
        assertEquals(fechaCreacion,agenda.getFechaAgenda());
        assertEquals(mascota.getId(),agenda.getMascota().getId());
    }

    @Test
    @DisplayName("Deberia fallar sin mascota relacionada")
    void deberiaFallarSinReferenciaDeLaMascota(){
        //arrange-act
        LocalDateTime fechaCreacion = LocalDateTime.of(2021, Month.DECEMBER, 8, 8, 0);
        AgendaTestDataBuilder agendaTestDataBuilder = new AgendaTestDataBuilder()
                .conFechaAgenda(fechaCreacion);
        //assert
        BasePrueba.assertThrows(()->{
            agendaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"la Agenda debe estar asociada a una mascota");
    }

    @Test
    @DisplayName("Deberia fallar sin fecha de agenda ")
    void deberiaFallarSinFechaAgenda(){
        //arrange-act
        AgendaTestDataBuilder agendaTestDataBuilder = new AgendaTestDataBuilder()
                .conFechaAgenda(null);
        //assert
        BasePrueba.assertThrows(()->{
            agendaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"La agenda debe tener la fecha de reserva");
    }

    @Test
    @DisplayName("Deberia fallar cuando la fecha de la agenda es de un dia anterior a la actual, o es el del mismo" +
            "dia pero debe estar una hora delante de la actual ")
    void deberiaFallarFechaErronea(){
        //arrange-act
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        AgendaTestDataBuilder agendaTestDataBuilder = new AgendaTestDataBuilder()
                .conFechaAgenda(localDateTime);
        //assert
        BasePrueba.assertThrows(()->{
            agendaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"La fecha a agenda es menor a la fecha actual, eso es invalido");
    }

    @Test
    @DisplayName("Deberia fallar cuando la fecha de la agenda esta fuera del horario laboral")
    void deberiaFallarFechaFueraHorarioLaboral(){
        //arrange-act
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.DECEMBER,1,6,0);
        AgendaTestDataBuilder agendaTestDataBuilder = new AgendaTestDataBuilder()
                .conFechaAgenda(localDateTime);
        //assert
        BasePrueba.assertThrows(()->{
            agendaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"La agenda se debe hacer dentro del horario de trabajo");
    }
}
