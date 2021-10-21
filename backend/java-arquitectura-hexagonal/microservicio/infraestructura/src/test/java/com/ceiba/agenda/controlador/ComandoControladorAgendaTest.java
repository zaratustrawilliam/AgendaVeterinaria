package com.ceiba.agenda.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.servicioTestDataBuilder.ComandoAgendaTestDataBuilder;
import com.ceiba.usuario.controlador.ComandoControladorUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorAgendaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear una agenda")
    void deberiaCrearUnaAgenda() throws Exception {
        //arrange
        ComandoAgenda comandoAgenda =  new ComandoAgendaTestDataBuilder()
                .conMascota(1L)
                .conFechaAgenda(LocalDateTime.of(2022, Month.JANUARY,1,9,0))
                .conDireccionMascota(null)
                .conPrecio(null)
                .build();

        //act - assert
        mocMvc.perform(post("/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoAgenda)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar una agenda")
    void deberiaActualizarUnaAgenda() throws Exception{
        // arrange
        Long id = 1L;
        ComandoAgenda comandoAgenda = new ComandoAgendaTestDataBuilder()
                .conId(1L)
                .conMascota(1L)
                .conFechaAgenda(LocalDateTime.of(2022, Month.JANUARY,1,9,0))
                .conDireccionMascota("el monarca")
                .conPrecio(null)
                .build();
        // act - assert
        mocMvc.perform(put("/agendas",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoAgenda)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar una agenda")
    void deberiaEliminarUnaAgenda() throws Exception {
        // arrange
        Long idAgenda = 1L;
        // act - assert
        mocMvc.perform(delete("/agendas/{idAgenda}",idAgenda)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/agendas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
