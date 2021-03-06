package com.ceiba.agenda.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.agenda.comando.ComandoAgenda;
import com.ceiba.agenda.servicioTestDataBuilder.ComandoAgendaTestDataBuilder;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
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
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorAgendaTest {

    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deberia listar las agendas")
    void deberiaListarFechasDisponibles() throws Exception {
        // arrange
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("fecha", LocalDateTime.now().toString());
        requestParams.add("registros", "3");
        // act - assert
        mocMvc.perform(get("/agendas/disponibilidad")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Deberia listar las agendas por usuario")
    void deberiaListarAgendasPorUsuario() throws Exception {
        // arrange
        Long idusuario = 1L;
        // act - assert
        mocMvc.perform(get("/agendas/{idUsuario}",idusuario)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)));
    }

    @Test
    @DisplayName("Deberia listar las agendas")
    void deberiaListarAgendas() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/agendas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)));
    }

    @Test
    @DisplayName("Deberia listar las agendas cuando ese dia hay agendas")
    void deberiaListarFechasDisponiblesAgendaOcupada() throws Exception {
        // arrange
        ComandoAgenda comandoAgenda =  new ComandoAgendaTestDataBuilder()
                .conMascota(1L)
                .conFechaAgenda(LocalDateTime.of(LocalDateTime.now().plusYears(1).getYear(), Month.JANUARY,1,9,0))
                .conDireccionMascota(null)
                .conPrecio(null)
                .build();

        mocMvc.perform(post("/agendas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoAgenda)))
                .andExpect(status().isOk());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("fecha", LocalDateTime.of(LocalDateTime.now().plusYears(1).getYear(),
                Month.JANUARY,1,9,0).toString());
        requestParams.add("registros", "3");
        // act - assert
        mocMvc.perform(get("/agendas/disponibilidad")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].hora", is("10:0")));
    }
}
